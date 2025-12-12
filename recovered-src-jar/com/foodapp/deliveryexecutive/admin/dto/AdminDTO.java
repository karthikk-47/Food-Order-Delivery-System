/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.admin.dto;

import com.foodapp.deliveryexecutive.admin.entity.Admin;
import java.time.LocalDateTime;
import lombok.Generated;

public class AdminDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private Admin.AdminRole adminRole;
    private Admin.AdminStatus status;
    private Boolean canManageUsers;
    private Boolean canManagePayments;
    private Boolean canManageDisputes;
    private Boolean canViewAnalytics;
    private Boolean canManageAdmins;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    private Boolean isAccountLocked;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getUsername() {
        return this.username;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getPassword() {
        return this.password;
    }

    @Generated
    public String getFullName() {
        return this.fullName;
    }

    @Generated
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Generated
    public Admin.AdminRole getAdminRole() {
        return this.adminRole;
    }

    @Generated
    public Admin.AdminStatus getStatus() {
        return this.status;
    }

    @Generated
    public Boolean getCanManageUsers() {
        return this.canManageUsers;
    }

    @Generated
    public Boolean getCanManagePayments() {
        return this.canManagePayments;
    }

    @Generated
    public Boolean getCanManageDisputes() {
        return this.canManageDisputes;
    }

    @Generated
    public Boolean getCanViewAnalytics() {
        return this.canViewAnalytics;
    }

    @Generated
    public Boolean getCanManageAdmins() {
        return this.canManageAdmins;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Generated
    public LocalDateTime getLastLogin() {
        return this.lastLogin;
    }

    @Generated
    public Boolean getIsAccountLocked() {
        return this.isAccountLocked;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setUsername(String username) {
        this.username = username;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Generated
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated
    public void setAdminRole(Admin.AdminRole adminRole) {
        this.adminRole = adminRole;
    }

    @Generated
    public void setStatus(Admin.AdminStatus status) {
        this.status = status;
    }

    @Generated
    public void setCanManageUsers(Boolean canManageUsers) {
        this.canManageUsers = canManageUsers;
    }

    @Generated
    public void setCanManagePayments(Boolean canManagePayments) {
        this.canManagePayments = canManagePayments;
    }

    @Generated
    public void setCanManageDisputes(Boolean canManageDisputes) {
        this.canManageDisputes = canManageDisputes;
    }

    @Generated
    public void setCanViewAnalytics(Boolean canViewAnalytics) {
        this.canViewAnalytics = canViewAnalytics;
    }

    @Generated
    public void setCanManageAdmins(Boolean canManageAdmins) {
        this.canManageAdmins = canManageAdmins;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Generated
    public void setIsAccountLocked(Boolean isAccountLocked) {
        this.isAccountLocked = isAccountLocked;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AdminDTO)) {
            return false;
        }
        AdminDTO other = (AdminDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Boolean this$canManageUsers = this.getCanManageUsers();
        Boolean other$canManageUsers = other.getCanManageUsers();
        if (this$canManageUsers == null ? other$canManageUsers != null : !((Object)this$canManageUsers).equals(other$canManageUsers)) {
            return false;
        }
        Boolean this$canManagePayments = this.getCanManagePayments();
        Boolean other$canManagePayments = other.getCanManagePayments();
        if (this$canManagePayments == null ? other$canManagePayments != null : !((Object)this$canManagePayments).equals(other$canManagePayments)) {
            return false;
        }
        Boolean this$canManageDisputes = this.getCanManageDisputes();
        Boolean other$canManageDisputes = other.getCanManageDisputes();
        if (this$canManageDisputes == null ? other$canManageDisputes != null : !((Object)this$canManageDisputes).equals(other$canManageDisputes)) {
            return false;
        }
        Boolean this$canViewAnalytics = this.getCanViewAnalytics();
        Boolean other$canViewAnalytics = other.getCanViewAnalytics();
        if (this$canViewAnalytics == null ? other$canViewAnalytics != null : !((Object)this$canViewAnalytics).equals(other$canViewAnalytics)) {
            return false;
        }
        Boolean this$canManageAdmins = this.getCanManageAdmins();
        Boolean other$canManageAdmins = other.getCanManageAdmins();
        if (this$canManageAdmins == null ? other$canManageAdmins != null : !((Object)this$canManageAdmins).equals(other$canManageAdmins)) {
            return false;
        }
        Boolean this$isAccountLocked = this.getIsAccountLocked();
        Boolean other$isAccountLocked = other.getIsAccountLocked();
        if (this$isAccountLocked == null ? other$isAccountLocked != null : !((Object)this$isAccountLocked).equals(other$isAccountLocked)) {
            return false;
        }
        String this$username = this.getUsername();
        String other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }
        String this$fullName = this.getFullName();
        String other$fullName = other.getFullName();
        if (this$fullName == null ? other$fullName != null : !this$fullName.equals(other$fullName)) {
            return false;
        }
        String this$phoneNumber = this.getPhoneNumber();
        String other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber)) {
            return false;
        }
        Admin.AdminRole this$adminRole = this.getAdminRole();
        Admin.AdminRole other$adminRole = other.getAdminRole();
        if (this$adminRole == null ? other$adminRole != null : !((Object)((Object)this$adminRole)).equals((Object)other$adminRole)) {
            return false;
        }
        Admin.AdminStatus this$status = this.getStatus();
        Admin.AdminStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)((Object)this$status)).equals((Object)other$status)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !((Object)this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !((Object)this$updatedAt).equals(other$updatedAt)) {
            return false;
        }
        LocalDateTime this$lastLogin = this.getLastLogin();
        LocalDateTime other$lastLogin = other.getLastLogin();
        return !(this$lastLogin == null ? other$lastLogin != null : !((Object)this$lastLogin).equals(other$lastLogin));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof AdminDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Boolean $canManageUsers = this.getCanManageUsers();
        result = result * 59 + ($canManageUsers == null ? 43 : ((Object)$canManageUsers).hashCode());
        Boolean $canManagePayments = this.getCanManagePayments();
        result = result * 59 + ($canManagePayments == null ? 43 : ((Object)$canManagePayments).hashCode());
        Boolean $canManageDisputes = this.getCanManageDisputes();
        result = result * 59 + ($canManageDisputes == null ? 43 : ((Object)$canManageDisputes).hashCode());
        Boolean $canViewAnalytics = this.getCanViewAnalytics();
        result = result * 59 + ($canViewAnalytics == null ? 43 : ((Object)$canViewAnalytics).hashCode());
        Boolean $canManageAdmins = this.getCanManageAdmins();
        result = result * 59 + ($canManageAdmins == null ? 43 : ((Object)$canManageAdmins).hashCode());
        Boolean $isAccountLocked = this.getIsAccountLocked();
        result = result * 59 + ($isAccountLocked == null ? 43 : ((Object)$isAccountLocked).hashCode());
        String $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        String $fullName = this.getFullName();
        result = result * 59 + ($fullName == null ? 43 : $fullName.hashCode());
        String $phoneNumber = this.getPhoneNumber();
        result = result * 59 + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        Admin.AdminRole $adminRole = this.getAdminRole();
        result = result * 59 + ($adminRole == null ? 43 : ((Object)((Object)$adminRole)).hashCode());
        Admin.AdminStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        LocalDateTime $lastLogin = this.getLastLogin();
        result = result * 59 + ($lastLogin == null ? 43 : ((Object)$lastLogin).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "AdminDTO(id=" + this.getId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", fullName=" + this.getFullName() + ", phoneNumber=" + this.getPhoneNumber() + ", adminRole=" + String.valueOf((Object)this.getAdminRole()) + ", status=" + String.valueOf((Object)this.getStatus()) + ", canManageUsers=" + this.getCanManageUsers() + ", canManagePayments=" + this.getCanManagePayments() + ", canManageDisputes=" + this.getCanManageDisputes() + ", canViewAnalytics=" + this.getCanViewAnalytics() + ", canManageAdmins=" + this.getCanManageAdmins() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ", lastLogin=" + String.valueOf(this.getLastLogin()) + ", isAccountLocked=" + this.getIsAccountLocked() + ")";
    }

    @Generated
    public AdminDTO() {
    }

    @Generated
    public AdminDTO(Long id, String username, String email, String password, String fullName, String phoneNumber, Admin.AdminRole adminRole, Admin.AdminStatus status, Boolean canManageUsers, Boolean canManagePayments, Boolean canManageDisputes, Boolean canViewAnalytics, Boolean canManageAdmins, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastLogin, Boolean isAccountLocked) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.adminRole = adminRole;
        this.status = status;
        this.canManageUsers = canManageUsers;
        this.canManagePayments = canManagePayments;
        this.canManageDisputes = canManageDisputes;
        this.canViewAnalytics = canViewAnalytics;
        this.canManageAdmins = canManageAdmins;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.isAccountLocked = isAccountLocked;
    }
}
