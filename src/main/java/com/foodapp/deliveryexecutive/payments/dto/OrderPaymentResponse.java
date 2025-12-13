/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.payments.dto;

import com.foodapp.deliveryexecutive.payments.entity.OrderPayment;
import java.time.LocalDateTime;
import lombok.Generated;

public class OrderPaymentResponse {
    private Long id;
    private Long orderId;
    private Long userId;
    private Double amount;
    private String currency;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private OrderPayment.PaymentStatus status;
    private String method;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;
    private String failureReason;
    private String razorpayKey;
    private String customerEmail;
    private String customerPhone;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getOrderId() {
        return this.orderId;
    }

    @Generated
    public Long getUserId() {
        return this.userId;
    }

    @Generated
    public Double getAmount() {
        return this.amount;
    }

    @Generated
    public String getCurrency() {
        return this.currency;
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
    public OrderPayment.PaymentStatus getStatus() {
        return this.status;
    }

    @Generated
    public String getMethod() {
        return this.method;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getPaidAt() {
        return this.paidAt;
    }

    @Generated
    public String getFailureReason() {
        return this.failureReason;
    }

    @Generated
    public String getRazorpayKey() {
        return this.razorpayKey;
    }

    @Generated
    public String getCustomerEmail() {
        return this.customerEmail;
    }

    @Generated
    public String getCustomerPhone() {
        return this.customerPhone;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Generated
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Generated
    public void setCurrency(String currency) {
        this.currency = currency;
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
    public void setStatus(OrderPayment.PaymentStatus status) {
        this.status = status;
    }

    @Generated
    public void setMethod(String method) {
        this.method = method;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    @Generated
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    @Generated
    public void setRazorpayKey(String razorpayKey) {
        this.razorpayKey = razorpayKey;
    }

    @Generated
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Generated
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OrderPaymentResponse)) {
            return false;
        }
        OrderPaymentResponse other = (OrderPaymentResponse)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$orderId = this.getOrderId();
        Long other$orderId = other.getOrderId();
        if (this$orderId == null ? other$orderId != null : !((Object)this$orderId).equals(other$orderId)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !((Object)this$userId).equals(other$userId)) {
            return false;
        }
        Double this$amount = this.getAmount();
        Double other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !((Object)this$amount).equals(other$amount)) {
            return false;
        }
        String this$currency = this.getCurrency();
        String other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) {
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
        OrderPayment.PaymentStatus this$status = this.getStatus();
        OrderPayment.PaymentStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)((Object)this$status)).equals((Object)other$status)) {
            return false;
        }
        String this$method = this.getMethod();
        String other$method = other.getMethod();
        if (this$method == null ? other$method != null : !this$method.equals(other$method)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !((Object)this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$paidAt = this.getPaidAt();
        LocalDateTime other$paidAt = other.getPaidAt();
        if (this$paidAt == null ? other$paidAt != null : !((Object)this$paidAt).equals(other$paidAt)) {
            return false;
        }
        String this$failureReason = this.getFailureReason();
        String other$failureReason = other.getFailureReason();
        if (this$failureReason == null ? other$failureReason != null : !this$failureReason.equals(other$failureReason)) {
            return false;
        }
        String this$razorpayKey = this.getRazorpayKey();
        String other$razorpayKey = other.getRazorpayKey();
        if (this$razorpayKey == null ? other$razorpayKey != null : !this$razorpayKey.equals(other$razorpayKey)) {
            return false;
        }
        String this$customerEmail = this.getCustomerEmail();
        String other$customerEmail = other.getCustomerEmail();
        if (this$customerEmail == null ? other$customerEmail != null : !this$customerEmail.equals(other$customerEmail)) {
            return false;
        }
        String this$customerPhone = this.getCustomerPhone();
        String other$customerPhone = other.getCustomerPhone();
        return !(this$customerPhone == null ? other$customerPhone != null : !this$customerPhone.equals(other$customerPhone));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof OrderPaymentResponse;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $orderId = this.getOrderId();
        result = result * 59 + ($orderId == null ? 43 : ((Object)$orderId).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Double $amount = this.getAmount();
        result = result * 59 + ($amount == null ? 43 : ((Object)$amount).hashCode());
        String $currency = this.getCurrency();
        result = result * 59 + ($currency == null ? 43 : $currency.hashCode());
        String $razorpayOrderId = this.getRazorpayOrderId();
        result = result * 59 + ($razorpayOrderId == null ? 43 : $razorpayOrderId.hashCode());
        String $razorpayPaymentId = this.getRazorpayPaymentId();
        result = result * 59 + ($razorpayPaymentId == null ? 43 : $razorpayPaymentId.hashCode());
        OrderPayment.PaymentStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        String $method = this.getMethod();
        result = result * 59 + ($method == null ? 43 : $method.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $paidAt = this.getPaidAt();
        result = result * 59 + ($paidAt == null ? 43 : ((Object)$paidAt).hashCode());
        String $failureReason = this.getFailureReason();
        result = result * 59 + ($failureReason == null ? 43 : $failureReason.hashCode());
        String $razorpayKey = this.getRazorpayKey();
        result = result * 59 + ($razorpayKey == null ? 43 : $razorpayKey.hashCode());
        String $customerEmail = this.getCustomerEmail();
        result = result * 59 + ($customerEmail == null ? 43 : $customerEmail.hashCode());
        String $customerPhone = this.getCustomerPhone();
        result = result * 59 + ($customerPhone == null ? 43 : $customerPhone.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "OrderPaymentResponse(id=" + this.getId() + ", orderId=" + this.getOrderId() + ", userId=" + this.getUserId() + ", amount=" + this.getAmount() + ", currency=" + this.getCurrency() + ", razorpayOrderId=" + this.getRazorpayOrderId() + ", razorpayPaymentId=" + this.getRazorpayPaymentId() + ", status=" + String.valueOf((Object)this.getStatus()) + ", method=" + this.getMethod() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", paidAt=" + String.valueOf(this.getPaidAt()) + ", failureReason=" + this.getFailureReason() + ", razorpayKey=" + this.getRazorpayKey() + ", customerEmail=" + this.getCustomerEmail() + ", customerPhone=" + this.getCustomerPhone() + ")";
    }

    @Generated
    public OrderPaymentResponse() {
    }

    @Generated
    public OrderPaymentResponse(Long id, Long orderId, Long userId, Double amount, String currency, String razorpayOrderId, String razorpayPaymentId, OrderPayment.PaymentStatus status, String method, LocalDateTime createdAt, LocalDateTime paidAt, String failureReason, String razorpayKey, String customerEmail, String customerPhone) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.razorpayOrderId = razorpayOrderId;
        this.razorpayPaymentId = razorpayPaymentId;
        this.status = status;
        this.method = method;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
        this.failureReason = failureReason;
        this.razorpayKey = razorpayKey;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }
}
