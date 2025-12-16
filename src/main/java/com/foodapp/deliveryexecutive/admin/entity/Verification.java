/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.PrePersist
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="verifications")
public class Verification {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String userType;
    @Enumerated(value=EnumType.STRING)
    private VerificationType verificationType;
    @Enumerated(value=EnumType.STRING)
    private VerificationStatus status;
    private String documentUrl;
    private String identityNumber;
    private String bankAccountNumber;
    private String phoneNumber;
    private String email;
    private Long verifiedBy;
    private String rejectionReason;
    private LocalDateTime submittedAt;
    private LocalDateTime verifiedAt;
    private LocalDateTime expiresAt;
    private Integer verificationAttempts;

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
        this.status = VerificationStatus.PENDING;
        this.verificationAttempts = 0;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getUserId() {
        return this.userId;
    }

    @Generated
    public String getUserType() {
        return this.userType;
    }

    @Generated
    public VerificationType getVerificationType() {
        return this.verificationType;
    }

    @Generated
    public VerificationStatus getStatus() {
        return this.status;
    }

    @Generated
    public String getDocumentUrl() {
        return this.documentUrl;
    }

    @Generated
    public String getIdentityNumber() {
        return this.identityNumber;
    }

    @Generated
    public String getBankAccountNumber() {
        return this.bankAccountNumber;
    }

    @Generated
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public Long getVerifiedBy() {
        return this.verifiedBy;
    }

    @Generated
    public String getRejectionReason() {
        return this.rejectionReason;
    }

    @Generated
    public LocalDateTime getSubmittedAt() {
        return this.submittedAt;
    }

    @Generated
    public LocalDateTime getVerifiedAt() {
        return this.verifiedAt;
    }

    @Generated
    public LocalDateTime getExpiresAt() {
        return this.expiresAt;
    }

