/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.cart.dto;

import lombok.Generated;

public class AddToCartRequest {
    private Long menuItemId;
    private Long homemakerId;
    private Integer quantity = 1;
    private String specialInstructions;

    @Generated
    public Long getMenuItemId() {
        return this.menuItemId;
    }

    @Generated
    public Long getHomemakerId() {
        return this.homemakerId;
    }

    @Generated
    public Integer getQuantity() {
        return this.quantity;
    }

    @Generated
    public String getSpecialInstructions() {
        return this.specialInstructions;
    }

    @Generated
    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    @Generated
    public void setHomemakerId(Long homemakerId) {
        this.homemakerId = homemakerId;
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
        if (!(o instanceof AddToCartRequest)) {
            return false;
        }
        AddToCartRequest other = (AddToCartRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$menuItemId = this.getMenuItemId();
        Long other$menuItemId = other.getMenuItemId();
        if (this$menuItemId == null ? other$menuItemId != null : !((Object)this$menuItemId).equals(other$menuItemId)) {
            return false;
        }
        Long this$homemakerId = this.getHomemakerId();
        Long other$homemakerId = other.getHomemakerId();
        if (this$homemakerId == null ? other$homemakerId != null : !((Object)this$homemakerId).equals(other$homemakerId)) {
            return false;
        }
        Integer this$quantity = this.getQuantity();
        Integer other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !((Object)this$quantity).equals(other$quantity)) {
            return false;
        }
        String this$specialInstructions = this.getSpecialInstructions();
        String other$specialInstructions = other.getSpecialInstructions();
        return !(this$specialInstructions == null ? other$specialInstructions != null : !this$specialInstructions.equals(other$specialInstructions));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof AddToCartRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $menuItemId = this.getMenuItemId();
        result = result * 59 + ($menuItemId == null ? 43 : ((Object)$menuItemId).hashCode());
        Long $homemakerId = this.getHomemakerId();
        result = result * 59 + ($homemakerId == null ? 43 : ((Object)$homemakerId).hashCode());
        Integer $quantity = this.getQuantity();
        result = result * 59 + ($quantity == null ? 43 : ((Object)$quantity).hashCode());
        String $specialInstructions = this.getSpecialInstructions();
        result = result * 59 + ($specialInstructions == null ? 43 : $specialInstructions.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "AddToCartRequest(menuItemId=" + this.getMenuItemId() + ", homemakerId=" + this.getHomemakerId() + ", quantity=" + this.getQuantity() + ", specialInstructions=" + this.getSpecialInstructions() + ")";
    }

    @Generated
    public AddToCartRequest() {
    }

    @Generated
    public AddToCartRequest(Long menuItemId, Long homemakerId, Integer quantity, String specialInstructions) {
        this.menuItemId = menuItemId;
        this.homemakerId = homemakerId;
        this.quantity = quantity;
        this.specialInstructions = specialInstructions;
    }
}
