package com.foodapp.deliveryexecutive.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to enable automatic logging for methods and classes.
 * When applied to a class, all public methods will be logged.
 * When applied to a method, only that method will be logged.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {

    /**
     * The category of the log entry
     */
    LogCategory category() default LogCategory.GENERAL;

    /**
     * Whether to log method entry
     */
    boolean logEntry() default true;

    /**
     * Whether to log method exit
     */
    boolean logExit() default true;

    /**
     * Whether to log method arguments
     */
    boolean logArgs() default true;

    /**
     * Whether to log return value
     */
    boolean logResult() default false;

    /**
     * Whether to log execution time
     */
    boolean logExecutionTime() default true;

    /**
     * Custom message to include in logs
     */
    String message() default "";

    /**
     * Whether to log exceptions
     */
    boolean logException() default true;
}
