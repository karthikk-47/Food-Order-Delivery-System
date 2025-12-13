/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.stereotype.Component
 */
package com.foodapp.deliveryexecutive.payments.dto;

import com.foodapp.deliveryexecutive.payments.entity.StatusDetails;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import org.springframework.stereotype.Component;

@Component
public class PayoutResponse {
    private String id;
    private String entity;
    private String fund_account_id;
    private int amount;
    private String currency;
    private Map<String, String> notes = new HashMap<String, String>();
    private int fees;
    private int tax;
    private String status;
    private String utr;
    private String mode;
    private String purpose;
    private String reference_id;
    private String debit_account_number;
    private String narration;
    private String batch_id;
    private StatusDetails status_details;
    private int created_at;
    private String fee_type;

    @Generated
    public PayoutResponse() {
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
    public String getFund_account_id() {
        return this.fund_account_id;
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
    public String getReference_id() {
        return this.reference_id;
    }

    @Generated
    public String getDebit_account_number() {
        return this.debit_account_number;
    }

    @Generated
    public String getNarration() {
        return this.narration;
    }

    @Generated
    public String getBatch_id() {
        return this.batch_id;
    }

    @Generated
    public StatusDetails getStatus_details() {
        return this.status_details;
    }

    @Generated
    public int getCreated_at() {
        return this.created_at;
    }

    @Generated
    public String getFee_type() {
        return this.fee_type;
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
    public void setFund_account_id(String fund_account_id) {
        this.fund_account_id = fund_account_id;
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
    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    @Generated
    public void setDebit_account_number(String debit_account_number) {
        this.debit_account_number = debit_account_number;
    }

    @Generated
    public void setNarration(String narration) {
        this.narration = narration;
    }

    @Generated
    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    @Generated
    public void setStatus_details(StatusDetails status_details) {
        this.status_details = status_details;
    }

    @Generated
    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    @Generated
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PayoutResponse)) {
            return false;
        }
        PayoutResponse other = (PayoutResponse)o;
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
        if (this.getCreated_at() != other.getCreated_at()) {
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
        String this$fund_account_id = this.getFund_account_id();
        String other$fund_account_id = other.getFund_account_id();
        if (this$fund_account_id == null ? other$fund_account_id != null : !this$fund_account_id.equals(other$fund_account_id)) {
            return false;
        }
        String this$currency = this.getCurrency();
        String other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) {
            return false;
        }
        Map<String, String> this$notes = this.getNotes();
        Map<String, String> other$notes = other.getNotes();
        if (this$notes == null ? other$notes != null : !((Object)this$notes).equals(other$notes)) {
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
        String this$reference_id = this.getReference_id();
        String other$reference_id = other.getReference_id();
        if (this$reference_id == null ? other$reference_id != null : !this$reference_id.equals(other$reference_id)) {
            return false;
        }
        String this$debit_account_number = this.getDebit_account_number();
        String other$debit_account_number = other.getDebit_account_number();
        if (this$debit_account_number == null ? other$debit_account_number != null : !this$debit_account_number.equals(other$debit_account_number)) {
            return false;
        }
        String this$narration = this.getNarration();
        String other$narration = other.getNarration();
        if (this$narration == null ? other$narration != null : !this$narration.equals(other$narration)) {
            return false;
        }
        String this$batch_id = this.getBatch_id();
        String other$batch_id = other.getBatch_id();
        if (this$batch_id == null ? other$batch_id != null : !this$batch_id.equals(other$batch_id)) {
            return false;
        }
        StatusDetails this$status_details = this.getStatus_details();
        StatusDetails other$status_details = other.getStatus_details();
        if (this$status_details == null ? other$status_details != null : !this$status_details.equals(other$status_details)) {
            return false;
        }
        String this$fee_type = this.getFee_type();
        String other$fee_type = other.getFee_type();
        return !(this$fee_type == null ? other$fee_type != null : !this$fee_type.equals(other$fee_type));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof PayoutResponse;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getAmount();
        result = result * 59 + this.getFees();
        result = result * 59 + this.getTax();
        result = result * 59 + this.getCreated_at();
        String $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        String $entity = this.getEntity();
        result = result * 59 + ($entity == null ? 43 : $entity.hashCode());
        String $fund_account_id = this.getFund_account_id();
        result = result * 59 + ($fund_account_id == null ? 43 : $fund_account_id.hashCode());
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
        String $reference_id = this.getReference_id();
        result = result * 59 + ($reference_id == null ? 43 : $reference_id.hashCode());
        String $debit_account_number = this.getDebit_account_number();
        result = result * 59 + ($debit_account_number == null ? 43 : $debit_account_number.hashCode());
        String $narration = this.getNarration();
        result = result * 59 + ($narration == null ? 43 : $narration.hashCode());
        String $batch_id = this.getBatch_id();
        result = result * 59 + ($batch_id == null ? 43 : $batch_id.hashCode());
        StatusDetails $status_details = this.getStatus_details();
        result = result * 59 + ($status_details == null ? 43 : $status_details.hashCode());
        String $fee_type = this.getFee_type();
        result = result * 59 + ($fee_type == null ? 43 : $fee_type.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "PayoutResponse(id=" + this.getId() + ", entity=" + this.getEntity() + ", fund_account_id=" + this.getFund_account_id() + ", amount=" + this.getAmount() + ", currency=" + this.getCurrency() + ", notes=" + String.valueOf(this.getNotes()) + ", fees=" + this.getFees() + ", tax=" + this.getTax() + ", status=" + this.getStatus() + ", utr=" + this.getUtr() + ", mode=" + this.getMode() + ", purpose=" + this.getPurpose() + ", reference_id=" + this.getReference_id() + ", debit_account_number=" + this.getDebit_account_number() + ", narration=" + this.getNarration() + ", batch_id=" + this.getBatch_id() + ", status_details=" + String.valueOf(this.getStatus_details()) + ", created_at=" + this.getCreated_at() + ", fee_type=" + this.getFee_type() + ")";
    }
}
