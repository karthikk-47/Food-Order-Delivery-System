/*
 * Decompiled with CFR 0.152.
 */
package com.foodapp.deliveryexecutive.executive.dto;

public class DeliveryProofDTO {
    private Long orderId;
    private String proofType;
    private String proofValue;
    private String customerOtp;
    private String status;
    private String message;

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProofType() {
        return this.proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public String getProofValue() {
        return this.proofValue;
    }

    public void setProofValue(String proofValue) {
        this.proofValue = proofValue;
    }

    public String getCustomerOtp() {
        return this.customerOtp;
    }

    public void setCustomerOtp(String customerOtp) {
        this.customerOtp = customerOtp;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
