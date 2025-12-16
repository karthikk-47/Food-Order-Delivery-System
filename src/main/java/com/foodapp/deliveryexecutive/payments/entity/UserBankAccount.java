package com.foodapp.deliveryexecutive.payments.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_bank_accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private Actor.Role userRole;

    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "ifsc_code", nullable = false)
    private String ifscCode;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "is_primary")
    private boolean isPrimary = true;

    @Column(name = "is_verified")
    private boolean isVerified = false;

    @Column(name = "razorpay_contact_id")
    private String razorpayContactId;

    @Column(name = "razorpay_fund_account_id")
    private String razorpayFundAccountId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
