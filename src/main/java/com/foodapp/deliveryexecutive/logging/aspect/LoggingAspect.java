package com.foodapp.deliveryexecutive.logging.aspect;

import com.foodapp.deliveryexecutive.logging.annotation.LogCategory;
import com.foodapp.deliveryexecutive.logging.annotation.Loggable;
import com.foodapp.deliveryexecutive.logging.service.AsyncLoggingService;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Aspect for automatic logging of methods annotated with @Loggable.
 * This separates logging logic from business logic using AOP.
 */
@Aspect
@Component
@Order(1)
public class LoggingAspect {

    @Autowired
    private AsyncLoggingService asyncLoggingService;

    /**
     * Pointcut for methods annotated with @Loggable
     */
    @Pointcut("@annotation(com.foodapp.deliveryexecutive.logging.annotation.Loggable)")
    public void loggableMethod() {
    }

    /**
     * Pointcut for classes annotated with @Loggable
     */
    @Pointcut("@within(com.foodapp.deliveryexecutive.logging.annotation.Loggable)")
    public void loggableClass() {
    }

    /**
     * Pointcut for all public methods in service classes
     */
    @Pointcut("execution(public * com.foodapp.deliveryexecutive..service..*(..))")
    public void serviceMethod() {
    }

    /**
     * Pointcut for all public methods in controller classes
     */
    @Pointcut("execution(public * com.foodapp.deliveryexecutive..controller..*(..))")
    public void controllerMethod() {
    }

    /**
     * Around advice for methods annotated with @Loggable
     */
    @Around("loggableMethod() || loggableClass()")
    public Object logAnnotatedMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // Get annotation from method or class
        Loggable loggable = method.getAnnotation(Loggable.class);
        if (loggable == null) {
            loggable = joinPoint.getTarget().getClass().getAnnotation(Loggable.class);
        }

        if (loggable == null) {
            return joinPoint.proceed();
        }

        return executeWithLogging(joinPoint, loggable);
    }

    /**
     * Around advice for service methods (optional auto-logging)
     * Only logs if DEBUG level is enabled
     */
    @Around("serviceMethod() && !loggableMethod() && !loggableClass()")
    public Object logServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // Auto-log service methods with default settings
        LogCategory category = determineCategory(joinPoint);
        return executeWithDefaultLogging(joinPoint, category);
    }

    /**
     * Executes the method with logging based on @Loggable annotation settings
     */
    private Object executeWithLogging(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        LogCategory category = loggable.category();

        Map<String, Object> parameters = null;
        if (loggable.logArgs()) {
            parameters = extractParameters(joinPoint);
        }

        // Log entry
        if (loggable.logEntry()) {
            asyncLoggingService.logMethodEntry(className, methodName, category, parameters);
        }

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            // Log exit
            if (loggable.logExit() || loggable.logExecutionTime()) {
                long executionTime = System.currentTimeMillis() - startTime;
                asyncLoggingService.logMethodExit(
                        className,
                        methodName,
                        category,
                        executionTime,
                        result,
                        loggable.logResult());
            }

            return result;

        } catch (Throwable ex) {
            // Log exception
            if (loggable.logException()) {
                asyncLoggingService.logException(className, methodName, category, ex, parameters);
            }
            throw ex;
        }
    }

    /**
     * Executes the method with default logging settings
     */
    private Object executeWithDefaultLogging(ProceedingJoinPoint joinPoint, LogCategory category) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long executionTime = System.currentTimeMillis() - startTime;

            // Only log if execution is slow (> 1 second) or DEBUG is enabled
            if (executionTime > 1000) {
                asyncLoggingService.logMethodExit(
                        className,
                        methodName,
                        category,
                        executionTime,
                        null,
                        false);
            }

            return result;

        } catch (Throwable ex) {
            Map<String, Object> parameters = extractParameters(joinPoint);
            asyncLoggingService.logException(className, methodName, category, ex, parameters);
            throw ex;
        }
    }

    /**
     * Extracts method parameters as a map
     */
    private Map<String, Object> extractParameters(ProceedingJoinPoint joinPoint) {
        Map<String, Object> parameters = new HashMap<>();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        if (parameterNames != null && args != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                String paramName = parameterNames[i];
                Object paramValue = args[i];

                // Skip sensitive parameters
                if (isSensitiveParameter(paramName)) {
                    parameters.put(paramName, "[REDACTED]");
                } else if (paramValue != null) {
                    parameters.put(paramName, summarizeValue(paramValue));
                } else {
                    parameters.put(paramName, "null");
                }
            }
        }

        return parameters;
    }

    /**
     * Determines the log category based on the package/class name
     */
    private LogCategory determineCategory(ProceedingJoinPoint joinPoint) {
        String packageName = joinPoint.getTarget().getClass().getPackage().getName();

        if (packageName.contains(".order")) {
            return LogCategory.ORDER;
        } else if (packageName.contains(".payment")) {
            return LogCategory.PAYMENT;
        } else if (packageName.contains(".user")) {
            return LogCategory.USER;
        } else if (packageName.contains(".homemaker")) {
            return LogCategory.HOMEMAKER;
        } else if (packageName.contains(".executive") || packageName.contains(".delivery")) {
            return LogCategory.DELIVERY;
        } else if (packageName.contains(".notification")) {
            return LogCategory.NOTIFICATION;
        } else if (packageName.contains(".security") || packageName.contains(".auth")) {
            return LogCategory.AUTHENTICATION;
        } else if (packageName.contains(".admin")) {
            return LogCategory.ADMIN;
        } else if (packageName.contains(".websocket")) {
            return LogCategory.WEBSOCKET;
        } else if (packageName.contains(".integration") || packageName.contains(".external")) {
            return LogCategory.EXTERNAL_API;
        }

        return LogCategory.GENERAL;
    }

    /**
     * Checks if a parameter name indicates sensitive data
     */
    private boolean isSensitiveParameter(String paramName) {
        String lowerName = paramName.toLowerCase();
        return lowerName.contains("password") ||
                lowerName.contains("secret") ||
                lowerName.contains("token") ||
                lowerName.contains("otp") ||
                lowerName.contains("pin") ||
                lowerName.contains("apikey") ||
                lowerName.contains("credential");
    }

    /**
     * Summarizes a value for logging (handles large objects)
     */
    private Object summarizeValue(Object value) {
        if (value == null) {
            return "null";
        }

        String str = value.toString();
        if (str.length() > 500) {
            return str.substring(0, 500) + "...[truncated]";
        }

        return value;
    }
}