    @Generated
    public Integer getVerificationAttempts() {
        return this.verificationAttempts;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Generated
    public void setVerificationType(VerificationType verificationType) {
        this.verificationType = verificationType;
    }

    @Generated
    public void setStatus(VerificationStatus status) {
        this.status = status;
    }

    @Generated
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    @Generated
    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @Generated
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @Generated
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setVerifiedBy(Long verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    @Generated
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Generated
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    @Generated
    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    @Generated
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Generated
    public void setVerificationAttempts(Integer verificationAttempts) {
        this.verificationAttempts = verificationAttempts;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Verification)) {
            return false;
        }
        Verification other = (Verification)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !(this$userId).equals(other$userId)) {
            return false;
        }
        Long this$verifiedBy = this.getVerifiedBy();
        Long other$verifiedBy = other.getVerifiedBy();
        if (this$verifiedBy == null ? other$verifiedBy != null : !(this$verifiedBy).equals(other$verifiedBy)) {
            return false;
        }
        Integer this$verificationAttempts = this.getVerificationAttempts();
        Integer other$verificationAttempts = other.getVerificationAttempts();
        if (this$verificationAttempts == null ? other$verificationAttempts != null : !(this$verificationAttempts).equals(other$verificationAttempts)) {
            return false;
        }
        String this$userType = this.getUserType();
        String other$userType = other.getUserType();
        if (this$userType == null ? other$userType != null : !this$userType.equals(other$userType)) {
            return false;
        }
        VerificationType this$verificationType = this.getVerificationType();
        VerificationType other$verificationType = other.getVerificationType();
        if (this$verificationType == null ? other$verificationType != null : !((Object)(this$verificationType)).equals(other$verificationType)) {
            return false;
        }
        VerificationStatus this$status = this.getStatus();
        VerificationStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)(this$status)).equals(other$status)) {
            return false;
        }
        String this$documentUrl = this.getDocumentUrl();
        String other$documentUrl = other.getDocumentUrl();
        if (this$documentUrl == null ? other$documentUrl != null : !this$documentUrl.equals(other$documentUrl)) {
            return false;
        }
        String this$identityNumber = this.getIdentityNumber();
        String other$identityNumber = other.getIdentityNumber();
        if (this$identityNumber == null ? other$identityNumber != null : !this$identityNumber.equals(other$identityNumber)) {
            return false;
        }
        String this$bankAccountNumber = this.getBankAccountNumber();
        String other$bankAccountNumber = other.getBankAccountNumber();
        if (this$bankAccountNumber == null ? other$bankAccountNumber != null : !this$bankAccountNumber.equals(other$bankAccountNumber)) {
            return false;
        }
        String this$phoneNumber = this.getPhoneNumber();
        String other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$rejectionReason = this.getRejectionReason();
        String other$rejectionReason = other.getRejectionReason();
        if (this$rejectionReason == null ? other$rejectionReason != null : !this$rejectionReason.equals(other$rejectionReason)) {
            return false;
        }
        LocalDateTime this$submittedAt = this.getSubmittedAt();
        LocalDateTime other$submittedAt = other.getSubmittedAt();
        if (this$submittedAt == null ? other$submittedAt != null : !(this$submittedAt).equals(other$submittedAt)) {
            return false;
        }
        LocalDateTime this$verifiedAt = this.getVerifiedAt();
        LocalDateTime other$verifiedAt = other.getVerifiedAt();
        if (this$verifiedAt == null ? other$verifiedAt != null : !(this$verifiedAt).equals(other$verifiedAt)) {
            return false;
        }
        LocalDateTime this$expiresAt = this.getExpiresAt();
        LocalDateTime other$expiresAt = other.getExpiresAt();
        return !(this$expiresAt == null ? other$expiresAt != null : !(this$expiresAt).equals(other$expiresAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Verification;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Long $verifiedBy = this.getVerifiedBy();
        result = result * 59 + ($verifiedBy == null ? 43 : ((Object)$verifiedBy).hashCode());
        Integer $verificationAttempts = this.getVerificationAttempts();
        result = result * 59 + ($verificationAttempts == null ? 43 : ((Object)$verificationAttempts).hashCode());
        String $userType = this.getUserType();
        result = result * 59 + ($userType == null ? 43 : $userType.hashCode());
        VerificationType $verificationType = this.getVerificationType();
        result = result * 59 + ($verificationType == null ? 43 : ((Object)((Object)$verificationType)).hashCode());
        VerificationStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        String $documentUrl = this.getDocumentUrl();
        result = result * 59 + ($documentUrl == null ? 43 : $documentUrl.hashCode());
        String $identityNumber = this.getIdentityNumber();
        result = result * 59 + ($identityNumber == null ? 43 : $identityNumber.hashCode());
        String $bankAccountNumber = this.getBankAccountNumber();
        result = result * 59 + ($bankAccountNumber == null ? 43 : $bankAccountNumber.hashCode());
        String $phoneNumber = this.getPhoneNumber();
        result = result * 59 + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $rejectionReason = this.getRejectionReason();
        result = result * 59 + ($rejectionReason == null ? 43 : $rejectionReason.hashCode());
        LocalDateTime $submittedAt = this.getSubmittedAt();
        result = result * 59 + ($submittedAt == null ? 43 : ((Object)$submittedAt).hashCode());
        LocalDateTime $verifiedAt = this.getVerifiedAt();
        result = result * 59 + ($verifiedAt == null ? 43 : ((Object)$verifiedAt).hashCode());
        LocalDateTime $expiresAt = this.getExpiresAt();
        result = result * 59 + ($expiresAt == null ? 43 : ((Object)$expiresAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Verification(id=" + this.getId() + ", userId=" + this.getUserId() + ", userType=" + this.getUserType() + ", verificationType=" + String.valueOf(this.getVerificationType()) + ", status=" + String.valueOf(this.getStatus()) + ", documentUrl=" + this.getDocumentUrl() + ", identityNumber=" + this.getIdentityNumber() + ", bankAccountNumber=" + this.getBankAccountNumber() + ", phoneNumber=" + this.getPhoneNumber() + ", email=" + this.getEmail() + ", verifiedBy=" + this.getVerifiedBy() + ", rejectionReason=" + this.getRejectionReason() + ", submittedAt=" + String.valueOf(this.getSubmittedAt()) + ", verifiedAt=" + String.valueOf(this.getVerifiedAt()) + ", expiresAt=" + String.valueOf(this.getExpiresAt()) + ", verificationAttempts=" + this.getVerificationAttempts() + ")";
    }

    @Generated
    public Verification() {
    }

    @Generated
    public Verification(Long id, Long userId, String userType, VerificationType verificationType, VerificationStatus status, String documentUrl, String identityNumber, String bankAccountNumber, String phoneNumber, String email, Long verifiedBy, String rejectionReason, LocalDateTime submittedAt, LocalDateTime verifiedAt, LocalDateTime expiresAt, Integer verificationAttempts) {
        this.id = id;
        this.userId = userId;
        this.userType = userType;
        this.verificationType = verificationType;
        this.status = status;
        this.documentUrl = documentUrl;
        this.identityNumber = identityNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.verifiedBy = verifiedBy;
        this.rejectionReason = rejectionReason;
        this.submittedAt = submittedAt;
        this.verifiedAt = verifiedAt;
        this.expiresAt = expiresAt;
        this.verificationAttempts = verificationAttempts;
    }

    public static enum VerificationStatus {
        PENDING,
        APPROVED,
        REJECTED,
        EXPIRED;

    }

    public static enum VerificationType {
        IDENTITY,
        BANK,
        PHONE,
        EMAIL,
        DOCUMENT;

    }
}
