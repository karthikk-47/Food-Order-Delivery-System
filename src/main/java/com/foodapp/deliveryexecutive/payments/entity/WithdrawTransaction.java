package com.foodapp.deliveryexecutive.payments.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdraw_transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    
    @Column(nullable = false)
    private Double amount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WithdrawStatus status;
    
    @Column(name = "payout_id")
    private String payoutId;
    
    @Column(name = "fund_account_id")
    private String fundAccountId;
    
    @Column(name = "utr")
    private String utr;
    
    @Column(name = "purpose")
    private String purpose;
    
    @Column(name = "narration")
    private String narration;
    
    @Column(name = "reference_id")
    private String referenceId;
    
    @Column(name = "failure_reason")
    private String failureReason;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum WithdrawStatus {
        PENDING,
        PROCESSING,
        PROCESSED,
        REVERSED,
        FAILED,
        CANCELLED
    }
}
