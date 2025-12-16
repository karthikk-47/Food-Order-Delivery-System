package com.foodapp.deliveryexecutive.admin.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Actor {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    
    @Enumerated(EnumType.STRING)
    private AdminRole adminRole;
    
    @Enumerated(EnumType.STRING)
    private AdminStatus status;
    
    private Boolean canManageUsers;
    private Boolean canManagePayments;
    private Boolean canManageDisputes;
    private Boolean canViewAnalytics;
    private Boolean canManageAdmins;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    private String lastLoginIp;
    private Integer loginAttempts;
    private LocalDateTime accountLockedUntil;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = AdminStatus.ACTIVE;
        this.loginAttempts = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getMobile() {
        return this.phoneNumber;
    }

    public void setMobile(String mobile) {
        this.phoneNumber = mobile;
    }

    public enum AdminStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED
    }

    public enum AdminRole {
        SUPER_ADMIN,
        MODERATOR,
        SUPPORT
    }
}
