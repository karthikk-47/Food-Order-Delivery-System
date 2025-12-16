package com.foodapp.deliveryexecutive.payments.entity;

import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String transactionId;
    private String paymentMethod;
    @Enumerated(value=EnumType.STRING)
    private PaymentStatus status;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Generated
    public Payment() {
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Double getAmount() {
        return this.amount;
    }

    @Generated
    public String getTransactionId() {
        return this.transactionId;
    }

    @Generated
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    @Generated
    public PaymentStatus getStatus() {
        return this.status;
    }

    @Generated
    public Order getOrder() {
        return this.order;
    }

    @Generated
    public User getUser() {
        return this.user;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Generated
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Generated
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Generated
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Generated
    public void setOrder(Order order) {
        this.order = order;
    }

    @Generated
    public void setUser(User user) {
        this.user = user;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        Payment other = (Payment)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Double this$amount = this.getAmount();
        Double other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !(this$amount).equals(other$amount)) {
            return false;
        }
        String this$transactionId = this.getTransactionId();
        String other$transactionId = other.getTransactionId();
        if (this$transactionId == null ? other$transactionId != null : !this$transactionId.equals(other$transactionId)) {
            return false;
        }
        String this$paymentMethod = this.getPaymentMethod();
        String other$paymentMethod = other.getPaymentMethod();
        if (this$paymentMethod == null ? other$paymentMethod != null : !this$paymentMethod.equals(other$paymentMethod)) {
            return false;
        }
        PaymentStatus this$status = this.getStatus();
        PaymentStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)(this$status)).equals(other$status)) {
            return false;
        }
        Order this$order = this.getOrder();
        Order other$order = other.getOrder();
        if (this$order == null ? other$order != null : !this$order.equals(other$order)) {
            return false;
        }
        User this$user = this.getUser();
        User other$user = other.getUser();
        if (this$user == null ? other$user != null : !(this$user).equals(other$user)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !(this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        return !(this$updatedAt == null ? other$updatedAt != null : !(this$updatedAt).equals(other$updatedAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Payment;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Double $amount = this.getAmount();
        result = result * 59 + ($amount == null ? 43 : ((Object)$amount).hashCode());
        String $transactionId = this.getTransactionId();
        result = result * 59 + ($transactionId == null ? 43 : $transactionId.hashCode());
        String $paymentMethod = this.getPaymentMethod();
        result = result * 59 + ($paymentMethod == null ? 43 : $paymentMethod.hashCode());
        PaymentStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        Order $order = this.getOrder();
        result = result * 59 + ($order == null ? 43 : $order.hashCode());
        User $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : ((Object)$user).hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Payment(id=" + this.getId() + ", amount=" + this.getAmount() + ", transactionId=" + this.getTransactionId() + ", paymentMethod=" + this.getPaymentMethod() + ", status=" + String.valueOf(this.getStatus()) + ", order=" + String.valueOf(this.getOrder()) + ", user=" + String.valueOf(this.getUser()) + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ")";
    }

    public static enum PaymentStatus {
        PENDING,
        SUCCESS,
        FAILED,
        REFUNDED;

    }
}
