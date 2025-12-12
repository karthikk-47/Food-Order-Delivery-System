/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.homemaker.dto;

import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerWalletDTO;
import java.time.LocalDateTime;
import lombok.Generated;

@Generated
public static class HomemakerWalletDTO.HomemakerWalletDTOBuilder {
    @Generated
    private Long id;
    @Generated
    private Long homemakerId;
    @Generated
    private Double balance;
    @Generated
    private Double totalEarnings;
    @Generated
    private Double totalWithdrawn;
    @Generated
    private LocalDateTime lastWithdrawalDate;
    @Generated
    private LocalDateTime createdAt;
    @Generated
    private LocalDateTime updatedAt;

    @Generated
    HomemakerWalletDTO.HomemakerWalletDTOBuilder() {
    }

    @Generated
    public HomemakerWalletDTO.HomemakerWalletDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    @Generated
    public HomemakerWalletDTO.HomemakerWalletDTOBuilder homemakerId(Long homemakerId) {
        this.homemakerId = homemakerId;
        return this;
    }

    @Generated
    public HomemakerWalletDTO.HomemakerWalletDTOBuilder balance(Double balance) {
        this.balance = balance;
        return this;
    }

    @Generated
    public HomemakerWalletDTO.HomemakerWalletDTOBuilder totalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
        return this;
    }

    @Generated
    public HomemakerWalletDTO.HomemakerWalletDTOBuilder totalWithdrawn(Double totalWithdrawn) {
        this.totalWithdrawn = totalWithdrawn;
        return this;
    }

    @Generated
    public HomemakerWalletDTO.HomemakerWalletDTOBuilder lastWithdrawalDate(LocalDateTime lastWithdrawalDate) {
        this.lastWithdrawalDate = lastWithdrawalDate;
        return this;
    }

    @Generated
    public HomemakerWalletDTO.HomemakerWalletDTOBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Generated
    public HomemakerWalletDTO.HomemakerWalletDTOBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Generated
    public HomemakerWalletDTO build() {
        return new HomemakerWalletDTO(this.id, this.homemakerId, this.balance, this.totalEarnings, this.totalWithdrawn, this.lastWithdrawalDate, this.createdAt, this.updatedAt);
    }

    @Generated
    public String toString() {
        return "HomemakerWalletDTO.HomemakerWalletDTOBuilder(id=" + this.id + ", homemakerId=" + this.homemakerId + ", balance=" + this.balance + ", totalEarnings=" + this.totalEarnings + ", totalWithdrawn=" + this.totalWithdrawn + ", lastWithdrawalDate=" + String.valueOf(this.lastWithdrawalDate) + ", createdAt=" + String.valueOf(this.createdAt) + ", updatedAt=" + String.valueOf(this.updatedAt) + ")";
    }
}
