package com.foodapp.deliveryexecutive.logging.model;

import com.foodapp.deliveryexecutive.logging.annotation.LogCategory;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing a structured log event.
 * This provides a consistent format for all log entries.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEvent {

    private String id;
    private LocalDateTime timestamp;
    private LogLevel level;
    private LogCategory category;
    private String message;
    private String className;
    private String methodName;
    private String userId;
    private String sessionId;
    private String requestId;
    private String traceId;
    private Long executionTimeMs;
    private Map<String, Object> parameters;
    private Object result;
    private String exceptionType;
    private String exceptionMessage;
    private String stackTrace;
    private Map<String, String> metadata;

    public enum LogLevel {
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }
}
