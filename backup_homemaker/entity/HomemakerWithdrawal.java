/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.PrePersist
 *  jakarta.persistence.PreUpdate
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.homemaker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="homemaker_withdrawals")
public class HomemakerWithdrawal {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long homemakerId;
    private Double amount;
    @Enumerated(value=EnumType.STRING)
    private WithdrawalStatus status;
    @Enumerated(value=EnumType.STRING)
    private WithdrawalMethod method;
    private String bankAccountNumber;
    private String bankIFSC;
    private String upiId;
    private String chequeNumber;
    private String transactionId;
    private String rejectionReason;
    private LocalDateTime requestDate;
    private LocalDateTime processedDate;
    private LocalDateTime completedDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = WithdrawalStatus.PENDING;
        this.requestDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getHomemakerId() {
        return this.homemakerId;
    }

    @Generated
    public Double getAmount() {
        return this.amount;
    }

    @Generated
    public WithdrawalStatus getStatus() {
        return this.status;
    }

    @Generated
    public WithdrawalMethod getMethod() {
        return this.method;
    }

    @Generated
    public String getBankAccountNumber() {
        return this.bankAccountNumber;
    }

    @Generated
    public String getBankIFSC() {
        return this.bankIFSC;
    }

    @Generated
    public String getUpiId() {
        return this.upiId;
    }

    @Generated
    public String getChequeNumber() {
        return this.chequeNumber;
    }

    @Generated
    public String getTransactionId() {
        return this.transactionId;
    }

    @Generated
    public String getRejectionReason() {
        return this.rejectionReason;
    }

    @Generated
    public LocalDateTime getRequestDate() {
        return this.requestDate;
    }

    @Generated
    public LocalDateTime getProcessedDate() {
        return this.processedDate;
    }

