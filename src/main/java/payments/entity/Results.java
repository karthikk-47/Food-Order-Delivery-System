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
public class Results {
    private String account_status;
    private String registered_name;

    @Generated
    public Results() {
    }

    @Generated
    public String getAccount_status() {
        return this.account_status;
    }

    @Generated
    public String getRegistered_name() {
        return this.registered_name;
    }

    @Generated
    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    @Generated
    public void setRegistered_name(String registered_name) {
        this.registered_name = registered_name;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Results)) {
            return false;
        }
        Results other = (Results)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$account_status = this.getAccount_status();
        String other$account_status = other.getAccount_status();
        if (this$account_status == null ? other$account_status != null : !this$account_status.equals(other$account_status)) {
            return false;
        }
        String this$registered_name = this.getRegistered_name();
        String other$registered_name = other.getRegistered_name();
        return !(this$registered_name == null ? other$registered_name != null : !this$registered_name.equals(other$registered_name));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Results;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $account_status = this.getAccount_status();
        result = result * 59 + ($account_status == null ? 43 : $account_status.hashCode());
        String $registered_name = this.getRegistered_name();
        result = result * 59 + ($registered_name == null ? 43 : $registered_name.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Results(account_status=" + this.getAccount_status() + ", registered_name=" + this.getRegistered_name() + ")";
    }
}
