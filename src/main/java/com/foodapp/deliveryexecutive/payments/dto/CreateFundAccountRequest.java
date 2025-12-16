/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.payments.dto;

import com.foodapp.deliveryexecutive.payments.entity.BankAccount;
import lombok.Generated;

public class CreateFundAccountRequest {
    private String contact_id;
    private String account_type;
    private BankAccount bank_account;

    @Generated
    public CreateFundAccountRequest() {
    }

    @Generated
    public String getContact_id() {
        return this.contact_id;
    }

    @Generated
    public String getAccount_type() {
        return this.account_type;
    }

    @Generated
    public BankAccount getBank_account() {
        return this.bank_account;
    }

    @Generated
    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    @Generated
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    @Generated
    public void setBank_account(BankAccount bank_account) {
        this.bank_account = bank_account;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreateFundAccountRequest)) {
            return false;
        }
        CreateFundAccountRequest other = (CreateFundAccountRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$contact_id = this.getContact_id();
        String other$contact_id = other.getContact_id();
        if (this$contact_id == null ? other$contact_id != null : !this$contact_id.equals(other$contact_id)) {
            return false;
        }
        String this$account_type = this.getAccount_type();
        String other$account_type = other.getAccount_type();
        if (this$account_type == null ? other$account_type != null : !this$account_type.equals(other$account_type)) {
            return false;
        }
        BankAccount this$bank_account = this.getBank_account();
        BankAccount other$bank_account = other.getBank_account();
        return !(this$bank_account == null ? other$bank_account != null : !(this$bank_account).equals(other$bank_account));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CreateFundAccountRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $contact_id = this.getContact_id();
        result = result * 59 + ($contact_id == null ? 43 : $contact_id.hashCode());
        String $account_type = this.getAccount_type();
        result = result * 59 + ($account_type == null ? 43 : $account_type.hashCode());
        BankAccount $bank_account = this.getBank_account();
        result = result * 59 + ($bank_account == null ? 43 : ((Object)$bank_account).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "CreateFundAccountRequest(contact_id=" + this.getContact_id() + ", account_type=" + this.getAccount_type() + ", bank_account=" + String.valueOf(this.getBank_account()) + ")";
    }
}
