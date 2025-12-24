package com.foodapp.deliveryexecutive.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for audit logging - tracks important business operations
 * for compliance and security purposes.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog {

    /**
     * The action being performed
     */
    String action();

    /**
     * The entity type being operated on
     */
    String entityType() default "";

    /**
     * Whether to capture request details
     */
    boolean captureRequest() default true;

    /**
     * Whether to capture response details
     */
    boolean captureResponse() default false;

    /**
     * Whether to capture user information
     */
    boolean captureUser() default true;
}
