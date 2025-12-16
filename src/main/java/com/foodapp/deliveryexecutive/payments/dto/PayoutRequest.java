/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.stereotype.Component
 */
package com.foodapp.deliveryexecutive.payments.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import org.springframework.stereotype.Component;

@Component
public class PayoutRequest {
    private String account_number;
    private String fund_account_id;
    private int amount;
    private String currency;
    private String mode;
    private String purpose;
    private boolean queue_if_low_balance;
    private String reference_id;
    private String narration;
    private Map<String, String> notes = new HashMap<String, String>();

    @Generated
    public PayoutRequest() {
    }

    @Generated
    public String getAccount_number() {
        return this.account_number;
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
    public String getMode() {
        return this.mode;
    }

    @Generated
    public String getPurpose() {
        return this.purpose;
    }

    @Generated
    public boolean isQueue_if_low_balance() {
        return this.queue_if_low_balance;
    }

    @Generated
    public String getReference_id() {
        return this.reference_id;
    }

    @Generated
    public String getNarration() {
        return this.narration;
    }

    @Generated
    public Map<String, String> getNotes() {
        return this.notes;
    }

    @Generated
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
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
    public void setMode(String mode) {
        this.mode = mode;
    }

    @Generated
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Generated
    public void setQueue_if_low_balance(boolean queue_if_low_balance) {
        this.queue_if_low_balance = queue_if_low_balance;
    }

    @Generated
    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    @Generated
    public void setNarration(String narration) {
        this.narration = narration;
    }

    @Generated
    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PayoutRequest)) {
            return false;
        }
        PayoutRequest other = (PayoutRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getAmount() != other.getAmount()) {
            return false;
        }
        if (this.isQueue_if_low_balance() != other.isQueue_if_low_balance()) {
            return false;
        }
        String this$account_number = this.getAccount_number();
        String other$account_number = other.getAccount_number();
        if (this$account_number == null ? other$account_number != null : !this$account_number.equals(other$account_number)) {
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
        String this$narration = this.getNarration();
        String other$narration = other.getNarration();
        if (this$narration == null ? other$narration != null : !this$narration.equals(other$narration)) {
            return false;
        }
        Map<String, String> this$notes = this.getNotes();
        Map<String, String> other$notes = other.getNotes();
        return !(this$notes == null ? other$notes != null : !(this$notes).equals(other$notes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof PayoutRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getAmount();
        result = result * 59 + (this.isQueue_if_low_balance() ? 79 : 97);
        String $account_number = this.getAccount_number();
        result = result * 59 + ($account_number == null ? 43 : $account_number.hashCode());
        String $fund_account_id = this.getFund_account_id();
        result = result * 59 + ($fund_account_id == null ? 43 : $fund_account_id.hashCode());
        String $currency = this.getCurrency();
        result = result * 59 + ($currency == null ? 43 : $currency.hashCode());
        String $mode = this.getMode();
        result = result * 59 + ($mode == null ? 43 : $mode.hashCode());
        String $purpose = this.getPurpose();
        result = result * 59 + ($purpose == null ? 43 : $purpose.hashCode());
        String $reference_id = this.getReference_id();
        result = result * 59 + ($reference_id == null ? 43 : $reference_id.hashCode());
        String $narration = this.getNarration();
        result = result * 59 + ($narration == null ? 43 : $narration.hashCode());
        Map<String, String> $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : ((Object)$notes).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "PayoutRequest(account_number=" + this.getAccount_number() + ", fund_account_id=" + this.getFund_account_id() + ", amount=" + this.getAmount() + ", currency=" + this.getCurrency() + ", mode=" + this.getMode() + ", purpose=" + this.getPurpose() + ", queue_if_low_balance=" + this.isQueue_if_low_balance() + ", reference_id=" + this.getReference_id() + ", narration=" + this.getNarration() + ", notes=" + String.valueOf(this.getNotes()) + ")";
    }
}
