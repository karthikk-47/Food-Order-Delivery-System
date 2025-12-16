/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.DecimalMin
 *  jakarta.validation.constraints.Email
 *  jakarta.validation.constraints.NotNull
 *  jakarta.validation.constraints.Pattern
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.payments.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Generated;

public class CreateOrderPaymentRequest {
    @NotNull(message="Order ID is required")
    private @NotNull(message="Order ID is required") Long orderId;
    @NotNull(message="User ID is required")
    private @NotNull(message="User ID is required") Long userId;
    @NotNull(message="Amount is required")
    @DecimalMin(value="1.0", message="Amount must be at least 1.0")
    private @NotNull(message="Amount is required") @DecimalMin(value="1.0", message="Amount must be at least 1.0") Double amount;
    @Email(message="Invalid email format")
    private @Email(message="Invalid email format") String customerEmail;
    @Pattern(regexp="^[0-9]{10}$", message="Phone number must be 10 digits")
    private @Pattern(regexp="^[0-9]{10}$", message="Phone number must be 10 digits") String customerPhone;
    private String notes;

    @Generated
    public CreateOrderPaymentRequest() {
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
    public String getCustomerEmail() {
        return this.customerEmail;
    }

    @Generated
    public String getCustomerPhone() {
        return this.customerPhone;
    }

    @Generated
    public String getNotes() {
        return this.notes;
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
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Generated
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Generated
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreateOrderPaymentRequest)) {
            return false;
        }
        CreateOrderPaymentRequest other = (CreateOrderPaymentRequest)o;
        if (!other.canEqual(this)) {
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
        String this$notes = this.getNotes();
        String other$notes = other.getNotes();
        return !(this$notes == null ? other$notes != null : !this$notes.equals(other$notes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CreateOrderPaymentRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $orderId = this.getOrderId();
        result = result * 59 + ($orderId == null ? 43 : ((Object)$orderId).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Double $amount = this.getAmount();
        result = result * 59 + ($amount == null ? 43 : ((Object)$amount).hashCode());
        String $customerEmail = this.getCustomerEmail();
        result = result * 59 + ($customerEmail == null ? 43 : $customerEmail.hashCode());
        String $customerPhone = this.getCustomerPhone();
        result = result * 59 + ($customerPhone == null ? 43 : $customerPhone.hashCode());
        String $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : $notes.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "CreateOrderPaymentRequest(orderId=" + this.getOrderId() + ", userId=" + this.getUserId() + ", amount=" + this.getAmount() + ", customerEmail=" + this.getCustomerEmail() + ", customerPhone=" + this.getCustomerPhone() + ", notes=" + this.getNotes() + ")";
    }
}
