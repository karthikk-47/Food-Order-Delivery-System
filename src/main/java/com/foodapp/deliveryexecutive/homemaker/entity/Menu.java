/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.CascadeType
 *  jakarta.persistence.ElementCollection
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.OneToMany
 *  jakarta.persistence.PrePersist
 *  jakarta.persistence.PreUpdate
 *  jakarta.persistence.Table
 *  jakarta.persistence.Transient
 *  lombok.Generated
 *  org.hibernate.annotations.Fetch
 *  org.hibernate.annotations.FetchMode
 */
package com.foodapp.deliveryexecutive.homemaker.entity;

import com.foodapp.deliveryexecutive.filestorage.model.FileInfo;
import com.foodapp.deliveryexecutive.homemaker.entity.MenuItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="menus")
public class Menu {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long homemakerId;
    @Enumerated(value=EnumType.STRING)
    private MenuStatus status;
    private LocalDateTime publishedDate;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private Boolean isRecurring;
    private String recurrencePattern;
    @ElementCollection
    private List<String> cuisineTypes;
    private String description;
    private Double estimatedPrepTime;
    @Enumerated(value=EnumType.STRING)
    private MenuType menuType;
    private Integer minOrderQuantity;
    private Integer maxOrderQuantity;
    private Double averageRating;
    private Integer totalOrders;
    private Integer totalReviews;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy="menu", cascade={CascadeType.ALL}, orphanRemoval=true)
    @Fetch(value=FetchMode.SUBSELECT)
    private List<MenuItem> items = new ArrayList<MenuItem>();
    @Transient
    private List<FileInfo> photos = new ArrayList<FileInfo>();
    @Transient
    private FileInfo primaryPhoto;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = MenuStatus.DRAFT;
        this.totalOrders = 0;
        this.totalReviews = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getHomemakerId() {
        return this.homemakerId;
    }

    @Generated
    public MenuStatus getStatus() {
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
    public MenuType getMenuType() {
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
    public List<MenuItem> getItems() {
        return this.items;
    }

    @Generated
    public List<FileInfo> getPhotos() {
        return this.photos;
    }

    @Generated
    public FileInfo getPrimaryPhoto() {
        return this.primaryPhoto;
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
    public void setStatus(MenuStatus status) {
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
    public void setMenuType(MenuType menuType) {
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
    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    @Generated
    public void setPhotos(List<FileInfo> photos) {
        this.photos = photos;
    }

    @Generated
    public void setPrimaryPhoto(FileInfo primaryPhoto) {
        this.primaryPhoto = primaryPhoto;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }
        Menu other = (Menu)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Long this$homemakerId = this.getHomemakerId();
        Long other$homemakerId = other.getHomemakerId();
        if (this$homemakerId == null ? other$homemakerId != null : !(this$homemakerId).equals(other$homemakerId)) {
            return false;
        }
        Boolean this$isRecurring = this.getIsRecurring();
        Boolean other$isRecurring = other.getIsRecurring();
        if (this$isRecurring == null ? other$isRecurring != null : !(this$isRecurring).equals(other$isRecurring)) {
            return false;
        }
        Double this$estimatedPrepTime = this.getEstimatedPrepTime();
        Double other$estimatedPrepTime = other.getEstimatedPrepTime();
        if (this$estimatedPrepTime == null ? other$estimatedPrepTime != null : !(this$estimatedPrepTime).equals(other$estimatedPrepTime)) {
            return false;
        }
        Integer this$minOrderQuantity = this.getMinOrderQuantity();
        Integer other$minOrderQuantity = other.getMinOrderQuantity();
        if (this$minOrderQuantity == null ? other$minOrderQuantity != null : !(this$minOrderQuantity).equals(other$minOrderQuantity)) {
            return false;
        }
        Integer this$maxOrderQuantity = this.getMaxOrderQuantity();
        Integer other$maxOrderQuantity = other.getMaxOrderQuantity();
        if (this$maxOrderQuantity == null ? other$maxOrderQuantity != null : !(this$maxOrderQuantity).equals(other$maxOrderQuantity)) {
            return false;
        }
        Double this$averageRating = this.getAverageRating();
        Double other$averageRating = other.getAverageRating();
        if (this$averageRating == null ? other$averageRating != null : !(this$averageRating).equals(other$averageRating)) {
            return false;
        }
        Integer this$totalOrders = this.getTotalOrders();
        Integer other$totalOrders = other.getTotalOrders();
        if (this$totalOrders == null ? other$totalOrders != null : !(this$totalOrders).equals(other$totalOrders)) {
            return false;
        }
        Integer this$totalReviews = this.getTotalReviews();
        Integer other$totalReviews = other.getTotalReviews();
        if (this$totalReviews == null ? other$totalReviews != null : !(this$totalReviews).equals(other$totalReviews)) {
            return false;
        }
        MenuStatus this$status = this.getStatus();
        MenuStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)(this$status)).equals(other$status)) {
            return false;
        }
        LocalDateTime this$publishedDate = this.getPublishedDate();
        LocalDateTime other$publishedDate = other.getPublishedDate();
        if (this$publishedDate == null ? other$publishedDate != null : !(this$publishedDate).equals(other$publishedDate)) {
            return false;
        }
        LocalDateTime this$validFrom = this.getValidFrom();
        LocalDateTime other$validFrom = other.getValidFrom();
        if (this$validFrom == null ? other$validFrom != null : !(this$validFrom).equals(other$validFrom)) {
            return false;
        }
        LocalDateTime this$validUntil = this.getValidUntil();
        LocalDateTime other$validUntil = other.getValidUntil();
        if (this$validUntil == null ? other$validUntil != null : !(this$validUntil).equals(other$validUntil)) {
            return false;
        }
        String this$recurrencePattern = this.getRecurrencePattern();
        String other$recurrencePattern = other.getRecurrencePattern();
        if (this$recurrencePattern == null ? other$recurrencePattern != null : !this$recurrencePattern.equals(other$recurrencePattern)) {
            return false;
        }
        List<String> this$cuisineTypes = this.getCuisineTypes();
        List<String> other$cuisineTypes = other.getCuisineTypes();
        if (this$cuisineTypes == null ? other$cuisineTypes != null : !(this$cuisineTypes).equals(other$cuisineTypes)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        MenuType this$menuType = this.getMenuType();
        MenuType other$menuType = other.getMenuType();
        if (this$menuType == null ? other$menuType != null : !((Object)(this$menuType)).equals(other$menuType)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !(this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !(this$updatedAt).equals(other$updatedAt)) {
            return false;
        }
        List<MenuItem> this$items = this.getItems();
        List<MenuItem> other$items = other.getItems();
        if (this$items == null ? other$items != null : !(this$items).equals(other$items)) {
            return false;
        }
        List<FileInfo> this$photos = this.getPhotos();
        List<FileInfo> other$photos = other.getPhotos();
        if (this$photos == null ? other$photos != null : !(this$photos).equals(other$photos)) {
            return false;
        }
        FileInfo this$primaryPhoto = this.getPrimaryPhoto();
        FileInfo other$primaryPhoto = other.getPrimaryPhoto();
        return !(this$primaryPhoto == null ? other$primaryPhoto != null : !(this$primaryPhoto).equals(other$primaryPhoto));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Menu;
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
        MenuStatus $status = this.getStatus();
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
        MenuType $menuType = this.getMenuType();
        result = result * 59 + ($menuType == null ? 43 : ((Object)((Object)$menuType)).hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        List<MenuItem> $items = this.getItems();
        result = result * 59 + ($items == null ? 43 : ((Object)$items).hashCode());
        List<FileInfo> $photos = this.getPhotos();
        result = result * 59 + ($photos == null ? 43 : ((Object)$photos).hashCode());
        FileInfo $primaryPhoto = this.getPrimaryPhoto();
        result = result * 59 + ($primaryPhoto == null ? 43 : ((Object)$primaryPhoto).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Menu(id=" + this.getId() + ", homemakerId=" + this.getHomemakerId() + ", status=" + String.valueOf(this.getStatus()) + ", publishedDate=" + String.valueOf(this.getPublishedDate()) + ", validFrom=" + String.valueOf(this.getValidFrom()) + ", validUntil=" + String.valueOf(this.getValidUntil()) + ", isRecurring=" + this.getIsRecurring() + ", recurrencePattern=" + this.getRecurrencePattern() + ", cuisineTypes=" + String.valueOf(this.getCuisineTypes()) + ", description=" + this.getDescription() + ", estimatedPrepTime=" + this.getEstimatedPrepTime() + ", menuType=" + String.valueOf(this.getMenuType()) + ", minOrderQuantity=" + this.getMinOrderQuantity() + ", maxOrderQuantity=" + this.getMaxOrderQuantity() + ", averageRating=" + this.getAverageRating() + ", totalOrders=" + this.getTotalOrders() + ", totalReviews=" + this.getTotalReviews() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ", items=" + String.valueOf(this.getItems()) + ", photos=" + String.valueOf(this.getPhotos()) + ", primaryPhoto=" + String.valueOf(this.getPrimaryPhoto()) + ")";
    }

    @Generated
    public Menu() {
    }

    @Generated
    public Menu(Long id, Long homemakerId, MenuStatus status, LocalDateTime publishedDate, LocalDateTime validFrom, LocalDateTime validUntil, Boolean isRecurring, String recurrencePattern, List<String> cuisineTypes, String description, Double estimatedPrepTime, MenuType menuType, Integer minOrderQuantity, Integer maxOrderQuantity, Double averageRating, Integer totalOrders, Integer totalReviews, LocalDateTime createdAt, LocalDateTime updatedAt, List<MenuItem> items, List<FileInfo> photos, FileInfo primaryPhoto) {
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
        this.photos = photos;
        this.primaryPhoto = primaryPhoto;
    }

    public static enum MenuStatus {
        ACTIVE,
        INACTIVE,
        DRAFT,
        ARCHIVED;

    }

    public static enum MenuType {
        SECRET_MENU,
        SIGNATURE_DISH,
        SUBSCRIPTION;

    }
}
