/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Generated;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlusCode {
    private String compound_code;
    private String global_code;

    @Generated
    public PlusCode() {
    }

    @Generated
    public String getCompound_code() {
        return this.compound_code;
    }

    @Generated
    public String getGlobal_code() {
        return this.global_code;
    }

    @Generated
    public void setCompound_code(String compound_code) {
        this.compound_code = compound_code;
    }

    @Generated
    public void setGlobal_code(String global_code) {
        this.global_code = global_code;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PlusCode)) {
            return false;
        }
        PlusCode other = (PlusCode)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$compound_code = this.getCompound_code();
        String other$compound_code = other.getCompound_code();
        if (this$compound_code == null ? other$compound_code != null : !this$compound_code.equals(other$compound_code)) {
            return false;
        }
        String this$global_code = this.getGlobal_code();
        String other$global_code = other.getGlobal_code();
        return !(this$global_code == null ? other$global_code != null : !this$global_code.equals(other$global_code));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof PlusCode;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $compound_code = this.getCompound_code();
        result = result * 59 + ($compound_code == null ? 43 : $compound_code.hashCode());
        String $global_code = this.getGlobal_code();
        result = result * 59 + ($global_code == null ? 43 : $global_code.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "PlusCode(compound_code=" + this.getCompound_code() + ", global_code=" + this.getGlobal_code() + ")";
    }
}
