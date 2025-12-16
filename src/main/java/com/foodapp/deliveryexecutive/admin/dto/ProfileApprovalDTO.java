package com.foodapp.deliveryexecutive.admin.dto;

import java.time.LocalDateTime;
import lombok.Generated;

public class ProfileApprovalDTO {
    private Long id;
    private String name;
    private String mobile;
    private String userType;
    private String approvalStatus;
    private String rejectionReason;
    private LocalDateTime registeredAt;
    private LocalDateTime approvedAt;
    private Long approvedBy;
    private String address;
    private String aadharNo;
    private String licenseNo;
    private String email;
    private boolean documentsVerified;
    private int pendingVerifications;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getMobile() {
        return this.mobile;
    }

    @Generated
    public String getUserType() {
        return this.userType;
    }

    @Generated
    public String getApprovalStatus() {
        return this.approvalStatus;
    }

    @Generated
    public String getRejectionReason() {
        return this.rejectionReason;
    }

    @Generated
    public LocalDateTime getRegisteredAt() {
        return this.registeredAt;
    }

    @Generated
    public LocalDateTime getApprovedAt() {
        return this.approvedAt;
    }

    @Generated
    public Long getApprovedBy() {
        return this.approvedBy;
    }

    @Generated
    public String getAddress() {
        return this.address;
    }

    @Generated
    public String getAadharNo() {
        return this.aadharNo;
    }

