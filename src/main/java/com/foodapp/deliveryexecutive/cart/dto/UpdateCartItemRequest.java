/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.cart.dto;

import lombok.Generated;

public class UpdateCartItemRequest {
    private Integer quantity;
    private String specialInstructions;

    @Generated
    public Integer getQuantity() {
        return this.quantity;
    }

    @Generated
    public String getSpecialInstructions() {
        return this.specialInstructions;
    }

    @Generated
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Generated
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UpdateCartItemRequest)) {
            return false;
        }
        UpdateCartItemRequest other = (UpdateCartItemRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Integer this$quantity = this.getQuantity();
        Integer other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !(this$quantity).equals(other$quantity)) {
            return false;
        }
        String this$specialInstructions = this.getSpecialInstructions();
        String other$specialInstructions = other.getSpecialInstructions();
        return !(this$specialInstructions == null ? other$specialInstructions != null : !this$specialInstructions.equals(other$specialInstructions));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof UpdateCartItemRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $quantity = this.getQuantity();
        result = result * 59 + ($quantity == null ? 43 : ((Object)$quantity).hashCode());
        String $specialInstructions = this.getSpecialInstructions();
        result = result * 59 + ($specialInstructions == null ? 43 : $specialInstructions.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "UpdateCartItemRequest(quantity=" + this.getQuantity() + ", specialInstructions=" + this.getSpecialInstructions() + ")";
    }

    @Generated
    public UpdateCartItemRequest() {
    }

    @Generated
    public UpdateCartItemRequest(Integer quantity, String specialInstructions) {
        this.quantity = quantity;
        this.specialInstructions = specialInstructions;
    }
}
