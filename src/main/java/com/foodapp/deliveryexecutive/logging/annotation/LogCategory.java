package com.foodapp.deliveryexecutive.logging.annotation;

/**
 * Categories for log entries to help organize and filter logs.
 */
public enum LogCategory {
    GENERAL("general"),
    AUTHENTICATION("auth"),
    ORDER("order"),
    PAYMENT("payment"),
    NOTIFICATION("notification"),
    DELIVERY("delivery"),
    USER("user"),
    HOMEMAKER("homemaker"),
    ADMIN("admin"),
    SECURITY("security"),
    DATABASE("database"),
    EXTERNAL_API("external-api"),
    WEBSOCKET("websocket"),
    CACHE("cache"),
    PERFORMANCE("performance"),
    AUDIT("audit");

    private final String value;

    LogCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
