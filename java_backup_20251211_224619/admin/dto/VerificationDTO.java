/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.admin.dto;

import com.foodapp.deliveryexecutive.admin.entity.Verification;
import java.time.LocalDateTime;
import lombok.Generated;

public class VerificationDTO {
    private Long id;
    private Long userId;
    private String userType;
    private Verification.VerificationType verificationType;
    private Verification.VerificationStatus status;
    private String documentUrl;
    private String identityNumber;
    private String bankAccountNumber;
    private String phoneNumber;
    private String email;
    private String rejectionReason;
    private LocalDateTime submittedAt;
    private LocalDateTime verifiedAt;
    private LocalDateTime expiresAt;
    private Integer verificationAttempts;

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
    public Verification.VerificationType getVerificationType() {
        return this.verificationType;
    }

    @Generated
    public Verification.VerificationStatus getStatus() {
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
    public void setVerificationType(Verification.VerificationType verificationType) {
        this.verificationType = verificationType;
    }

    @Generated
    public void setStatus(Verification.VerificationStatus status) {
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
        if (!(o instanceof VerificationDTO)) {
            return false;
        }
        VerificationDTO other = (VerificationDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !((Object)this$userId).equals(other$userId)) {
            return false;
        }
        Integer this$verificationAttempts = this.getVerificationAttempts();
        Integer other$verificationAttempts = other.getVerificationAttempts();
        if (this$verificationAttempts == null ? other$verificationAttempts != null : !((Object)this$verificationAttempts).equals(other$verificationAttempts)) {
            return false;
        }
        String this$userType = this.getUserType();
        String other$userType = other.getUserType();
        if (this$userType == null ? other$userType != null : !this$userType.equals(other$userType)) {
            return false;
        }
        Verification.VerificationType this$verificationType = this.getVerificationType();
        Verification.VerificationType other$verificationType = other.getVerificationType();
        if (this$verificationType == null ? other$verificationType != null : !((Object)((Object)this$verificationType)).equals((Object)other$verificationType)) {
            return false;
        }
        Verification.VerificationStatus this$status = this.getStatus();
        Verification.VerificationStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)((Object)this$status)).equals((Object)other$status)) {
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
        if (this$submittedAt == null ? other$submittedAt != null : !((Object)this$submittedAt).equals(other$submittedAt)) {
            return false;
        }
        LocalDateTime this$verifiedAt = this.getVerifiedAt();
        LocalDateTime other$verifiedAt = other.getVerifiedAt();
        if (this$verifiedAt == null ? other$verifiedAt != null : !((Object)this$verifiedAt).equals(other$verifiedAt)) {
            return false;
        }
        LocalDateTime this$expiresAt = this.getExpiresAt();
        LocalDateTime other$expiresAt = other.getExpiresAt();
        return !(this$expiresAt == null ? other$expiresAt != null : !((Object)this$expiresAt).equals(other$expiresAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof VerificationDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Integer $verificationAttempts = this.getVerificationAttempts();
        result = result * 59 + ($verificationAttempts == null ? 43 : ((Object)$verificationAttempts).hashCode());
        String $userType = this.getUserType();
        result = result * 59 + ($userType == null ? 43 : $userType.hashCode());
        Verification.VerificationType $verificationType = this.getVerificationType();
        result = result * 59 + ($verificationType == null ? 43 : ((Object)((Object)$verificationType)).hashCode());
        Verification.VerificationStatus $status = this.getStatus();
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
        return "VerificationDTO(id=" + this.getId() + ", userId=" + this.getUserId() + ", userType=" + this.getUserType() + ", verificationType=" + String.valueOf((Object)this.getVerificationType()) + ", status=" + String.valueOf((Object)this.getStatus()) + ", documentUrl=" + this.getDocumentUrl() + ", identityNumber=" + this.getIdentityNumber() + ", bankAccountNumber=" + this.getBankAccountNumber() + ", phoneNumber=" + this.getPhoneNumber() + ", email=" + this.getEmail() + ", rejectionReason=" + this.getRejectionReason() + ", submittedAt=" + String.valueOf(this.getSubmittedAt()) + ", verifiedAt=" + String.valueOf(this.getVerifiedAt()) + ", expiresAt=" + String.valueOf(this.getExpiresAt()) + ", verificationAttempts=" + this.getVerificationAttempts() + ")";
    }

    @Generated
    public VerificationDTO() {
    }

    @Generated
    public VerificationDTO(Long id, Long userId, String userType, Verification.VerificationType verificationType, Verification.VerificationStatus status, String documentUrl, String identityNumber, String bankAccountNumber, String phoneNumber, String email, String rejectionReason, LocalDateTime submittedAt, LocalDateTime verifiedAt, LocalDateTime expiresAt, Integer verificationAttempts) {
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
        this.rejectionReason = rejectionReason;
        this.submittedAt = submittedAt;
        this.verifiedAt = verifiedAt;
        this.expiresAt = expiresAt;
        this.verificationAttempts = verificationAttempts;
    }
}
