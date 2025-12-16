package com.foodapp.deliveryexecutive.payments.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Generated;

public class VerifyPaymentRequest {
    @NotBlank(message="Razorpay order ID is required")
    private @NotBlank(message="Razorpay order ID is required") String razorpayOrderId;
    @NotBlank(message="Razorpay payment ID is required")
    private @NotBlank(message="Razorpay payment ID is required") String razorpayPaymentId;
    @NotBlank(message="Razorpay signature is required")
    private @NotBlank(message="Razorpay signature is required") String razorpaySignature;

    @Generated
    public VerifyPaymentRequest() {
    }

    @Generated
    public String getRazorpayOrderId() {
        return this.razorpayOrderId;
    }

    @Generated
    public String getRazorpayPaymentId() {
        return this.razorpayPaymentId;
    }

    @Generated
    public String getRazorpaySignature() {
        return this.razorpaySignature;
    }

    @Generated
    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    @Generated
    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    @Generated
    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VerifyPaymentRequest)) {
            return false;
        }
        VerifyPaymentRequest other = (VerifyPaymentRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$razorpayOrderId = this.getRazorpayOrderId();
        String other$razorpayOrderId = other.getRazorpayOrderId();
        if (this$razorpayOrderId == null ? other$razorpayOrderId != null : !this$razorpayOrderId.equals(other$razorpayOrderId)) {
            return false;
        }
        String this$razorpayPaymentId = this.getRazorpayPaymentId();
        String other$razorpayPaymentId = other.getRazorpayPaymentId();
        if (this$razorpayPaymentId == null ? other$razorpayPaymentId != null : !this$razorpayPaymentId.equals(other$razorpayPaymentId)) {
            return false;
        }
        String this$razorpaySignature = this.getRazorpaySignature();
        String other$razorpaySignature = other.getRazorpaySignature();
        return !(this$razorpaySignature == null ? other$razorpaySignature != null : !this$razorpaySignature.equals(other$razorpaySignature));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof VerifyPaymentRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $razorpayOrderId = this.getRazorpayOrderId();
        result = result * 59 + ($razorpayOrderId == null ? 43 : $razorpayOrderId.hashCode());
        String $razorpayPaymentId = this.getRazorpayPaymentId();
        result = result * 59 + ($razorpayPaymentId == null ? 43 : $razorpayPaymentId.hashCode());
        String $razorpaySignature = this.getRazorpaySignature();
        result = result * 59 + ($razorpaySignature == null ? 43 : $razorpaySignature.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "VerifyPaymentRequest(razorpayOrderId=" + this.getRazorpayOrderId() + ", razorpayPaymentId=" + this.getRazorpayPaymentId() + ", razorpaySignature=" + this.getRazorpaySignature() + ")";
    }
}