    @Generated
    public LocalDateTime getCompletedDate() {
        return this.completedDate;
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
    public void setHomemakerId(Long homemakerId) {
        this.homemakerId = homemakerId;
    }

    @Generated
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Generated
    public void setStatus(WithdrawalStatus status) {
        this.status = status;
    }

    @Generated
    public void setMethod(WithdrawalMethod method) {
        this.method = method;
    }

    @Generated
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @Generated
    public void setBankIFSC(String bankIFSC) {
        this.bankIFSC = bankIFSC;
    }

    @Generated
    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    @Generated
    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    @Generated
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Generated
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Generated
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    @Generated
    public void setProcessedDate(LocalDateTime processedDate) {
        this.processedDate = processedDate;
    }

    @Generated
    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
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
        if (!(o instanceof HomemakerWithdrawal)) {
            return false;
        }
        HomemakerWithdrawal other = (HomemakerWithdrawal)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$homemakerId = this.getHomemakerId();
        Long other$homemakerId = other.getHomemakerId();
        if (this$homemakerId == null ? other$homemakerId != null : !((Object)this$homemakerId).equals(other$homemakerId)) {
            return false;
        }
        Double this$amount = this.getAmount();
        Double other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !((Object)this$amount).equals(other$amount)) {
            return false;
        }
        WithdrawalStatus this$status = this.getStatus();
        WithdrawalStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)((Object)this$status)).equals((Object)other$status)) {
            return false;
        }
        WithdrawalMethod this$method = this.getMethod();
        WithdrawalMethod other$method = other.getMethod();
        if (this$method == null ? other$method != null : !((Object)((Object)this$method)).equals((Object)other$method)) {
            return false;
        }
        String this$bankAccountNumber = this.getBankAccountNumber();
        String other$bankAccountNumber = other.getBankAccountNumber();
        if (this$bankAccountNumber == null ? other$bankAccountNumber != null : !this$bankAccountNumber.equals(other$bankAccountNumber)) {
            return false;
        }
        String this$bankIFSC = this.getBankIFSC();
        String other$bankIFSC = other.getBankIFSC();
        if (this$bankIFSC == null ? other$bankIFSC != null : !this$bankIFSC.equals(other$bankIFSC)) {
            return false;
        }
        String this$upiId = this.getUpiId();
        String other$upiId = other.getUpiId();
        if (this$upiId == null ? other$upiId != null : !this$upiId.equals(other$upiId)) {
            return false;
        }
        String this$chequeNumber = this.getChequeNumber();
        String other$chequeNumber = other.getChequeNumber();
        if (this$chequeNumber == null ? other$chequeNumber != null : !this$chequeNumber.equals(other$chequeNumber)) {
            return false;
        }
        String this$transactionId = this.getTransactionId();
        String other$transactionId = other.getTransactionId();
        if (this$transactionId == null ? other$transactionId != null : !this$transactionId.equals(other$transactionId)) {
            return false;
        }
        String this$rejectionReason = this.getRejectionReason();
        String other$rejectionReason = other.getRejectionReason();
        if (this$rejectionReason == null ? other$rejectionReason != null : !this$rejectionReason.equals(other$rejectionReason)) {
            return false;
        }
        LocalDateTime this$requestDate = this.getRequestDate();
        LocalDateTime other$requestDate = other.getRequestDate();
        if (this$requestDate == null ? other$requestDate != null : !((Object)this$requestDate).equals(other$requestDate)) {
            return false;
        }
        LocalDateTime this$processedDate = this.getProcessedDate();
        LocalDateTime other$processedDate = other.getProcessedDate();
        if (this$processedDate == null ? other$processedDate != null : !((Object)this$processedDate).equals(other$processedDate)) {
            return false;
        }
        LocalDateTime this$completedDate = this.getCompletedDate();
        LocalDateTime other$completedDate = other.getCompletedDate();
        if (this$completedDate == null ? other$completedDate != null : !((Object)this$completedDate).equals(other$completedDate)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !((Object)this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        return !(this$updatedAt == null ? other$updatedAt != null : !((Object)this$updatedAt).equals(other$updatedAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof HomemakerWithdrawal;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $homemakerId = this.getHomemakerId();
        result = result * 59 + ($homemakerId == null ? 43 : ((Object)$homemakerId).hashCode());
        Double $amount = this.getAmount();
        result = result * 59 + ($amount == null ? 43 : ((Object)$amount).hashCode());
        WithdrawalStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        WithdrawalMethod $method = this.getMethod();
        result = result * 59 + ($method == null ? 43 : ((Object)((Object)$method)).hashCode());
        String $bankAccountNumber = this.getBankAccountNumber();
        result = result * 59 + ($bankAccountNumber == null ? 43 : $bankAccountNumber.hashCode());
        String $bankIFSC = this.getBankIFSC();
        result = result * 59 + ($bankIFSC == null ? 43 : $bankIFSC.hashCode());
        String $upiId = this.getUpiId();
        result = result * 59 + ($upiId == null ? 43 : $upiId.hashCode());
        String $chequeNumber = this.getChequeNumber();
        result = result * 59 + ($chequeNumber == null ? 43 : $chequeNumber.hashCode());
        String $transactionId = this.getTransactionId();
        result = result * 59 + ($transactionId == null ? 43 : $transactionId.hashCode());
        String $rejectionReason = this.getRejectionReason();
        result = result * 59 + ($rejectionReason == null ? 43 : $rejectionReason.hashCode());
        LocalDateTime $requestDate = this.getRequestDate();
        result = result * 59 + ($requestDate == null ? 43 : ((Object)$requestDate).hashCode());
        LocalDateTime $processedDate = this.getProcessedDate();
        result = result * 59 + ($processedDate == null ? 43 : ((Object)$processedDate).hashCode());
        LocalDateTime $completedDate = this.getCompletedDate();
        result = result * 59 + ($completedDate == null ? 43 : ((Object)$completedDate).hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "HomemakerWithdrawal(id=" + this.getId() + ", homemakerId=" + this.getHomemakerId() + ", amount=" + this.getAmount() + ", status=" + String.valueOf((Object)this.getStatus()) + ", method=" + String.valueOf((Object)this.getMethod()) + ", bankAccountNumber=" + this.getBankAccountNumber() + ", bankIFSC=" + this.getBankIFSC() + ", upiId=" + this.getUpiId() + ", chequeNumber=" + this.getChequeNumber() + ", transactionId=" + this.getTransactionId() + ", rejectionReason=" + this.getRejectionReason() + ", requestDate=" + String.valueOf(this.getRequestDate()) + ", processedDate=" + String.valueOf(this.getProcessedDate()) + ", completedDate=" + String.valueOf(this.getCompletedDate()) + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ")";
    }

    @Generated
    public HomemakerWithdrawal() {
    }

    @Generated
    public HomemakerWithdrawal(Long id, Long homemakerId, Double amount, WithdrawalStatus status, WithdrawalMethod method, String bankAccountNumber, String bankIFSC, String upiId, String chequeNumber, String transactionId, String rejectionReason, LocalDateTime requestDate, LocalDateTime processedDate, LocalDateTime completedDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.homemakerId = homemakerId;
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.bankAccountNumber = bankAccountNumber;
        this.bankIFSC = bankIFSC;
        this.upiId = upiId;
        this.chequeNumber = chequeNumber;
        this.transactionId = transactionId;
        this.rejectionReason = rejectionReason;
        this.requestDate = requestDate;
        this.processedDate = processedDate;
        this.completedDate = completedDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static enum WithdrawalStatus {
        PENDING,
        APPROVED,
        REJECTED,
        COMPLETED;

    }

    public static enum WithdrawalMethod {
        BANK_TRANSFER,
        UPI,
        CHEQUE;

    }
}
