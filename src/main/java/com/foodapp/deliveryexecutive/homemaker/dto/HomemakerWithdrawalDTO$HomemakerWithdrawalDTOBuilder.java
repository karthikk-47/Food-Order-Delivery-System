/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.homemaker.dto;

import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerWithdrawalDTO;
import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWithdrawal;
import java.time.LocalDateTime;
import lombok.Generated;

@Generated
public static class HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder {
    @Generated
    private Long id;
    @Generated
    private Long homemakerId;
    @Generated
    private Double amount;
    @Generated
    private HomemakerWithdrawal.WithdrawalStatus status;
    @Generated
    private HomemakerWithdrawal.WithdrawalMethod method;
    @Generated
    private String bankAccountNumber;
    @Generated
    private String bankIFSC;
    @Generated
    private String upiId;
    @Generated
    private String chequeNumber;
    @Generated
    private String transactionId;
    @Generated
    private String rejectionReason;
    @Generated
    private LocalDateTime requestDate;
    @Generated
    private LocalDateTime processedDate;
    @Generated
    private LocalDateTime completedDate;
    @Generated
    private LocalDateTime createdAt;
    @Generated
    private LocalDateTime updatedAt;

    @Generated
    HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder() {
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder homemakerId(Long homemakerId) {
        this.homemakerId = homemakerId;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder amount(Double amount) {
        this.amount = amount;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder status(HomemakerWithdrawal.WithdrawalStatus status) {
        this.status = status;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder method(HomemakerWithdrawal.WithdrawalMethod method) {
        this.method = method;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder bankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder bankIFSC(String bankIFSC) {
        this.bankIFSC = bankIFSC;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder upiId(String upiId) {
        this.upiId = upiId;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder chequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder rejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder requestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder processedDate(LocalDateTime processedDate) {
        this.processedDate = processedDate;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder completedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Generated
    public HomemakerWithdrawalDTO build() {
        return new HomemakerWithdrawalDTO(this.id, this.homemakerId, this.amount, this.status, this.method, this.bankAccountNumber, this.bankIFSC, this.upiId, this.chequeNumber, this.transactionId, this.rejectionReason, this.requestDate, this.processedDate, this.completedDate, this.createdAt, this.updatedAt);
    }

    @Generated
    public String toString() {
        return "HomemakerWithdrawalDTO.HomemakerWithdrawalDTOBuilder(id=" + this.id + ", homemakerId=" + this.homemakerId + ", amount=" + this.amount + ", status=" + String.valueOf(this.status) + ", method=" + String.valueOf(this.method) + ", bankAccountNumber=" + this.bankAccountNumber + ", bankIFSC=" + this.bankIFSC + ", upiId=" + this.upiId + ", chequeNumber=" + this.chequeNumber + ", transactionId=" + this.transactionId + ", rejectionReason=" + this.rejectionReason + ", requestDate=" + String.valueOf(this.requestDate) + ", processedDate=" + String.valueOf(this.processedDate) + ", completedDate=" + String.valueOf(this.completedDate) + ", createdAt=" + String.valueOf(this.createdAt) + ", updatedAt=" + String.valueOf(this.updatedAt) + ")";
    }
}
