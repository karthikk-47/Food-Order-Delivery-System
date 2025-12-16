/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.ElementCollection
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.JoinColumn
 *  jakarta.persistence.ManyToOne
 *  jakarta.persistence.Table
 *  jakarta.persistence.Transient
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.homemaker.entity;

import com.foodapp.deliveryexecutive.filestorage.model.FileInfo;
import com.foodapp.deliveryexecutive.homemaker.entity.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

@Entity
@Table(name="menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="menu_id")
    private Menu menu;
    private String itemName;
    private String description;
    @Transient
    private List<FileInfo> photos = new ArrayList<FileInfo>();
    @Transient
    private FileInfo primaryPhoto;
    @Column(name="primary_photo_url")
    private String primaryPhotoUrl;
    private Double price;
    private Double discountedPrice;
    private Integer quantity;
    private Boolean isVegetarian;
    private Boolean isSpicy;
    private Boolean containsNuts;
    private Boolean containsDairy;
    @ElementCollection
    private List<String> ingredients;
    private String preparationNotes;
    private Double estimatedPrepTime;
    private Integer calories;
    private String servingSize;
    private Boolean isAvailable;
    private Integer soldCount;

    public MenuItem(String itemName, Double price) {
        this.itemName = itemName;
        this.price = price;
        this.isAvailable = true;
        this.soldCount = 0;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Menu getMenu() {
        return this.menu;
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
    public List<FileInfo> getPhotos() {
        return this.photos;
    }

    @Generated
    public FileInfo getPrimaryPhoto() {
        return this.primaryPhoto;
    }

    @Generated
    public String getPrimaryPhotoUrl() {
        return this.primaryPhotoUrl;
    }

    @Generated
    public Double getPrice() {
        return this.price;
    }

    @Generated
    public Double getDiscountedPrice() {
        return this.discountedPrice;
    }

    @Generated
    public Integer getQuantity() {
        return this.quantity;
    }

    @Generated
    public Boolean getIsVegetarian() {
        return this.isVegetarian;
    }

    @Generated
    public Boolean getIsSpicy() {
        return this.isSpicy;
    }

    @Generated
    public Boolean getContainsNuts() {
        return this.containsNuts;
    }

    @Generated
    public Boolean getContainsDairy() {
        return this.containsDairy;
    }

    @Generated
    public List<String> getIngredients() {
        return this.ingredients;
    }

    @Generated
    public String getPreparationNotes() {
        return this.preparationNotes;
    }

    @Generated
    public Double getEstimatedPrepTime() {
        return this.estimatedPrepTime;
    }

    @Generated
    public Integer getCalories() {
        return this.calories;
    }

    @Generated
    public String getServingSize() {
        return this.servingSize;
    }

    @Generated
    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    @Generated
    public Integer getSoldCount() {
        return this.soldCount;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setMenu(Menu menu) {
        this.menu = menu;
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
    public void setPhotos(List<FileInfo> photos) {
        this.photos = photos;
    }

    @Generated
    public void setPrimaryPhoto(FileInfo primaryPhoto) {
        this.primaryPhoto = primaryPhoto;
    }

    @Generated
    public void setPrimaryPhotoUrl(String primaryPhotoUrl) {
        this.primaryPhotoUrl = primaryPhotoUrl;
    }

    @Generated
    public void setPrice(Double price) {
        this.price = price;
    }

    @Generated
    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Generated
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Generated
    public void setIsVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    @Generated
    public void setIsSpicy(Boolean isSpicy) {
        this.isSpicy = isSpicy;
    }

    @Generated
    public void setContainsNuts(Boolean containsNuts) {
        this.containsNuts = containsNuts;
    }

    @Generated
    public void setContainsDairy(Boolean containsDairy) {
        this.containsDairy = containsDairy;
    }

    @Generated
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Generated
    public void setPreparationNotes(String preparationNotes) {
        this.preparationNotes = preparationNotes;
    }

    @Generated
    public void setEstimatedPrepTime(Double estimatedPrepTime) {
        this.estimatedPrepTime = estimatedPrepTime;
    }

    @Generated
    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Generated
    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    @Generated
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Generated
    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Double this$price = this.getPrice();
        Double other$price = other.getPrice();
        if (this$price == null ? other$price != null : !(this$price).equals(other$price)) {
            return false;
        }
        Double this$discountedPrice = this.getDiscountedPrice();
        Double other$discountedPrice = other.getDiscountedPrice();
        if (this$discountedPrice == null ? other$discountedPrice != null : !(this$discountedPrice).equals(other$discountedPrice)) {
            return false;
        }
        Integer this$quantity = this.getQuantity();
        Integer other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !(this$quantity).equals(other$quantity)) {
            return false;
        }
        Boolean this$isVegetarian = this.getIsVegetarian();
        Boolean other$isVegetarian = other.getIsVegetarian();
        if (this$isVegetarian == null ? other$isVegetarian != null : !(this$isVegetarian).equals(other$isVegetarian)) {
            return false;
        }
        Boolean this$isSpicy = this.getIsSpicy();
        Boolean other$isSpicy = other.getIsSpicy();
        if (this$isSpicy == null ? other$isSpicy != null : !(this$isSpicy).equals(other$isSpicy)) {
            return false;
        }
        Boolean this$containsNuts = this.getContainsNuts();
        Boolean other$containsNuts = other.getContainsNuts();
        if (this$containsNuts == null ? other$containsNuts != null : !(this$containsNuts).equals(other$containsNuts)) {
            return false;
        }
        Boolean this$containsDairy = this.getContainsDairy();
        Boolean other$containsDairy = other.getContainsDairy();
        if (this$containsDairy == null ? other$containsDairy != null : !(this$containsDairy).equals(other$containsDairy)) {
            return false;
        }
        Double this$estimatedPrepTime = this.getEstimatedPrepTime();
        Double other$estimatedPrepTime = other.getEstimatedPrepTime();
        if (this$estimatedPrepTime == null ? other$estimatedPrepTime != null : !(this$estimatedPrepTime).equals(other$estimatedPrepTime)) {
            return false;
        }
        Integer this$calories = this.getCalories();
        Integer other$calories = other.getCalories();
        if (this$calories == null ? other$calories != null : !(this$calories).equals(other$calories)) {
            return false;
        }
        Boolean this$isAvailable = this.getIsAvailable();
        Boolean other$isAvailable = other.getIsAvailable();
        if (this$isAvailable == null ? other$isAvailable != null : !(this$isAvailable).equals(other$isAvailable)) {
            return false;
        }
        Integer this$soldCount = this.getSoldCount();
        Integer other$soldCount = other.getSoldCount();
        if (this$soldCount == null ? other$soldCount != null : !(this$soldCount).equals(other$soldCount)) {
            return false;
        }
        Menu this$menu = this.getMenu();
        Menu other$menu = other.getMenu();
        if (this$menu == null ? other$menu != null : !(this$menu).equals(other$menu)) {
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
        List<FileInfo> this$photos = this.getPhotos();
        List<FileInfo> other$photos = other.getPhotos();
        if (this$photos == null ? other$photos != null : !(this$photos).equals(other$photos)) {
            return false;
        }
        FileInfo this$primaryPhoto = this.getPrimaryPhoto();
        FileInfo other$primaryPhoto = other.getPrimaryPhoto();
        if (this$primaryPhoto == null ? other$primaryPhoto != null : !(this$primaryPhoto).equals(other$primaryPhoto)) {
            return false;
        }
        String this$primaryPhotoUrl = this.getPrimaryPhotoUrl();
        String other$primaryPhotoUrl = other.getPrimaryPhotoUrl();
        if (this$primaryPhotoUrl == null ? other$primaryPhotoUrl != null : !this$primaryPhotoUrl.equals(other$primaryPhotoUrl)) {
            return false;
        }
        List<String> this$ingredients = this.getIngredients();
        List<String> other$ingredients = other.getIngredients();
        if (this$ingredients == null ? other$ingredients != null : !(this$ingredients).equals(other$ingredients)) {
            return false;
        }
        String this$preparationNotes = this.getPreparationNotes();
        String other$preparationNotes = other.getPreparationNotes();
        if (this$preparationNotes == null ? other$preparationNotes != null : !this$preparationNotes.equals(other$preparationNotes)) {
            return false;
        }
        String this$servingSize = this.getServingSize();
        String other$servingSize = other.getServingSize();
        return !(this$servingSize == null ? other$servingSize != null : !this$servingSize.equals(other$servingSize));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof MenuItem;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Double $price = this.getPrice();
        result = result * 59 + ($price == null ? 43 : ((Object)$price).hashCode());
        Double $discountedPrice = this.getDiscountedPrice();
        result = result * 59 + ($discountedPrice == null ? 43 : ((Object)$discountedPrice).hashCode());
        Integer $quantity = this.getQuantity();
        result = result * 59 + ($quantity == null ? 43 : ((Object)$quantity).hashCode());
        Boolean $isVegetarian = this.getIsVegetarian();
        result = result * 59 + ($isVegetarian == null ? 43 : ((Object)$isVegetarian).hashCode());
        Boolean $isSpicy = this.getIsSpicy();
        result = result * 59 + ($isSpicy == null ? 43 : ((Object)$isSpicy).hashCode());
        Boolean $containsNuts = this.getContainsNuts();
        result = result * 59 + ($containsNuts == null ? 43 : ((Object)$containsNuts).hashCode());
        Boolean $containsDairy = this.getContainsDairy();
        result = result * 59 + ($containsDairy == null ? 43 : ((Object)$containsDairy).hashCode());
        Double $estimatedPrepTime = this.getEstimatedPrepTime();
        result = result * 59 + ($estimatedPrepTime == null ? 43 : ((Object)$estimatedPrepTime).hashCode());
        Integer $calories = this.getCalories();
        result = result * 59 + ($calories == null ? 43 : ((Object)$calories).hashCode());
        Boolean $isAvailable = this.getIsAvailable();
        result = result * 59 + ($isAvailable == null ? 43 : ((Object)$isAvailable).hashCode());
        Integer $soldCount = this.getSoldCount();
        result = result * 59 + ($soldCount == null ? 43 : ((Object)$soldCount).hashCode());
        Menu $menu = this.getMenu();
        result = result * 59 + ($menu == null ? 43 : ((Object)$menu).hashCode());
        String $itemName = this.getItemName();
        result = result * 59 + ($itemName == null ? 43 : $itemName.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        List<FileInfo> $photos = this.getPhotos();
        result = result * 59 + ($photos == null ? 43 : ((Object)$photos).hashCode());
        FileInfo $primaryPhoto = this.getPrimaryPhoto();
        result = result * 59 + ($primaryPhoto == null ? 43 : ((Object)$primaryPhoto).hashCode());
        String $primaryPhotoUrl = this.getPrimaryPhotoUrl();
        result = result * 59 + ($primaryPhotoUrl == null ? 43 : $primaryPhotoUrl.hashCode());
        List<String> $ingredients = this.getIngredients();
        result = result * 59 + ($ingredients == null ? 43 : ((Object)$ingredients).hashCode());
        String $preparationNotes = this.getPreparationNotes();
        result = result * 59 + ($preparationNotes == null ? 43 : $preparationNotes.hashCode());
        String $servingSize = this.getServingSize();
        result = result * 59 + ($servingSize == null ? 43 : $servingSize.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "MenuItem(id=" + this.getId() + ", menu=" + String.valueOf(this.getMenu()) + ", itemName=" + this.getItemName() + ", description=" + this.getDescription() + ", photos=" + String.valueOf(this.getPhotos()) + ", primaryPhoto=" + String.valueOf(this.getPrimaryPhoto()) + ", primaryPhotoUrl=" + this.getPrimaryPhotoUrl() + ", price=" + this.getPrice() + ", discountedPrice=" + this.getDiscountedPrice() + ", quantity=" + this.getQuantity() + ", isVegetarian=" + this.getIsVegetarian() + ", isSpicy=" + this.getIsSpicy() + ", containsNuts=" + this.getContainsNuts() + ", containsDairy=" + this.getContainsDairy() + ", ingredients=" + String.valueOf(this.getIngredients()) + ", preparationNotes=" + this.getPreparationNotes() + ", estimatedPrepTime=" + this.getEstimatedPrepTime() + ", calories=" + this.getCalories() + ", servingSize=" + this.getServingSize() + ", isAvailable=" + this.getIsAvailable() + ", soldCount=" + this.getSoldCount() + ")";
    }

    @Generated
    public MenuItem() {
    }

    @Generated
    public MenuItem(Long id, Menu menu, String itemName, String description, List<FileInfo> photos, FileInfo primaryPhoto, String primaryPhotoUrl, Double price, Double discountedPrice, Integer quantity, Boolean isVegetarian, Boolean isSpicy, Boolean containsNuts, Boolean containsDairy, List<String> ingredients, String preparationNotes, Double estimatedPrepTime, Integer calories, String servingSize, Boolean isAvailable, Integer soldCount) {
        this.id = id;
        this.menu = menu;
        this.itemName = itemName;
        this.description = description;
        this.photos = photos;
        this.primaryPhoto = primaryPhoto;
        this.primaryPhotoUrl = primaryPhotoUrl;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.quantity = quantity;
        this.isVegetarian = isVegetarian;
        this.isSpicy = isSpicy;
        this.containsNuts = containsNuts;
        this.containsDairy = containsDairy;
        this.ingredients = ingredients;
        this.preparationNotes = preparationNotes;
        this.estimatedPrepTime = estimatedPrepTime;
        this.calories = calories;
        this.servingSize = servingSize;
        this.isAvailable = isAvailable;
        this.soldCount = soldCount;
    }
}
