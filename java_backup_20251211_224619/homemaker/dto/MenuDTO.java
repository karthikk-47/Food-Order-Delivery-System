/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.homemaker.dto;

import com.foodapp.deliveryexecutive.homemaker.dto.MenuItemDTO;
import com.foodapp.deliveryexecutive.homemaker.entity.Menu;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Generated;

public class MenuDTO {
    private Long id;
    private Long homemakerId;
    private Menu.MenuStatus status;
    private LocalDateTime publishedDate;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private Boolean isRecurring;
    private String recurrencePattern;
    private List<String> cuisineTypes;
    private String description;
    private Double estimatedPrepTime;
    private Menu.MenuType menuType;
    private Integer minOrderQuantity;
    private Integer maxOrderQuantity;
    private Double averageRating;
    private Integer totalOrders;
    private Integer totalReviews;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<MenuItemDTO> items;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getHomemakerId() {
        return this.homemakerId;
    }

    @Generated
    public Menu.MenuStatus getStatus() {
        return this.status;
    }

    @Generated
    public LocalDateTime getPublishedDate() {
        return this.publishedDate;
    }

    @Generated
    public LocalDateTime getValidFrom() {
        return this.validFrom;
    }

    @Generated
    public LocalDateTime getValidUntil() {
        return this.validUntil;
    }

    @Generated
    public Boolean getIsRecurring() {
        return this.isRecurring;
    }

    @Generated
    public String getRecurrencePattern() {
        return this.recurrencePattern;
    }

    @Generated
    public List<String> getCuisineTypes() {
        return this.cuisineTypes;
    }

    @Generated
    public String getDescription() {
        return this.description;
    }

    @Generated
    public Double getEstimatedPrepTime() {
        return this.estimatedPrepTime;
    }

    @Generated
    public Menu.MenuType getMenuType() {
        return this.menuType;
    }

    @Generated
    public Integer getMinOrderQuantity() {
        return this.minOrderQuantity;
    }

    @Generated
    public Integer getMaxOrderQuantity() {
        return this.maxOrderQuantity;
    }

    @Generated
    public Double getAverageRating() {
        return this.averageRating;
    }

    @Generated
    public Integer getTotalOrders() {
        return this.totalOrders;
    }