    @Generated
    public String getLicenseNo() {
        return this.licenseNo;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public boolean isDocumentsVerified() {
        return this.documentsVerified;
    }

    @Generated
    public int getPendingVerifications() {
        return this.pendingVerifications;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Generated
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Generated
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Generated
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Generated
    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    @Generated
    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    @Generated
    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Generated
    public void setAddress(String address) {
        this.address = address;
    }

    @Generated
    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    @Generated
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setDocumentsVerified(boolean documentsVerified) {
        this.documentsVerified = documentsVerified;
    }

    @Generated
    public void setPendingVerifications(int pendingVerifications) {
        this.pendingVerifications = pendingVerifications;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProfileApprovalDTO)) {
            return false;
        }
        ProfileApprovalDTO other = (ProfileApprovalDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.isDocumentsVerified() != other.isDocumentsVerified()) {
            return false;
        }
        if (this.getPendingVerifications() != other.getPendingVerifications()) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Long this$approvedBy = this.getApprovedBy();
        Long other$approvedBy = other.getApprovedBy();
        if (this$approvedBy == null ? other$approvedBy != null : !(this$approvedBy).equals(other$approvedBy)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$mobile = this.getMobile();
        String other$mobile = other.getMobile();
        if (this$mobile == null ? other$mobile != null : !this$mobile.equals(other$mobile)) {
            return false;
        }
        String this$userType = this.getUserType();
        String other$userType = other.getUserType();
        if (this$userType == null ? other$userType != null : !this$userType.equals(other$userType)) {
            return false;
        }
        String this$approvalStatus = this.getApprovalStatus();
        String other$approvalStatus = other.getApprovalStatus();
        if (this$approvalStatus == null ? other$approvalStatus != null : !this$approvalStatus.equals(other$approvalStatus)) {
            return false;
        }
        String this$rejectionReason = this.getRejectionReason();
        String other$rejectionReason = other.getRejectionReason();
        if (this$rejectionReason == null ? other$rejectionReason != null : !this$rejectionReason.equals(other$rejectionReason)) {
            return false;
        }
        LocalDateTime this$registeredAt = this.getRegisteredAt();
        LocalDateTime other$registeredAt = other.getRegisteredAt();
        if (this$registeredAt == null ? other$registeredAt != null : !(this$registeredAt).equals(other$registeredAt)) {
            return false;
        }
        LocalDateTime this$approvedAt = this.getApprovedAt();
        LocalDateTime other$approvedAt = other.getApprovedAt();
        if (this$approvedAt == null ? other$approvedAt != null : !(this$approvedAt).equals(other$approvedAt)) {
            return false;
        }
        String this$address = this.getAddress();
        String other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) {
            return false;
        }
        String this$aadharNo = this.getAadharNo();
        String other$aadharNo = other.getAadharNo();
        if (this$aadharNo == null ? other$aadharNo != null : !this$aadharNo.equals(other$aadharNo)) {
            return false;
        }
        String this$licenseNo = this.getLicenseNo();
        String other$licenseNo = other.getLicenseNo();
        if (this$licenseNo == null ? other$licenseNo != null : !this$licenseNo.equals(other$licenseNo)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        return !(this$email == null ? other$email != null : !this$email.equals(other$email));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof ProfileApprovalDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isDocumentsVerified() ? 79 : 97);
        result = result * 59 + this.getPendingVerifications();
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $approvedBy = this.getApprovedBy();
        result = result * 59 + ($approvedBy == null ? 43 : ((Object)$approvedBy).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $mobile = this.getMobile();
        result = result * 59 + ($mobile == null ? 43 : $mobile.hashCode());
        String $userType = this.getUserType();
        result = result * 59 + ($userType == null ? 43 : $userType.hashCode());
        String $approvalStatus = this.getApprovalStatus();
        result = result * 59 + ($approvalStatus == null ? 43 : $approvalStatus.hashCode());
        String $rejectionReason = this.getRejectionReason();
        result = result * 59 + ($rejectionReason == null ? 43 : $rejectionReason.hashCode());
        LocalDateTime $registeredAt = this.getRegisteredAt();
        result = result * 59 + ($registeredAt == null ? 43 : ((Object)$registeredAt).hashCode());
        LocalDateTime $approvedAt = this.getApprovedAt();
        result = result * 59 + ($approvedAt == null ? 43 : ((Object)$approvedAt).hashCode());
        String $address = this.getAddress();
        result = result * 59 + ($address == null ? 43 : $address.hashCode());
        String $aadharNo = this.getAadharNo();
        result = result * 59 + ($aadharNo == null ? 43 : $aadharNo.hashCode());
        String $licenseNo = this.getLicenseNo();
        result = result * 59 + ($licenseNo == null ? 43 : $licenseNo.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "ProfileApprovalDTO(id=" + this.getId() + ", name=" + this.getName() + ", mobile=" + this.getMobile() + ", userType=" + this.getUserType() + ", approvalStatus=" + this.getApprovalStatus() + ", rejectionReason=" + this.getRejectionReason() + ", registeredAt=" + String.valueOf(this.getRegisteredAt()) + ", approvedAt=" + String.valueOf(this.getApprovedAt()) + ", approvedBy=" + this.getApprovedBy() + ", address=" + this.getAddress() + ", aadharNo=" + this.getAadharNo() + ", licenseNo=" + this.getLicenseNo() + ", email=" + this.getEmail() + ", documentsVerified=" + this.isDocumentsVerified() + ", pendingVerifications=" + this.getPendingVerifications() + ")";
    }

    @Generated
    public ProfileApprovalDTO() {
    }

    @Generated
    public ProfileApprovalDTO(Long id, String name, String mobile, String userType, String approvalStatus, String rejectionReason, LocalDateTime registeredAt, LocalDateTime approvedAt, Long approvedBy, String address, String aadharNo, String licenseNo, String email, boolean documentsVerified, int pendingVerifications) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.userType = userType;
        this.approvalStatus = approvalStatus;
        this.rejectionReason = rejectionReason;
        this.registeredAt = registeredAt;
        this.approvedAt = approvedAt;
        this.approvedBy = approvedBy;
        this.address = address;
        this.aadharNo = aadharNo;
        this.licenseNo = licenseNo;
        this.email = email;
        this.documentsVerified = documentsVerified;
        this.pendingVerifications = pendingVerifications;
    }
}
