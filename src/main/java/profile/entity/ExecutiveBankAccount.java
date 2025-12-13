/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.PrePersist
 *  jakarta.persistence.PreUpdate
 *  jakarta.persistence.Table
 */
package com.foodapp.deliveryexecutive.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="executive_bank_accounts")
public class ExecutiveBankAccount {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="executive_id", nullable=false)
    private Long executiveId;
    @Column(name="account_holder_name", nullable=false)
    private String accountHolderName;
    @Column(name="account_number", nullable=false)
    private String accountNumber;
    @Column(name="ifsc_code", nullable=false)
    private String ifscCode;
    @Column(name="bank_name", nullable=false)
    private String bankName;
    @Column(name="branch_name")
    private String branchName;
    @Column(name="account_type", nullable=false)
    private String accountType;
    @Column(name="is_primary")
    private boolean isPrimary;
    @Column(name="is_verified")
    private boolean isVerified;
    @Column(name="razorpay_contact_id")
    private String razorpayContactId;
    @Column(name="razorpay_fund_account_id")
    private String razorpayFundAccountId;
    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExecutiveId() {
        return this.executiveId;
    }

    public void setExecutiveId(Long executiveId) {
        this.executiveId = executiveId;
    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public boolean isPrimary() {
        return this.isPrimary;
    }

    public void setPrimary(boolean primary) {
        this.isPrimary = primary;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public void setVerified(boolean verified) {
        this.isVerified = verified;
    }

    public String getRazorpayContactId() {
        return this.razorpayContactId;
    }

    public void setRazorpayContactId(String razorpayContactId) {
        this.razorpayContactId = razorpayContactId;
    }

    public String getRazorpayFundAccountId() {
        return this.razorpayFundAccountId;
    }

    public void setRazorpayFundAccountId(String razorpayFundAccountId) {
        this.razorpayFundAccountId = razorpayFundAccountId;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
