/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.homemaker.dto;

import java.time.LocalDateTime;
import lombok.Generated;

public class HomemakerWalletDTO {
    private Long id;
    private Long homemakerId;
    private Double balance;
    private Double totalEarnings;
    private Double totalWithdrawn;
    private LocalDateTime lastWithdrawalDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Generated
    public static HomemakerWalletDTOBuilder builder() {
        return new HomemakerWalletDTOBuilder();
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
    public Double getBalance() {
        return this.balance;
    }

    @Generated
    public Double getTotalEarnings() {
        return this.totalEarnings;
    }

    @Generated
    public Double getTotalWithdrawn() {
        return this.totalWithdrawn;
    }

    @Generated
    public LocalDateTime getLastWithdrawalDate() {
        return this.lastWithdrawalDate;
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
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Generated
    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    @Generated
    public void setTotalWithdrawn(Double totalWithdrawn) {
        this.totalWithdrawn = totalWithdrawn;
    }

    @Generated
    public void setLastWithdrawalDate(LocalDateTime lastWithdrawalDate) {
        this.lastWithdrawalDate = lastWithdrawalDate;
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
        if (!(o instanceof HomemakerWalletDTO)) {
            return false;
        }
        HomemakerWalletDTO other = (HomemakerWalletDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Long this$homemakerId = this.getHomemakerId();
        Long other$homemakerId = other.getHomemakerId();
        if (this$homemakerId == null ? other$homemakerId != null : !(this$homemakerId).equals(other$homemakerId)) {
            return false;
        }
        Double this$balance = this.getBalance();
        Double other$balance = other.getBalance();
        if (this$balance == null ? other$balance != null : !(this$balance).equals(other$balance)) {
            return false;
        }
        Double this$totalEarnings = this.getTotalEarnings();
        Double other$totalEarnings = other.getTotalEarnings();
        if (this$totalEarnings == null ? other$totalEarnings != null : !(this$totalEarnings).equals(other$totalEarnings)) {
            return false;
        }
        Double this$totalWithdrawn = this.getTotalWithdrawn();
        Double other$totalWithdrawn = other.getTotalWithdrawn();
        if (this$totalWithdrawn == null ? other$totalWithdrawn != null : !(this$totalWithdrawn).equals(other$totalWithdrawn)) {
            return false;
        }
        LocalDateTime this$lastWithdrawalDate = this.getLastWithdrawalDate();
        LocalDateTime other$lastWithdrawalDate = other.getLastWithdrawalDate();
        if (this$lastWithdrawalDate == null ? other$lastWithdrawalDate != null : !(this$lastWithdrawalDate).equals(other$lastWithdrawalDate)) {
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
        return other instanceof HomemakerWalletDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $homemakerId = this.getHomemakerId();
        result = result * 59 + ($homemakerId == null ? 43 : ((Object)$homemakerId).hashCode());
        Double $balance = this.getBalance();
        result = result * 59 + ($balance == null ? 43 : ((Object)$balance).hashCode());
        Double $totalEarnings = this.getTotalEarnings();
        result = result * 59 + ($totalEarnings == null ? 43 : ((Object)$totalEarnings).hashCode());
        Double $totalWithdrawn = this.getTotalWithdrawn();
        result = result * 59 + ($totalWithdrawn == null ? 43 : ((Object)$totalWithdrawn).hashCode());
        LocalDateTime $lastWithdrawalDate = this.getLastWithdrawalDate();
        result = result * 59 + ($lastWithdrawalDate == null ? 43 : ((Object)$lastWithdrawalDate).hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "HomemakerWalletDTO(id=" + this.getId() + ", homemakerId=" + this.getHomemakerId() + ", balance=" + this.getBalance() + ", totalEarnings=" + this.getTotalEarnings() + ", totalWithdrawn=" + this.getTotalWithdrawn() + ", lastWithdrawalDate=" + String.valueOf(this.getLastWithdrawalDate()) + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ")";
    }

    @Generated
    public HomemakerWalletDTO() {
    }

    @Generated
    public HomemakerWalletDTO(Long id, Long homemakerId, Double balance, Double totalEarnings, Double totalWithdrawn, LocalDateTime lastWithdrawalDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.homemakerId = homemakerId;
        this.balance = balance;
        this.totalEarnings = totalEarnings;
        this.totalWithdrawn = totalWithdrawn;
        this.lastWithdrawalDate = lastWithdrawalDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Generated
    public static class HomemakerWalletDTOBuilder {
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
        HomemakerWalletDTOBuilder() {
        }

        @Generated
        public HomemakerWalletDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Generated
        public HomemakerWalletDTOBuilder homemakerId(Long homemakerId) {
            this.homemakerId = homemakerId;
            return this;
        }

        @Generated
        public HomemakerWalletDTOBuilder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        @Generated
        public HomemakerWalletDTOBuilder totalEarnings(Double totalEarnings) {
            this.totalEarnings = totalEarnings;
            return this;
        }

        @Generated
        public HomemakerWalletDTOBuilder totalWithdrawn(Double totalWithdrawn) {
            this.totalWithdrawn = totalWithdrawn;
            return this;
        }

        @Generated
        public HomemakerWalletDTOBuilder lastWithdrawalDate(LocalDateTime lastWithdrawalDate) {
            this.lastWithdrawalDate = lastWithdrawalDate;
            return this;
        }

        @Generated
        public HomemakerWalletDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @Generated
        public HomemakerWalletDTOBuilder updatedAt(LocalDateTime updatedAt) {
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
}
