/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.ElementCollection
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.MapKeyColumn
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.payments.entity;

import com.foodapp.deliveryexecutive.payments.entity.StatusDetails;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

@Entity
public class Payout {
    @Id
    private String id;
    private String entity;
    @Column(name="fund_account_id")
    private String fundAccountId;
    private int amount;
    private String currency;
    @ElementCollection
    @MapKeyColumn(name="note_key")
    @Column(name="note_value", length=256)
    private Map<String, String> notes = new HashMap<String, String>();
    private int fees;
    private int tax;
    private String status;
    private String utr;
    private String mode;
    private String purpose;
    @Column(name="reference_id")
    private String referenceId;
    @Column(name="debit_account_number")
    private String debitAccountNumber;
    private String narration;
    @Column(name="batch_id")
    private String batchId;
    @Column(name="status_details")
    private StatusDetails statusDetails;
    @Column(name="created_at")
    private int createdAt;
    @Column(name="fee_type")
    private String feeType;

    @Generated
    public Payout() {
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getEntity() {
        return this.entity;
    }

    @Generated
    public String getFundAccountId() {
        return this.fundAccountId;
    }

    @Generated
    public int getAmount() {
        return this.amount;
    }

    @Generated
    public String getCurrency() {
        return this.currency;
    }

    @Generated
    public Map<String, String> getNotes() {
        return this.notes;
    }

    @Generated
    public int getFees() {
        return this.fees;
    }

    @Generated
    public int getTax() {
        return this.tax;
    }

    @Generated
    public String getStatus() {
        return this.status;
    }

    @Generated
    public String getUtr() {
        return this.utr;
    }

    @Generated
    public String getMode() {
        return this.mode;
    }

    @Generated
    public String getPurpose() {
        return this.purpose;
    }

    @Generated
    public String getReferenceId() {
        return this.referenceId;
    }

    @Generated
    public String getDebitAccountNumber() {
        return this.debitAccountNumber;
    }

    @Generated
    public String getNarration() {
        return this.narration;
    }

    @Generated
    public String getBatchId() {
        return this.batchId;
    }

    @Generated
    public StatusDetails getStatusDetails() {
        return this.statusDetails;
    }

    @Generated
    public int getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public String getFeeType() {
        return this.feeType;
    }

    @Generated
    public void setId(String id) {
        this.id = id;
    }

    @Generated
    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Generated
    public void setFundAccountId(String fundAccountId) {
        this.fundAccountId = fundAccountId;
    }

    @Generated
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Generated
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Generated
    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }

    @Generated
    public void setFees(int fees) {
        this.fees = fees;
    }

    @Generated
    public void setTax(int tax) {
        this.tax = tax;
    }

    @Generated
    public void setStatus(String status) {
        this.status = status;
    }

    @Generated
    public void setUtr(String utr) {
        this.utr = utr;
    }

    @Generated
    public void setMode(String mode) {
        this.mode = mode;
    }

    @Generated
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Generated
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    @Generated
    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    @Generated
    public void setNarration(String narration) {
        this.narration = narration;
    }

    @Generated
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Generated
    public void setStatusDetails(StatusDetails statusDetails) {
        this.statusDetails = statusDetails;
    }

