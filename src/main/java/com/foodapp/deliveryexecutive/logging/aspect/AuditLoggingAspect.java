package com.foodapp.deliveryexecutive.logging.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.foodapp.deliveryexecutive.logging.annotation.AuditLog;
import com.foodapp.deliveryexecutive.logging.model.AuditLogEntry;
import com.foodapp.deliveryexecutive.logging.service.AsyncLoggingService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Aspect for audit logging of methods annotated with @AuditLog.
 * Captures detailed information about operations for compliance and security.
 */
@Aspect
@Component
@Order(0)
public class AuditLoggingAspect {

    @Autowired
    private AsyncLoggingService asyncLoggingService;

    private final ObjectMapper objectMapper;

    public AuditLoggingAspect() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Around("@annotation(auditLog)")
    public Object logAudit(ProceedingJoinPoint joinPoint, AuditLog auditLog) throws Throwable {
        long startTime = System.currentTimeMillis();

        AuditLogEntry.AuditLogEntryBuilder entryBuilder = AuditLogEntry.builder()
                .timestamp(LocalDateTime.now())
                .action(auditLog.action())
                .entityType(auditLog.entityType());

        // Get request details
        HttpServletRequest request = getRequest();
        if (request != null) {
            entryBuilder
                    .ipAddress(getClientIpAddress(request))
                    .userAgent(request.getHeader("User-Agent"))
                    .requestUri(request.getRequestURI())
                    .requestMethod(request.getMethod());

            if (auditLog.captureRequest()) {
                entryBuilder.requestBody(extractRequestBody(joinPoint));
            }
        }

        // Get user details
        if (auditLog.captureUser()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                entryBuilder.userId(auth.getName());
                entryBuilder.userRole(getAuthorities(auth));
            }
        }

        // Extract entity ID if possible
        String entityId = extractEntityId(joinPoint);
        if (entityId != null) {
            entryBuilder.entityId(entityId);
        }

        Object result = null;
        boolean success = true;
        String errorMessage = null;

        try {
            result = joinPoint.proceed();

            if (auditLog.captureResponse() && result != null) {
                entryBuilder.responseBody(serializeResult(result));
            }

            return result;

        } catch (Throwable ex) {
            success = false;
            errorMessage = ex.getMessage();
            throw ex;

        } finally {
            long executionTime = System.currentTimeMillis() - startTime;

            entryBuilder
                    .success(success)
                    .errorMessage(errorMessage)
                    .executionTimeMs(executionTime);

            // Save audit log asynchronously
            asyncLoggingService.saveAuditLogAsync(entryBuilder.build());
        }
    }

    private HttpServletRequest getRequest() {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return attrs != null ? attrs.getRequest() : null;
        } catch (Exception e) {
            return null;
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }
        return request.getRemoteAddr();
    }

    private String getAuthorities(Authentication auth) {
        if (auth.getAuthorities() != null && !auth.getAuthorities().isEmpty()) {
            return auth.getAuthorities().stream()
                    .map(a -> a.getAuthority())
                    .reduce((a, b) -> a + "," + b)
                    .orElse("");
        }
        return "";
    }

    private String extractRequestBody(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                // Find the request body (skip primitives and HttpServletRequest)
                for (Object arg : args) {
                    if (arg != null &&
                            !(arg instanceof HttpServletRequest) &&
                            !isPrimitive(arg)) {
                        return serializeWithTruncation(arg, 5000);
                    }
                }
            }
        } catch (Exception e) {
            return "[failed to extract]";
        }
        return null;
    }

    private String extractEntityId(ProceedingJoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String[] paramNames = signature.getParameterNames();
            Object[] args = joinPoint.getArgs();

            if (paramNames != null && args != null) {
                for (int i = 0; i < paramNames.length; i++) {
                    String name = paramNames[i].toLowerCase();
                    if ((name.equals("id") || name.endsWith("id")) && args[i] != null) {
                        return String.valueOf(args[i]);
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private boolean isPrimitive(Object obj) {
        return obj instanceof String ||
                obj instanceof Number ||
                obj instanceof Boolean ||
                obj instanceof Character;
    }

    private String serializeResult(Object result) {
        return serializeWithTruncation(result, 2000);
    }

    private String serializeWithTruncation(Object obj, int maxLength) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            if (json.length() > maxLength) {
                return json.substring(0, maxLength) + "...[truncated]";
            }
            return json;
        } catch (JsonProcessingException e) {
            return "[serialization failed]";
        }
    }
}
