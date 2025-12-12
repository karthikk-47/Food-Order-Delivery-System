/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Embeddable
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.payments.entity;

import jakarta.persistence.Embeddable;
import lombok.Generated;

@Embeddable
public class BankAccount {
    private String name;
    private String bank_name;
    private String ifsc;
    private String account_number;

    @Generated
    public BankAccount() {
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getBank_name() {
        return this.bank_name;
    }

    @Generated
    public String getIfsc() {
        return this.ifsc;
    }

    @Generated
    public String getAccount_number() {
        return this.account_number;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    @Generated
    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    @Generated
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        BankAccount other = (BankAccount)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$bank_name = this.getBank_name();
        String other$bank_name = other.getBank_name();
        if (this$bank_name == null ? other$bank_name != null : !this$bank_name.equals(other$bank_name)) {
            return false;
        }
        String this$ifsc = this.getIfsc();
        String other$ifsc = other.getIfsc();
        if (this$ifsc == null ? other$ifsc != null : !this$ifsc.equals(other$ifsc)) {
            return false;
        }
        String this$account_number = this.getAccount_number();
        String other$account_number = other.getAccount_number();
        return !(this$account_number == null ? other$account_number != null : !this$account_number.equals(other$account_number));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof BankAccount;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $bank_name = this.getBank_name();
        result = result * 59 + ($bank_name == null ? 43 : $bank_name.hashCode());
        String $ifsc = this.getIfsc();
        result = result * 59 + ($ifsc == null ? 43 : $ifsc.hashCode());
        String $account_number = this.getAccount_number();
        result = result * 59 + ($account_number == null ? 43 : $account_number.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "BankAccount(name=" + this.getName() + ", bank_name=" + this.getBank_name() + ", ifsc=" + this.getIfsc() + ", account_number=" + this.getAccount_number() + ")";
    }
}
