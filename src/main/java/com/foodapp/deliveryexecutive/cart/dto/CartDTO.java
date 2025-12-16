/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.cart.dto;

import com.foodapp.deliveryexecutive.cart.dto.CartItemDTO;
import java.util.List;
import lombok.Generated;

public class CartDTO {
    private Long id;
    private Long userId;
    private List<CartItemDTO> items;
    private Double totalAmount;
    private Integer itemCount;
    private Double deliveryFee;
    private Double grandTotal;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getUserId() {
        return this.userId;
    }

    @Generated
    public List<CartItemDTO> getItems() {
        return this.items;
    }

    @Generated
    public Double getTotalAmount() {
        return this.totalAmount;
    }

    @Generated
    public Integer getItemCount() {
        return this.itemCount;
    }

    @Generated
    public Double getDeliveryFee() {
        return this.deliveryFee;
    }

    @Generated
    public Double getGrandTotal() {
        return this.grandTotal;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated
    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    @Generated
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Generated
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    @Generated
    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Generated
    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CartDTO)) {
            return false;
        }
        CartDTO other = (CartDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !(this$userId).equals(other$userId)) {
            return false;
        }
        Double this$totalAmount = this.getTotalAmount();
        Double other$totalAmount = other.getTotalAmount();
        if (this$totalAmount == null ? other$totalAmount != null : !(this$totalAmount).equals(other$totalAmount)) {
            return false;
        }
        Integer this$itemCount = this.getItemCount();
        Integer other$itemCount = other.getItemCount();
        if (this$itemCount == null ? other$itemCount != null : !(this$itemCount).equals(other$itemCount)) {
            return false;
        }
        Double this$deliveryFee = this.getDeliveryFee();
        Double other$deliveryFee = other.getDeliveryFee();
        if (this$deliveryFee == null ? other$deliveryFee != null : !(this$deliveryFee).equals(other$deliveryFee)) {
            return false;
        }
        Double this$grandTotal = this.getGrandTotal();
        Double other$grandTotal = other.getGrandTotal();
        if (this$grandTotal == null ? other$grandTotal != null : !(this$grandTotal).equals(other$grandTotal)) {
            return false;
        }
        List<CartItemDTO> this$items = this.getItems();
        List<CartItemDTO> other$items = other.getItems();
        return !(this$items == null ? other$items != null : !(this$items).equals(other$items));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CartDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Double $totalAmount = this.getTotalAmount();
        result = result * 59 + ($totalAmount == null ? 43 : ((Object)$totalAmount).hashCode());
        Integer $itemCount = this.getItemCount();
        result = result * 59 + ($itemCount == null ? 43 : ((Object)$itemCount).hashCode());
        Double $deliveryFee = this.getDeliveryFee();
        result = result * 59 + ($deliveryFee == null ? 43 : ((Object)$deliveryFee).hashCode());
        Double $grandTotal = this.getGrandTotal();
        result = result * 59 + ($grandTotal == null ? 43 : ((Object)$grandTotal).hashCode());
        List<CartItemDTO> $items = this.getItems();
        result = result * 59 + ($items == null ? 43 : ((Object)$items).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "CartDTO(id=" + this.getId() + ", userId=" + this.getUserId() + ", items=" + String.valueOf(this.getItems()) + ", totalAmount=" + this.getTotalAmount() + ", itemCount=" + this.getItemCount() + ", deliveryFee=" + this.getDeliveryFee() + ", grandTotal=" + this.getGrandTotal() + ")";
    }

    @Generated
    public CartDTO() {
    }

    @Generated
    public CartDTO(Long id, Long userId, List<CartItemDTO> items, Double totalAmount, Integer itemCount, Double deliveryFee, Double grandTotal) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.itemCount = itemCount;
        this.deliveryFee = deliveryFee;
        this.grandTotal = grandTotal;
    }
}
