package com.foodapp.deliveryexecutive.logging.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.foodapp.deliveryexecutive.logging.annotation.LogCategory;
import com.foodapp.deliveryexecutive.logging.model.AuditLogEntry;
import com.foodapp.deliveryexecutive.logging.model.LogEvent;
import com.foodapp.deliveryexecutive.logging.repository.AuditLogRepository;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Asynchronous logging service that handles all logging operations
 * without blocking the main application threads.
 * 
 * This service provides:
 * - Async logging to SLF4J
 * - Structured JSON logging
 * - Audit log persistence
 * - Context preservation across async boundaries
 */
@Service
public class AsyncLoggingService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncLoggingService.class);
    private final ObjectMapper objectMapper;

    @Autowired(required = false)
    private AuditLogRepository auditLogRepository;

    public AsyncLoggingService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Logs a message asynchronously.
     */
    @Async("loggingTaskExecutor")
    public CompletableFuture<Void> logAsync(LogEvent.LogLevel level, LogCategory category, String message,
            Object... args) {
        try {
            String formattedMessage = formatLogMessage(category, message);

            switch (level) {
                case TRACE -> logger.trace(formattedMessage, args);
                case DEBUG -> logger.debug(formattedMessage, args);
                case INFO -> logger.info(formattedMessage, args);
                case WARN -> logger.warn(formattedMessage, args);
                case ERROR -> logger.error(formattedMessage, args);
            }
        } catch (Exception e) {
            logger.error("Failed to log message asynchronously: {}", e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Logs a structured event asynchronously.
     */
    @Async("loggingTaskExecutor")
    public CompletableFuture<Void> logEventAsync(LogEvent event) {
        try {
            if (event.getId() == null) {
                event.setId(UUID.randomUUID().toString());
            }
            if (event.getTimestamp() == null) {
                event.setTimestamp(LocalDateTime.now());
            }

            // Set MDC context for structured logging
            setMDCContext(event);

            String jsonEvent = objectMapper.writeValueAsString(event);
            String message = String.format("[%s] %s - %s",
                    event.getCategory() != null ? event.getCategory().getValue() : "general",
                    event.getMessage(),
                    jsonEvent);

            switch (event.getLevel()) {
                case TRACE -> logger.trace(message);
                case DEBUG -> logger.debug(message);
                case INFO -> logger.info(message);
                case WARN -> logger.warn(message);
                case ERROR -> logger.error(message);
            }

            clearMDCContext();
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize log event: {}", e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Logs method entry asynchronously.
     */
    @Async("loggingTaskExecutor")
    public CompletableFuture<Void> logMethodEntry(
            String className,
            String methodName,
            LogCategory category,
            Map<String, Object> parameters) {
        try {
            String message = String.format("[%s] Entering %s.%s with params: %s",
                    category.getValue(),
                    className,
                    methodName,
                    sanitizeParameters(parameters));
            logger.debug(message);
        } catch (Exception e) {
            logger.error("Failed to log method entry: {}", e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Logs method exit asynchronously.
     */
    @Async("loggingTaskExecutor")
    public CompletableFuture<Void> logMethodExit(
            String className,
            String methodName,
            LogCategory category,
            long executionTimeMs,
            Object result,
            boolean logResult) {
        try {
            String resultInfo = logResult ? ", result: " + sanitizeResult(result) : "";
            String message = String.format("[%s] Exiting %s.%s in %dms%s",
                    category.getValue(),
                    className,
                    methodName,
                    executionTimeMs,
                    resultInfo);
            logger.debug(message);

            // Log performance warning if method is slow
            if (executionTimeMs > 5000) {
                logger.warn("[performance] Slow method detected: {}.{} took {}ms",
                        className, methodName, executionTimeMs);
            }
        } catch (Exception e) {
            logger.error("Failed to log method exit: {}", e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Logs an exception asynchronously.
     */
    @Async("loggingTaskExecutor")
    public CompletableFuture<Void> logException(
            String className,
            String methodName,
            LogCategory category,
            Throwable exception,
            Map<String, Object> parameters) {
        try {
            LogEvent event = LogEvent.builder()
                    .id(UUID.randomUUID().toString())
                    .timestamp(LocalDateTime.now())
                    .level(LogEvent.LogLevel.ERROR)
                    .category(category)
                    .message("Exception in " + methodName)
                    .className(className)
                    .methodName(methodName)
                    .parameters(parameters)
                    .exceptionType(exception.getClass().getName())
                    .exceptionMessage(exception.getMessage())
                    .stackTrace(getStackTrace(exception))
                    .build();

            logger.error("[{}] Exception in {}.{}: {}",
                    category.getValue(),
                    className,
                    methodName,
                    exception.getMessage(),
                    exception);

        } catch (Exception e) {
            logger.error("Failed to log exception: {}", e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Saves an audit log entry asynchronously.
     */
    @Async("auditLoggingExecutor")
    public CompletableFuture<Long> saveAuditLogAsync(AuditLogEntry entry) {
        try {
            if (entry.getTimestamp() == null) {
                entry.setTimestamp(LocalDateTime.now());
            }

            if (auditLogRepository != null) {
                AuditLogEntry saved = auditLogRepository.save(entry);
                logger.debug("[audit] Saved audit log entry: action={}, entityType={}, userId={}",
                        entry.getAction(), entry.getEntityType(), entry.getUserId());
                return CompletableFuture.completedFuture(saved.getId());
            } else {
                // If repository not available, just log to file
                logger.info("[audit] {}", objectMapper.writeValueAsString(entry));
                return CompletableFuture.completedFuture(null);
            }
        } catch (Exception e) {
            logger.error("Failed to save audit log: {}", e.getMessage());
            return CompletableFuture.completedFuture(null);
        }
    }

    /**
     * Convenient method for info logging.
     */
    @Async("loggingTaskExecutor")
    public void info(LogCategory category, String message, Object... args) {
        logAsync(LogEvent.LogLevel.INFO, category, message, args);
    }

    /**
     * Convenient method for debug logging.
     */
    @Async("loggingTaskExecutor")
    public void debug(LogCategory category, String message, Object... args) {
        logAsync(LogEvent.LogLevel.DEBUG, category, message, args);
    }

    /**
     * Convenient method for warn logging.
     */
    @Async("loggingTaskExecutor")
    public void warn(LogCategory category, String message, Object... args) {
        logAsync(LogEvent.LogLevel.WARN, category, message, args);
    }

    /**
     * Convenient method for error logging.
     */
    @Async("loggingTaskExecutor")
    public void error(LogCategory category, String message, Object... args) {
        logAsync(LogEvent.LogLevel.ERROR, category, message, args);
    }

    // ========== Helper Methods ==========

    private String formatLogMessage(LogCategory category, String message) {
        return String.format("[%s] %s", category.getValue(), message);
    }

    private void setMDCContext(LogEvent event) {
        if (event.getRequestId() != null) {
            MDC.put("requestId", event.getRequestId());
        }
        if (event.getTraceId() != null) {
            MDC.put("traceId", event.getTraceId());
        }
        if (event.getUserId() != null) {
            MDC.put("userId", event.getUserId());
        }
        if (event.getCategory() != null) {
            MDC.put("category", event.getCategory().getValue());
        }
    }

    private void clearMDCContext() {
        MDC.remove("requestId");
        MDC.remove("traceId");
        MDC.remove("userId");
        MDC.remove("category");
    }

    private String sanitizeParameters(Map<String, Object> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return "{}";
        }
        try {
            // Remove sensitive fields
            parameters.remove("password");
            parameters.remove("token");
            parameters.remove("secret");
            parameters.remove("apiKey");
            parameters.remove("otp");
            return objectMapper.writeValueAsString(parameters);
        } catch (JsonProcessingException e) {
            return parameters.toString();
        }
    }

    private String sanitizeResult(Object result) {
        if (result == null) {
            return "null";
        }
        try {
            String json = objectMapper.writeValueAsString(result);
            // Truncate if too long
            if (json.length() > 1000) {
                return json.substring(0, 1000) + "...[truncated]";
            }
            return json;
        } catch (JsonProcessingException e) {
            return result.toString();
        }
    }

    private String getStackTrace(Throwable t) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : t.getStackTrace()) {
            sb.append(element.toString()).append("\n");
            if (sb.length() > 2000) {
                sb.append("...[truncated]");
                break;
            }
        }
        return sb.toString();
    }
}
