/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.Min
 *  jakarta.validation.constraints.NotNull
 */
package com.foodapp.deliveryexecutive.payments.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WithdrawRequest {
    @NotNull(message="Customer ID is required")
    private @NotNull(message="Customer ID is required") Long customerId;
    @NotNull(message="Amount is required")
    @Min(value=100L, message="Minimum withdrawal amount is 100")
    private @NotNull(message="Amount is required") @Min(value=100L, message="Minimum withdrawal amount is 100") Double amount;
    @NotNull(message="Fund account ID is required")
    private @NotNull(message="Fund account ID is required") String fundAccountId;
    private String purpose;
    private String narration;
    private String referenceId;

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFundAccountId() {
        return this.fundAccountId;
    }

    public void setFundAccountId(String fundAccountId) {
        this.fundAccountId = fundAccountId;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getNarration() {
        return this.narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
