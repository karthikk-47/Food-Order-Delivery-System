package com.foodapp.deliveryexecutive.admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogLevel level;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogCategory category;
    
    @Column(nullable = false)
    private String action;
    
    @Column(columnDefinition = "TEXT")
    private String message;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "user_role")
    private String userRole;
    
    @Column(name = "user_mobile")
    private String userMobile;
    
    @Column(name = "ip_address")
    private String ipAddress;
    
    @Column(name = "entity_type")
    private String entityType;
    
    @Column(name = "entity_id")
    private Long entityId;
    
    @Column(columnDefinition = "TEXT")
    private String details;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @PrePersist
    protected void onCreate() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }
    
    public enum LogLevel {
        INFO, WARNING, ERROR, DEBUG
    }
    
    public enum LogCategory {
        AUTH,           // Login, logout, password reset
        USER,           // User management
        HOMEMAKER,      // Homemaker management
        EXECUTIVE,      // Executive management
        ORDER,          // Order operations
        PAYMENT,        // Payment operations
        APPROVAL,       // Approval/rejection actions
        SYSTEM,         // System events
        ADMIN           // Admin actions
    }
}
