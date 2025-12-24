package com.foodapp.deliveryexecutive.logging.service;

import com.foodapp.deliveryexecutive.logging.annotation.LogCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Application Logger - A wrapper around AsyncLoggingService for convenient
 * injection.
 * Use this in your services and controllers instead of directly accessing SLF4J
 * loggers.
 * 
 * This completely separates logging from business logic and ensures all logging
 * is performed asynchronously.
 * 
 * Example usage:
 * 
 * @Autowired
 *            private AppLogger appLogger;
 * 
 *            public void someMethod() {
 *            appLogger.info(LogCategory.ORDER, "Processing order {}", orderId);
 *            }
 */
@Component
public class AppLogger {

    @Autowired
    private AsyncLoggingService asyncLoggingService;

    // ========== General Logging Methods ==========

    public void trace(String message, Object... args) {
        asyncLoggingService.logAsync(
                com.foodapp.deliveryexecutive.logging.model.LogEvent.LogLevel.TRACE,
                LogCategory.GENERAL,
                message,
                args);
    }

    public void debug(String message, Object... args) {
        asyncLoggingService.debug(LogCategory.GENERAL, message, args);
    }

    public void info(String message, Object... args) {
        asyncLoggingService.info(LogCategory.GENERAL, message, args);
    }

    public void warn(String message, Object... args) {
        asyncLoggingService.warn(LogCategory.GENERAL, message, args);
    }

    public void error(String message, Object... args) {
        asyncLoggingService.error(LogCategory.GENERAL, message, args);
    }

    // ========== Categorized Logging Methods ==========

    public void trace(LogCategory category, String message, Object... args) {
        asyncLoggingService.logAsync(
                com.foodapp.deliveryexecutive.logging.model.LogEvent.LogLevel.TRACE,
                category,
                message,
                args);
    }

    public void debug(LogCategory category, String message, Object... args) {
        asyncLoggingService.debug(category, message, args);
    }

    public void info(LogCategory category, String message, Object... args) {
        asyncLoggingService.info(category, message, args);
    }

    public void warn(LogCategory category, String message, Object... args) {
        asyncLoggingService.warn(category, message, args);
    }

    public void error(LogCategory category, String message, Object... args) {
        asyncLoggingService.error(category, message, args);
    }

    // ========== Domain-Specific Convenience Methods ==========

    /**
     * Log order-related events
     */
    public void order(String message, Object... args) {
        asyncLoggingService.info(LogCategory.ORDER, message, args);
    }

    /**
     * Log payment-related events
     */
    public void payment(String message, Object... args) {
        asyncLoggingService.info(LogCategory.PAYMENT, message, args);
    }

    /**
     * Log user-related events
     */
    public void user(String message, Object... args) {
        asyncLoggingService.info(LogCategory.USER, message, args);
    }

    /**
     * Log delivery-related events
     */
    public void delivery(String message, Object... args) {
        asyncLoggingService.info(LogCategory.DELIVERY, message, args);
    }

    /**
     * Log notification-related events
     */
    public void notification(String message, Object... args) {
        asyncLoggingService.info(LogCategory.NOTIFICATION, message, args);
    }

    /**
     * Log authentication-related events
     */
    public void auth(String message, Object... args) {
        asyncLoggingService.info(LogCategory.AUTHENTICATION, message, args);
    }

    /**
     * Log security-related events
     */
    public void security(String message, Object... args) {
        asyncLoggingService.info(LogCategory.SECURITY, message, args);
    }

    /**
     * Log performance-related events
     */
    public void performance(String message, Object... args) {
        asyncLoggingService.info(LogCategory.PERFORMANCE, message, args);
    }

    /**
     * Log external API calls
     */
    public void externalApi(String message, Object... args) {
        asyncLoggingService.info(LogCategory.EXTERNAL_API, message, args);
    }

    /**
     * Log database operations
     */
    public void database(String message, Object... args) {
        asyncLoggingService.info(LogCategory.DATABASE, message, args);
    }

    /**
     * Log admin operations
     */
    public void admin(String message, Object... args) {
        asyncLoggingService.info(LogCategory.ADMIN, message, args);
    }

    /**
     * Log homemaker-related events
     */
    public void homemaker(String message, Object... args) {
        asyncLoggingService.info(LogCategory.HOMEMAKER, message, args);
    }

    /**
     * Log websocket events
     */
    public void websocket(String message, Object... args) {
        asyncLoggingService.info(LogCategory.WEBSOCKET, message, args);
    }
}
