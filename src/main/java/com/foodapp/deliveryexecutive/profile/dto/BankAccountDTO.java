package com.foodapp.deliveryexecutive.profile.dto;

public class BankAccountDTO {
    private Long id;
    private Long executiveId;
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private String bankName;
    private String branchName;
    private String accountType;
    private boolean isPrimary;
    private boolean isVerified;
    private String razorpayContactId;
    private String razorpayFundAccountId;

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
}
