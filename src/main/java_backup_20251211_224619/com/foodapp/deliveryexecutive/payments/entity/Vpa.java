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
public class Vpa {
    private String username;
    private String handle;
    private String address;

    @Generated
    public Vpa() {
    }

    @Generated
    public String getUsername() {
        return this.username;
    }

    @Generated
    public String getHandle() {
        return this.handle;
    }

    @Generated
    public String getAddress() {
        return this.address;
    }

    @Generated
    public void setUsername(String username) {
        this.username = username;
    }

    @Generated
    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Generated
    public void setAddress(String address) {
        this.address = address;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Vpa)) {
            return false;
        }
        Vpa other = (Vpa)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$username = this.getUsername();
        String other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
            return false;
        }
        String this$handle = this.getHandle();
        String other$handle = other.getHandle();
        if (this$handle == null ? other$handle != null : !this$handle.equals(other$handle)) {
            return false;
        }
        String this$address = this.getAddress();
        String other$address = other.getAddress();
        return !(this$address == null ? other$address != null : !this$address.equals(other$address));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Vpa;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        String $handle = this.getHandle();
        result = result * 59 + ($handle == null ? 43 : $handle.hashCode());
        String $address = this.getAddress();
        result = result * 59 + ($address == null ? 43 : $address.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Vpa(username=" + this.getUsername() + ", handle=" + this.getHandle() + ", address=" + this.getAddress() + ")";
    }
}
