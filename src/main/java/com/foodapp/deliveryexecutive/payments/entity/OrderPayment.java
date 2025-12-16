/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.PrePersist
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.payments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="order_payments")
public class OrderPayment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private Long orderId;
    @Column(nullable=false)
    private Long userId;
    @Column(nullable=false)
    private Double amount;
    @Column(nullable=false)
    private String currency = "INR";
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false)
    private PaymentStatus status;
    @Enumerated(value=EnumType.STRING)
    private PaymentMethod method;
    private String paymentDescription;
    private String customerEmail;
    private String customerPhone;
    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;
    private LocalDateTime failedAt;
    private String failureReason;
    private String errorCode;
    private String errorDescription;
    private Boolean isRefunded = false;
    private String refundId;
    private Double refundAmount;
    private LocalDateTime refundedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = PaymentStatus.CREATED;
        }
    }

    @Generated
    public OrderPayment() {
    }

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
    public String getRazorpaySignature() {
        return this.razorpaySignature;
    }

    @Generated
    public PaymentStatus getStatus() {
        return this.status;
    }

    @Generated
    public PaymentMethod getMethod() {
        return this.method;
    }

    @Generated
    public String getPaymentDescription() {
        return this.paymentDescription;
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
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getPaidAt() {
        return this.paidAt;
    }

    @Generated
    public LocalDateTime getFailedAt() {
        return this.failedAt;
    }

    @Generated
    public String getFailureReason() {
        return this.failureReason;
    }

    @Generated
    public String getErrorCode() {
        return this.errorCode;
    }

    @Generated
    public String getErrorDescription() {
        return this.errorDescription;
    }

    @Generated
    public Boolean getIsRefunded() {
        return this.isRefunded;
    }

    @Generated
    public String getRefundId() {
        return this.refundId;
    }

    @Generated
    public Double getRefundAmount() {
        return this.refundAmount;
    }

    @Generated
    public LocalDateTime getRefundedAt() {
        return this.refundedAt;
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
    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    @Generated
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Generated
    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    @Generated
    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
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
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    @Generated
    public void setFailedAt(LocalDateTime failedAt) {
        this.failedAt = failedAt;
    }

    @Generated
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    @Generated
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Generated
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Generated
    public void setIsRefunded(Boolean isRefunded) {
        this.isRefunded = isRefunded;
    }

    @Generated
    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    @Generated
    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Generated
    public void setRefundedAt(LocalDateTime refundedAt) {
        this.refundedAt = refundedAt;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OrderPayment)) {
            return false;
        }
        OrderPayment other = (OrderPayment)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Long this$orderId = this.getOrderId();
        Long other$orderId = other.getOrderId();
        if (this$orderId == null ? other$orderId != null : !(this$orderId).equals(other$orderId)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !(this$userId).equals(other$userId)) {
            return false;
        }
        Double this$amount = this.getAmount();
        Double other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !(this$amount).equals(other$amount)) {
            return false;
        }
        Boolean this$isRefunded = this.getIsRefunded();
        Boolean other$isRefunded = other.getIsRefunded();
        if (this$isRefunded == null ? other$isRefunded != null : !(this$isRefunded).equals(other$isRefunded)) {
            return false;
        }
        Double this$refundAmount = this.getRefundAmount();
        Double other$refundAmount = other.getRefundAmount();
        if (this$refundAmount == null ? other$refundAmount != null : !(this$refundAmount).equals(other$refundAmount)) {
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
        String this$razorpaySignature = this.getRazorpaySignature();
        String other$razorpaySignature = other.getRazorpaySignature();
        if (this$razorpaySignature == null ? other$razorpaySignature != null : !this$razorpaySignature.equals(other$razorpaySignature)) {
            return false;
        }
        PaymentStatus this$status = this.getStatus();
        PaymentStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)(this$status)).equals(other$status)) {
            return false;
        }
        PaymentMethod this$method = this.getMethod();
        PaymentMethod other$method = other.getMethod();
        if (this$method == null ? other$method != null : !((Object)(this$method)).equals(other$method)) {
            return false;
        }
        String this$paymentDescription = this.getPaymentDescription();
        String other$paymentDescription = other.getPaymentDescription();
        if (this$paymentDescription == null ? other$paymentDescription != null : !this$paymentDescription.equals(other$paymentDescription)) {
            return false;
        }
        String this$customerEmail = this.getCustomerEmail();
        String other$customerEmail = other.getCustomerEmail();
        if (this$customerEmail == null ? other$customerEmail != null : !this$customerEmail.equals(other$customerEmail)) {
            return false;
        }
        String this$customerPhone = this.getCustomerPhone();
        String other$customerPhone = other.getCustomerPhone();
        if (this$customerPhone == null ? other$customerPhone != null : !this$customerPhone.equals(other$customerPhone)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !(this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$paidAt = this.getPaidAt();
        LocalDateTime other$paidAt = other.getPaidAt();
        if (this$paidAt == null ? other$paidAt != null : !(this$paidAt).equals(other$paidAt)) {
            return false;
        }
        LocalDateTime this$failedAt = this.getFailedAt();
        LocalDateTime other$failedAt = other.getFailedAt();
        if (this$failedAt == null ? other$failedAt != null : !(this$failedAt).equals(other$failedAt)) {
            return false;
        }
        String this$failureReason = this.getFailureReason();
        String other$failureReason = other.getFailureReason();
        if (this$failureReason == null ? other$failureReason != null : !this$failureReason.equals(other$failureReason)) {
            return false;
        }
        String this$errorCode = this.getErrorCode();
        String other$errorCode = other.getErrorCode();
        if (this$errorCode == null ? other$errorCode != null : !this$errorCode.equals(other$errorCode)) {
            return false;
        }
        String this$errorDescription = this.getErrorDescription();
        String other$errorDescription = other.getErrorDescription();
        if (this$errorDescription == null ? other$errorDescription != null : !this$errorDescription.equals(other$errorDescription)) {
            return false;
        }
        String this$refundId = this.getRefundId();
        String other$refundId = other.getRefundId();
        if (this$refundId == null ? other$refundId != null : !this$refundId.equals(other$refundId)) {
            return false;
        }
        LocalDateTime this$refundedAt = this.getRefundedAt();
        LocalDateTime other$refundedAt = other.getRefundedAt();
        return !(this$refundedAt == null ? other$refundedAt != null : !(this$refundedAt).equals(other$refundedAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof OrderPayment;
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
        Boolean $isRefunded = this.getIsRefunded();
        result = result * 59 + ($isRefunded == null ? 43 : ((Object)$isRefunded).hashCode());
        Double $refundAmount = this.getRefundAmount();
        result = result * 59 + ($refundAmount == null ? 43 : ((Object)$refundAmount).hashCode());
        String $currency = this.getCurrency();
        result = result * 59 + ($currency == null ? 43 : $currency.hashCode());
        String $razorpayOrderId = this.getRazorpayOrderId();
        result = result * 59 + ($razorpayOrderId == null ? 43 : $razorpayOrderId.hashCode());
        String $razorpayPaymentId = this.getRazorpayPaymentId();
        result = result * 59 + ($razorpayPaymentId == null ? 43 : $razorpayPaymentId.hashCode());
        String $razorpaySignature = this.getRazorpaySignature();
        result = result * 59 + ($razorpaySignature == null ? 43 : $razorpaySignature.hashCode());
        PaymentStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        PaymentMethod $method = this.getMethod();
        result = result * 59 + ($method == null ? 43 : ((Object)((Object)$method)).hashCode());
        String $paymentDescription = this.getPaymentDescription();
        result = result * 59 + ($paymentDescription == null ? 43 : $paymentDescription.hashCode());
        String $customerEmail = this.getCustomerEmail();
        result = result * 59 + ($customerEmail == null ? 43 : $customerEmail.hashCode());
        String $customerPhone = this.getCustomerPhone();
        result = result * 59 + ($customerPhone == null ? 43 : $customerPhone.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $paidAt = this.getPaidAt();
        result = result * 59 + ($paidAt == null ? 43 : ((Object)$paidAt).hashCode());
        LocalDateTime $failedAt = this.getFailedAt();
        result = result * 59 + ($failedAt == null ? 43 : ((Object)$failedAt).hashCode());
        String $failureReason = this.getFailureReason();
        result = result * 59 + ($failureReason == null ? 43 : $failureReason.hashCode());
        String $errorCode = this.getErrorCode();
        result = result * 59 + ($errorCode == null ? 43 : $errorCode.hashCode());
        String $errorDescription = this.getErrorDescription();
        result = result * 59 + ($errorDescription == null ? 43 : $errorDescription.hashCode());
        String $refundId = this.getRefundId();
        result = result * 59 + ($refundId == null ? 43 : $refundId.hashCode());
        LocalDateTime $refundedAt = this.getRefundedAt();
        result = result * 59 + ($refundedAt == null ? 43 : ((Object)$refundedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "OrderPayment(id=" + this.getId() + ", orderId=" + this.getOrderId() + ", userId=" + this.getUserId() + ", amount=" + this.getAmount() + ", currency=" + this.getCurrency() + ", razorpayOrderId=" + this.getRazorpayOrderId() + ", razorpayPaymentId=" + this.getRazorpayPaymentId() + ", razorpaySignature=" + this.getRazorpaySignature() + ", status=" + String.valueOf(this.getStatus()) + ", method=" + String.valueOf(this.getMethod()) + ", paymentDescription=" + this.getPaymentDescription() + ", customerEmail=" + this.getCustomerEmail() + ", customerPhone=" + this.getCustomerPhone() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", paidAt=" + String.valueOf(this.getPaidAt()) + ", failedAt=" + String.valueOf(this.getFailedAt()) + ", failureReason=" + this.getFailureReason() + ", errorCode=" + this.getErrorCode() + ", errorDescription=" + this.getErrorDescription() + ", isRefunded=" + this.getIsRefunded() + ", refundId=" + this.getRefundId() + ", refundAmount=" + this.getRefundAmount() + ", refundedAt=" + String.valueOf(this.getRefundedAt()) + ")";
    }

    public static enum PaymentStatus {
        CREATED,
        PENDING,
        PROCESSING,
        SUCCESS,
        FAILED,
        REFUNDED,
        CANCELLED;

    }

    public static enum PaymentMethod {
        UPI,
        CARD,
        NETBANKING,
        WALLET,
        COD;

    }
}
