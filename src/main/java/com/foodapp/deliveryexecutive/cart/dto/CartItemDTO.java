package com.foodapp.deliveryexecutive.cart.dto;

import lombok.Generated;

public class CartItemDTO {
    private Long id;
    private Long menuItemId;
    private String itemName;
    private String description;
    private String imageUrl;
    private Double price;
    private Integer quantity;
    private Double subtotal;
    private Long homemakerId;
    private String homemakerName;
    private String specialInstructions;
    private Boolean isVegetarian;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getMenuItemId() {
        return this.menuItemId;
    }

    @Generated
    public String getItemName() {
        return this.itemName;
    }

    @Generated
    public String getDescription() {
        return this.description;
    }

    @Generated
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Generated
    public Double getPrice() {
        return this.price;
    }

    @Generated
    public Integer getQuantity() {
        return this.quantity;
    }

    @Generated
    public Double getSubtotal() {
        return this.subtotal;
    }

    @Generated
    public Long getHomemakerId() {
        return this.homemakerId;
    }

    @Generated
    public String getHomemakerName() {
        return this.homemakerName;
    }

    @Generated
    public String getSpecialInstructions() {
        return this.specialInstructions;
    }

    @Generated
    public Boolean getIsVegetarian() {
        return this.isVegetarian;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    @Generated
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Generated
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Generated
    public void setPrice(Double price) {
        this.price = price;
    }

    @Generated
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Generated
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Generated
    public void setHomemakerId(Long homemakerId) {
        this.homemakerId = homemakerId;
    }

    @Generated
    public void setHomemakerName(String homemakerName) {
        this.homemakerName = homemakerName;
    }

    @Generated
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    @Generated
    public void setIsVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CartItemDTO)) {
            return false;
        }
        CartItemDTO other = (CartItemDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Long this$menuItemId = this.getMenuItemId();
        Long other$menuItemId = other.getMenuItemId();
        if (this$menuItemId == null ? other$menuItemId != null : !(this$menuItemId).equals(other$menuItemId)) {
            return false;
        }
        Double this$price = this.getPrice();
        Double other$price = other.getPrice();
        if (this$price == null ? other$price != null : !(this$price).equals(other$price)) {
            return false;
        }
        Integer this$quantity = this.getQuantity();
        Integer other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !(this$quantity).equals(other$quantity)) {
            return false;
        }
        Double this$subtotal = this.getSubtotal();
        Double other$subtotal = other.getSubtotal();
        if (this$subtotal == null ? other$subtotal != null : !(this$subtotal).equals(other$subtotal)) {
            return false;
        }
        Long this$homemakerId = this.getHomemakerId();
        Long other$homemakerId = other.getHomemakerId();
        if (this$homemakerId == null ? other$homemakerId != null : !(this$homemakerId).equals(other$homemakerId)) {
            return false;
        }
        Boolean this$isVegetarian = this.getIsVegetarian();
        Boolean other$isVegetarian = other.getIsVegetarian();
        if (this$isVegetarian == null ? other$isVegetarian != null : !(this$isVegetarian).equals(other$isVegetarian)) {
            return false;
        }
        String this$itemName = this.getItemName();
        String other$itemName = other.getItemName();
        if (this$itemName == null ? other$itemName != null : !this$itemName.equals(other$itemName)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$imageUrl = this.getImageUrl();
        String other$imageUrl = other.getImageUrl();
        if (this$imageUrl == null ? other$imageUrl != null : !this$imageUrl.equals(other$imageUrl)) {
            return false;
        }
        String this$homemakerName = this.getHomemakerName();
        String other$homemakerName = other.getHomemakerName();
        if (this$homemakerName == null ? other$homemakerName != null : !this$homemakerName.equals(other$homemakerName)) {
            return false;
        }
        String this$specialInstructions = this.getSpecialInstructions();
        String other$specialInstructions = other.getSpecialInstructions();
        return !(this$specialInstructions == null ? other$specialInstructions != null : !this$specialInstructions.equals(other$specialInstructions));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CartItemDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $menuItemId = this.getMenuItemId();
        result = result * 59 + ($menuItemId == null ? 43 : ((Object)$menuItemId).hashCode());
        Double $price = this.getPrice();
        result = result * 59 + ($price == null ? 43 : ((Object)$price).hashCode());
        Integer $quantity = this.getQuantity();
        result = result * 59 + ($quantity == null ? 43 : ((Object)$quantity).hashCode());
        Double $subtotal = this.getSubtotal();
        result = result * 59 + ($subtotal == null ? 43 : ((Object)$subtotal).hashCode());
        Long $homemakerId = this.getHomemakerId();
        result = result * 59 + ($homemakerId == null ? 43 : ((Object)$homemakerId).hashCode());
        Boolean $isVegetarian = this.getIsVegetarian();
        result = result * 59 + ($isVegetarian == null ? 43 : ((Object)$isVegetarian).hashCode());
        String $itemName = this.getItemName();
        result = result * 59 + ($itemName == null ? 43 : $itemName.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $imageUrl = this.getImageUrl();
        result = result * 59 + ($imageUrl == null ? 43 : $imageUrl.hashCode());
        String $homemakerName = this.getHomemakerName();
        result = result * 59 + ($homemakerName == null ? 43 : $homemakerName.hashCode());
        String $specialInstructions = this.getSpecialInstructions();
        result = result * 59 + ($specialInstructions == null ? 43 : $specialInstructions.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "CartItemDTO(id=" + this.getId() + ", menuItemId=" + this.getMenuItemId() + ", itemName=" + this.getItemName() + ", description=" + this.getDescription() + ", imageUrl=" + this.getImageUrl() + ", price=" + this.getPrice() + ", quantity=" + this.getQuantity() + ", subtotal=" + this.getSubtotal() + ", homemakerId=" + this.getHomemakerId() + ", homemakerName=" + this.getHomemakerName() + ", specialInstructions=" + this.getSpecialInstructions() + ", isVegetarian=" + this.getIsVegetarian() + ")";
    }

    @Generated
    public CartItemDTO() {
    }

    @Generated
    public CartItemDTO(Long id, Long menuItemId, String itemName, String description, String imageUrl, Double price, Integer quantity, Double subtotal, Long homemakerId, String homemakerName, String specialInstructions, Boolean isVegetarian) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.homemakerId = homemakerId;
        this.homemakerName = homemakerName;
        this.specialInstructions = specialInstructions;
        this.isVegetarian = isVegetarian;
    }
}