    @Generated
    public Integer getTotalReviews() {
        return this.totalReviews;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Generated
    public List<MenuItemDTO> getItems() {
        return this.items;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setHomemakerId(Long homemakerId) {
        this.homemakerId = homemakerId;
    }

    @Generated
    public void setStatus(Menu.MenuStatus status) {
        this.status = status;
    }

    @Generated
    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Generated
    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    @Generated
    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    @Generated
    public void setIsRecurring(Boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    @Generated
    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }

    @Generated
    public void setCuisineTypes(List<String> cuisineTypes) {
        this.cuisineTypes = cuisineTypes;
    }

    @Generated
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated
    public void setEstimatedPrepTime(Double estimatedPrepTime) {
        this.estimatedPrepTime = estimatedPrepTime;
    }

    @Generated
    public void setMenuType(Menu.MenuType menuType) {
        this.menuType = menuType;
    }

    @Generated
    public void setMinOrderQuantity(Integer minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }

    @Generated
    public void setMaxOrderQuantity(Integer maxOrderQuantity) {
        this.maxOrderQuantity = maxOrderQuantity;
    }

    @Generated
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    @Generated
    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    @Generated
    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated
    public void setItems(List<MenuItemDTO> items) {
        this.items = items;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MenuDTO)) {
            return false;
        }
        MenuDTO other = (MenuDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$homemakerId = this.getHomemakerId();
        Long other$homemakerId = other.getHomemakerId();
        if (this$homemakerId == null ? other$homemakerId != null : !((Object)this$homemakerId).equals(other$homemakerId)) {
            return false;
        }
        Boolean this$isRecurring = this.getIsRecurring();
        Boolean other$isRecurring = other.getIsRecurring();
        if (this$isRecurring == null ? other$isRecurring != null : !((Object)this$isRecurring).equals(other$isRecurring)) {
            return false;
        }
        Double this$estimatedPrepTime = this.getEstimatedPrepTime();
        Double other$estimatedPrepTime = other.getEstimatedPrepTime();
        if (this$estimatedPrepTime == null ? other$estimatedPrepTime != null : !((Object)this$estimatedPrepTime).equals(other$estimatedPrepTime)) {
            return false;
        }
        Integer this$minOrderQuantity = this.getMinOrderQuantity();
        Integer other$minOrderQuantity = other.getMinOrderQuantity();
        if (this$minOrderQuantity == null ? other$minOrderQuantity != null : !((Object)this$minOrderQuantity).equals(other$minOrderQuantity)) {
            return false;
        }
        Integer this$maxOrderQuantity = this.getMaxOrderQuantity();
        Integer other$maxOrderQuantity = other.getMaxOrderQuantity();
        if (this$maxOrderQuantity == null ? other$maxOrderQuantity != null : !((Object)this$maxOrderQuantity).equals(other$maxOrderQuantity)) {
            return false;
        }
        Double this$averageRating = this.getAverageRating();
        Double other$averageRating = other.getAverageRating();
        if (this$averageRating == null ? other$averageRating != null : !((Object)this$averageRating).equals(other$averageRating)) {
            return false;
        }
        Integer this$totalOrders = this.getTotalOrders();
        Integer other$totalOrders = other.getTotalOrders();
        if (this$totalOrders == null ? other$totalOrders != null : !((Object)this$totalOrders).equals(other$totalOrders)) {
            return false;
        }
        Integer this$totalReviews = this.getTotalReviews();
        Integer other$totalReviews = other.getTotalReviews();
        if (this$totalReviews == null ? other$totalReviews != null : !((Object)this$totalReviews).equals(other$totalReviews)) {
            return false;
        }
        Menu.MenuStatus this$status = this.getStatus();
        Menu.MenuStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)((Object)this$status)).equals((Object)other$status)) {
            return false;
        }
        LocalDateTime this$publishedDate = this.getPublishedDate();
        LocalDateTime other$publishedDate = other.getPublishedDate();
        if (this$publishedDate == null ? other$publishedDate != null : !((Object)this$publishedDate).equals(other$publishedDate)) {
            return false;
        }
        LocalDateTime this$validFrom = this.getValidFrom();
        LocalDateTime other$validFrom = other.getValidFrom();
        if (this$validFrom == null ? other$validFrom != null : !((Object)this$validFrom).equals(other$validFrom)) {
            return false;
        }
        LocalDateTime this$validUntil = this.getValidUntil();
        LocalDateTime other$validUntil = other.getValidUntil();
        if (this$validUntil == null ? other$validUntil != null : !((Object)this$validUntil).equals(other$validUntil)) {
            return false;
        }
        String this$recurrencePattern = this.getRecurrencePattern();
        String other$recurrencePattern = other.getRecurrencePattern();
        if (this$recurrencePattern == null ? other$recurrencePattern != null : !this$recurrencePattern.equals(other$recurrencePattern)) {
            return false;
        }
        List<String> this$cuisineTypes = this.getCuisineTypes();
        List<String> other$cuisineTypes = other.getCuisineTypes();
        if (this$cuisineTypes == null ? other$cuisineTypes != null : !((Object)this$cuisineTypes).equals(other$cuisineTypes)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        Menu.MenuType this$menuType = this.getMenuType();
        Menu.MenuType other$menuType = other.getMenuType();
        if (this$menuType == null ? other$menuType != null : !((Object)((Object)this$menuType)).equals((Object)other$menuType)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !((Object)this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !((Object)this$updatedAt).equals(other$updatedAt)) {
            return false;
        }
        List<MenuItemDTO> this$items = this.getItems();
        List<MenuItemDTO> other$items = other.getItems();
        return !(this$items == null ? other$items != null : !((Object)this$items).equals(other$items));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof MenuDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $homemakerId = this.getHomemakerId();
        result = result * 59 + ($homemakerId == null ? 43 : ((Object)$homemakerId).hashCode());
        Boolean $isRecurring = this.getIsRecurring();
        result = result * 59 + ($isRecurring == null ? 43 : ((Object)$isRecurring).hashCode());
        Double $estimatedPrepTime = this.getEstimatedPrepTime();
        result = result * 59 + ($estimatedPrepTime == null ? 43 : ((Object)$estimatedPrepTime).hashCode());
        Integer $minOrderQuantity = this.getMinOrderQuantity();
        result = result * 59 + ($minOrderQuantity == null ? 43 : ((Object)$minOrderQuantity).hashCode());
        Integer $maxOrderQuantity = this.getMaxOrderQuantity();
        result = result * 59 + ($maxOrderQuantity == null ? 43 : ((Object)$maxOrderQuantity).hashCode());
        Double $averageRating = this.getAverageRating();
        result = result * 59 + ($averageRating == null ? 43 : ((Object)$averageRating).hashCode());
        Integer $totalOrders = this.getTotalOrders();
        result = result * 59 + ($totalOrders == null ? 43 : ((Object)$totalOrders).hashCode());
        Integer $totalReviews = this.getTotalReviews();
        result = result * 59 + ($totalReviews == null ? 43 : ((Object)$totalReviews).hashCode());
        Menu.MenuStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        LocalDateTime $publishedDate = this.getPublishedDate();
        result = result * 59 + ($publishedDate == null ? 43 : ((Object)$publishedDate).hashCode());
        LocalDateTime $validFrom = this.getValidFrom();
        result = result * 59 + ($validFrom == null ? 43 : ((Object)$validFrom).hashCode());
        LocalDateTime $validUntil = this.getValidUntil();
        result = result * 59 + ($validUntil == null ? 43 : ((Object)$validUntil).hashCode());
        String $recurrencePattern = this.getRecurrencePattern();
        result = result * 59 + ($recurrencePattern == null ? 43 : $recurrencePattern.hashCode());
        List<String> $cuisineTypes = this.getCuisineTypes();
        result = result * 59 + ($cuisineTypes == null ? 43 : ((Object)$cuisineTypes).hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        Menu.MenuType $menuType = this.getMenuType();
        result = result * 59 + ($menuType == null ? 43 : ((Object)((Object)$menuType)).hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        List<MenuItemDTO> $items = this.getItems();
        result = result * 59 + ($items == null ? 43 : ((Object)$items).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "MenuDTO(id=" + this.getId() + ", homemakerId=" + this.getHomemakerId() + ", status=" + String.valueOf((Object)this.getStatus()) + ", publishedDate=" + String.valueOf(this.getPublishedDate()) + ", validFrom=" + String.valueOf(this.getValidFrom()) + ", validUntil=" + String.valueOf(this.getValidUntil()) + ", isRecurring=" + this.getIsRecurring() + ", recurrencePattern=" + this.getRecurrencePattern() + ", cuisineTypes=" + String.valueOf(this.getCuisineTypes()) + ", description=" + this.getDescription() + ", estimatedPrepTime=" + this.getEstimatedPrepTime() + ", menuType=" + String.valueOf((Object)this.getMenuType()) + ", minOrderQuantity=" + this.getMinOrderQuantity() + ", maxOrderQuantity=" + this.getMaxOrderQuantity() + ", averageRating=" + this.getAverageRating() + ", totalOrders=" + this.getTotalOrders() + ", totalReviews=" + this.getTotalReviews() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ", items=" + String.valueOf(this.getItems()) + ")";
    }

    @Generated
    public MenuDTO() {
    }

    @Generated
    public MenuDTO(Long id, Long homemakerId, Menu.MenuStatus status, LocalDateTime publishedDate, LocalDateTime validFrom, LocalDateTime validUntil, Boolean isRecurring, String recurrencePattern, List<String> cuisineTypes, String description, Double estimatedPrepTime, Menu.MenuType menuType, Integer minOrderQuantity, Integer maxOrderQuantity, Double averageRating, Integer totalOrders, Integer totalReviews, LocalDateTime createdAt, LocalDateTime updatedAt, List<MenuItemDTO> items) {
        this.id = id;
        this.homemakerId = homemakerId;
        this.status = status;
        this.publishedDate = publishedDate;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.isRecurring = isRecurring;
        this.recurrencePattern = recurrencePattern;
        this.cuisineTypes = cuisineTypes;
        this.description = description;
        this.estimatedPrepTime = estimatedPrepTime;
        this.menuType = menuType;
        this.minOrderQuantity = minOrderQuantity;
        this.maxOrderQuantity = maxOrderQuantity;
        this.averageRating = averageRating;
        this.totalOrders = totalOrders;
        this.totalReviews = totalReviews;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.items = items;
    }
}
