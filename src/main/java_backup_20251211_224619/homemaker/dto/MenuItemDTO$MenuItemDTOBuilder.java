/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.homemaker.dto;

import com.foodapp.deliveryexecutive.homemaker.dto.MenuItemDTO;
import java.util.List;
import lombok.Generated;

@Generated
public static class MenuItemDTO.MenuItemDTOBuilder {
    @Generated
    private Long id;
    @Generated
    private String itemName;
    @Generated
    private String description;
    @Generated
    private String itemImage;
    @Generated
    private Double price;
    @Generated
    private Double discountedPrice;
    @Generated
    private Integer quantity;
    @Generated
    private Boolean isVegetarian;
    @Generated
    private Boolean isSpicy;
    @Generated
    private Boolean containsNuts;
    @Generated
    private Boolean containsDairy;
    @Generated
    private List<String> ingredients;
    @Generated
    private String preparationNotes;
    @Generated
    private Double estimatedPrepTime;
    @Generated
    private Integer calories;
    @Generated
    private String servingSize;
    @Generated
    private Boolean isAvailable;
    @Generated
    private Integer soldCount;

    @Generated
    MenuItemDTO.MenuItemDTOBuilder() {
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder itemImage(String itemImage) {
        this.itemImage = itemImage;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder price(Double price) {
        this.price = price;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder discountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder isVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder isSpicy(Boolean isSpicy) {
        this.isSpicy = isSpicy;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder containsNuts(Boolean containsNuts) {
        this.containsNuts = containsNuts;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder containsDairy(Boolean containsDairy) {
        this.containsDairy = containsDairy;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder ingredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder preparationNotes(String preparationNotes) {
        this.preparationNotes = preparationNotes;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder estimatedPrepTime(Double estimatedPrepTime) {
        this.estimatedPrepTime = estimatedPrepTime;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder calories(Integer calories) {
        this.calories = calories;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder servingSize(String servingSize) {
        this.servingSize = servingSize;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder isAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    @Generated
    public MenuItemDTO.MenuItemDTOBuilder soldCount(Integer soldCount) {
        this.soldCount = soldCount;
        return this;
    }

    @Generated
    public MenuItemDTO build() {
        return new MenuItemDTO(this.id, this.itemName, this.description, this.itemImage, this.price, this.discountedPrice, this.quantity, this.isVegetarian, this.isSpicy, this.containsNuts, this.containsDairy, this.ingredients, this.preparationNotes, this.estimatedPrepTime, this.calories, this.servingSize, this.isAvailable, this.soldCount);
    }

    @Generated
    public String toString() {
        return "MenuItemDTO.MenuItemDTOBuilder(id=" + this.id + ", itemName=" + this.itemName + ", description=" + this.description + ", itemImage=" + this.itemImage + ", price=" + this.price + ", discountedPrice=" + this.discountedPrice + ", quantity=" + this.quantity + ", isVegetarian=" + this.isVegetarian + ", isSpicy=" + this.isSpicy + ", containsNuts=" + this.containsNuts + ", containsDairy=" + this.containsDairy + ", ingredients=" + String.valueOf(this.ingredients) + ", preparationNotes=" + this.preparationNotes + ", estimatedPrepTime=" + this.estimatedPrepTime + ", calories=" + this.calories + ", servingSize=" + this.servingSize + ", isAvailable=" + this.isAvailable + ", soldCount=" + this.soldCount + ")";
    }
}
