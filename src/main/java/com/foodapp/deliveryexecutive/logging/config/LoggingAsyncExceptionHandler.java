package com.foodapp.deliveryexecutive.logging.config;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 * Handler for exceptions that occur during async logging operations.
 * Ensures that logging failures don't crash the application.
 */
public class LoggingAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAsyncExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        logger.error(
                "Async logging exception in method '{}': {}",
                method.getName(),
                ex.getMessage(),
                ex);

        // Log parameters for debugging
        if (params != null && params.length > 0) {
            StringBuilder paramInfo = new StringBuilder("Parameters: ");
            for (int i = 0; i < params.length; i++) {
                paramInfo.append(String.format("[%d]=%s ", i, params[i]));
            }
            logger.debug(paramInfo.toString());
        }
    }
}