    @Generated
    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Payout)) {
            return false;
        }
        Payout other = (Payout)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getAmount() != other.getAmount()) {
            return false;
        }
        if (this.getFees() != other.getFees()) {
            return false;
        }
        if (this.getTax() != other.getTax()) {
            return false;
        }
        if (this.getCreatedAt() != other.getCreatedAt()) {
            return false;
        }
        String this$id = this.getId();
        String other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
            return false;
        }
        String this$entity = this.getEntity();
        String other$entity = other.getEntity();
        if (this$entity == null ? other$entity != null : !this$entity.equals(other$entity)) {
            return false;
        }
        String this$fundAccountId = this.getFundAccountId();
        String other$fundAccountId = other.getFundAccountId();
        if (this$fundAccountId == null ? other$fundAccountId != null : !this$fundAccountId.equals(other$fundAccountId)) {
            return false;
        }
        String this$currency = this.getCurrency();
        String other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) {
            return false;
        }
        Map<String, String> this$notes = this.getNotes();
        Map<String, String> other$notes = other.getNotes();
        if (this$notes == null ? other$notes != null : !(this$notes).equals(other$notes)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        String this$utr = this.getUtr();
        String other$utr = other.getUtr();
        if (this$utr == null ? other$utr != null : !this$utr.equals(other$utr)) {
            return false;
        }
        String this$mode = this.getMode();
        String other$mode = other.getMode();
        if (this$mode == null ? other$mode != null : !this$mode.equals(other$mode)) {
            return false;
        }
        String this$purpose = this.getPurpose();
        String other$purpose = other.getPurpose();
        if (this$purpose == null ? other$purpose != null : !this$purpose.equals(other$purpose)) {
            return false;
        }
        String this$referenceId = this.getReferenceId();
        String other$referenceId = other.getReferenceId();
        if (this$referenceId == null ? other$referenceId != null : !this$referenceId.equals(other$referenceId)) {
            return false;
        }
        String this$debitAccountNumber = this.getDebitAccountNumber();
        String other$debitAccountNumber = other.getDebitAccountNumber();
        if (this$debitAccountNumber == null ? other$debitAccountNumber != null : !this$debitAccountNumber.equals(other$debitAccountNumber)) {
            return false;
        }
        String this$narration = this.getNarration();
        String other$narration = other.getNarration();
        if (this$narration == null ? other$narration != null : !this$narration.equals(other$narration)) {
            return false;
        }
        String this$batchId = this.getBatchId();
        String other$batchId = other.getBatchId();
        if (this$batchId == null ? other$batchId != null : !this$batchId.equals(other$batchId)) {
            return false;
        }
        StatusDetails this$statusDetails = this.getStatusDetails();
        StatusDetails other$statusDetails = other.getStatusDetails();
        if (this$statusDetails == null ? other$statusDetails != null : !this$statusDetails.equals(other$statusDetails)) {
            return false;
        }
        String this$feeType = this.getFeeType();
        String other$feeType = other.getFeeType();
        return !(this$feeType == null ? other$feeType != null : !this$feeType.equals(other$feeType));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Payout;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getAmount();
        result = result * 59 + this.getFees();
        result = result * 59 + this.getTax();
        result = result * 59 + this.getCreatedAt();
        String $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        String $entity = this.getEntity();
        result = result * 59 + ($entity == null ? 43 : $entity.hashCode());
        String $fundAccountId = this.getFundAccountId();
        result = result * 59 + ($fundAccountId == null ? 43 : $fundAccountId.hashCode());
        String $currency = this.getCurrency();
        result = result * 59 + ($currency == null ? 43 : $currency.hashCode());
        Map<String, String> $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : ((Object)$notes).hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        String $utr = this.getUtr();
        result = result * 59 + ($utr == null ? 43 : $utr.hashCode());
        String $mode = this.getMode();
        result = result * 59 + ($mode == null ? 43 : $mode.hashCode());
        String $purpose = this.getPurpose();
        result = result * 59 + ($purpose == null ? 43 : $purpose.hashCode());
        String $referenceId = this.getReferenceId();
        result = result * 59 + ($referenceId == null ? 43 : $referenceId.hashCode());
        String $debitAccountNumber = this.getDebitAccountNumber();
        result = result * 59 + ($debitAccountNumber == null ? 43 : $debitAccountNumber.hashCode());
        String $narration = this.getNarration();
        result = result * 59 + ($narration == null ? 43 : $narration.hashCode());
        String $batchId = this.getBatchId();
        result = result * 59 + ($batchId == null ? 43 : $batchId.hashCode());
        StatusDetails $statusDetails = this.getStatusDetails();
        result = result * 59 + ($statusDetails == null ? 43 : $statusDetails.hashCode());
        String $feeType = this.getFeeType();
        result = result * 59 + ($feeType == null ? 43 : $feeType.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Payout(id=" + this.getId() + ", entity=" + this.getEntity() + ", fundAccountId=" + this.getFundAccountId() + ", amount=" + this.getAmount() + ", currency=" + this.getCurrency() + ", notes=" + String.valueOf(this.getNotes()) + ", fees=" + this.getFees() + ", tax=" + this.getTax() + ", status=" + this.getStatus() + ", utr=" + this.getUtr() + ", mode=" + this.getMode() + ", purpose=" + this.getPurpose() + ", referenceId=" + this.getReferenceId() + ", debitAccountNumber=" + this.getDebitAccountNumber() + ", narration=" + this.getNarration() + ", batchId=" + this.getBatchId() + ", statusDetails=" + String.valueOf(this.getStatusDetails()) + ", createdAt=" + this.getCreatedAt() + ", feeType=" + this.getFeeType() + ")";
    }
}
