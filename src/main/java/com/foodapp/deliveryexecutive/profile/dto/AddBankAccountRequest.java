package com.foodapp.deliveryexecutive.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddBankAccountRequest {
    @NotBlank(message="Account holder name is required")
    private @NotBlank(message="Account holder name is required") String accountHolderName;
    @NotBlank(message="Account number is required")
    @Pattern(regexp="^[0-9]{9,18}$", message="Invalid account number")
    private @NotBlank(message="Account number is required") @Pattern(regexp="^[0-9]{9,18}$", message="Invalid account number") String accountNumber;
    @NotBlank(message="IFSC code is required")
    @Pattern(regexp="^[A-Z]{4}0[A-Z0-9]{6}$", message="Invalid IFSC code")
    private @NotBlank(message="IFSC code is required") @Pattern(regexp="^[A-Z]{4}0[A-Z0-9]{6}$", message="Invalid IFSC code") String ifscCode;
    @NotBlank(message="Bank name is required")
    private @NotBlank(message="Bank name is required") String bankName;
    private String branchName;
    @NotBlank(message="Account type is required")
    private @NotBlank(message="Account type is required") String accountType;
    private boolean isPrimary;

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
}
