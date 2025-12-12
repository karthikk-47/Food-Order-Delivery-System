/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.foodapp.deliveryexecutive.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodapp.deliveryexecutive.payments.dto.RazorpayWebhookEvent;

public static class RazorpayWebhookEvent.PayoutPayload.PayoutEntity {
    private String id;
    private String entity;
    @JsonProperty(value="fund_account_id")
    private String fundAccountId;
    private Integer amount;
    private String currency;
    private String status;
    private String utr;
    private String mode;
    private String purpose;
    @JsonProperty(value="reference_id")
    private String referenceId;
    private String narration;
    @JsonProperty(value="status_details")
    private RazorpayWebhookEvent.PayoutPayload.StatusDetails statusDetails;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return this.entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getFundAccountId() {
        return this.fundAccountId;
    }

    public void setFundAccountId(String fundAccountId) {
        this.fundAccountId = fundAccountId;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUtr() {
        return this.utr;
    }

    public void setUtr(String utr) {
        this.utr = utr;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getNarration() {
        return this.narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public RazorpayWebhookEvent.PayoutPayload.StatusDetails getStatusDetails() {
        return this.statusDetails;
    }

    public void setStatusDetails(RazorpayWebhookEvent.PayoutPayload.StatusDetails statusDetails) {
        this.statusDetails = statusDetails;
    }
}
