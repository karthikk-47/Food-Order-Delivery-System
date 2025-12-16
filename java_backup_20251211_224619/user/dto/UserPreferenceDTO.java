/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.user.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Generated;

public class UserPreferenceDTO {
    private Long id;
    private Long userId;
    private Boolean vegetarianPreference;
    private Boolean spicyPreference;
    private Boolean dairyFreePreference;
    private Boolean glutenFreePreference;
    private List<String> preferredCuisines;
    private List<String> allergies;
    private Boolean isNutAllergy;
    private Boolean isDairyAllergy;
    private Boolean isSeafoodAllergy;
    private Boolean isPeanutAllergy;
    private String dietaryRestrictions;
    private Boolean receiveOrderReminders;
    private Boolean receiveNewHomemakerNotifications;
    private Boolean receiveDealsAndOffers;
    private String preferredNotificationTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getUserId() {
        return this.userId;
    }

    @Generated
    public Boolean getVegetarianPreference() {
        return this.vegetarianPreference;
    }

    @Generated
    public Boolean getSpicyPreference() {
        return this.spicyPreference;
    }

    @Generated
    public Boolean getDairyFreePreference() {
        return this.dairyFreePreference;
    }

    @Generated
    public Boolean getGlutenFreePreference() {
        return this.glutenFreePreference;
    }

    @Generated
    public List<String> getPreferredCuisines() {
        return this.preferredCuisines;
    }

    @Generated
    public List<String> getAllergies() {
        return this.allergies;
    }

    @Generated
    public Boolean getIsNutAllergy() {
        return this.isNutAllergy;
    }

    @Generated
    public Boolean getIsDairyAllergy() {
        return this.isDairyAllergy;
    }

    @Generated
    public Boolean getIsSeafoodAllergy() {
        return this.isSeafoodAllergy;
    }

    @Generated
    public Boolean getIsPeanutAllergy() {
        return this.isPeanutAllergy;
    }

    @Generated
    public String getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }

    @Generated
    public Boolean getReceiveOrderReminders() {
        return this.receiveOrderReminders;
    }

    @Generated
    public Boolean getReceiveNewHomemakerNotifications() {
        return this.receiveNewHomemakerNotifications;
    }

    @Generated
    public Boolean getReceiveDealsAndOffers() {
        return this.receiveDealsAndOffers;
    }

    @Generated
    public String getPreferredNotificationTime() {
        return this.preferredNotificationTime;
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
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated
    public void setVegetarianPreference(Boolean vegetarianPreference) {
        this.vegetarianPreference = vegetarianPreference;
    }

    @Generated
    public void setSpicyPreference(Boolean spicyPreference) {
        this.spicyPreference = spicyPreference;
    }

    @Generated
    public void setDairyFreePreference(Boolean dairyFreePreference) {
        this.dairyFreePreference = dairyFreePreference;
    }

    @Generated
    public void setGlutenFreePreference(Boolean glutenFreePreference) {
        this.glutenFreePreference = glutenFreePreference;
    }

    @Generated
    public void setPreferredCuisines(List<String> preferredCuisines) {
        this.preferredCuisines = preferredCuisines;
    }

    @Generated
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    @Generated
    public void setIsNutAllergy(Boolean isNutAllergy) {
        this.isNutAllergy = isNutAllergy;
    }

    @Generated
    public void setIsDairyAllergy(Boolean isDairyAllergy) {
        this.isDairyAllergy = isDairyAllergy;
    }

    @Generated
    public void setIsSeafoodAllergy(Boolean isSeafoodAllergy) {
        this.isSeafoodAllergy = isSeafoodAllergy;
    }

    @Generated
    public void setIsPeanutAllergy(Boolean isPeanutAllergy) {
        this.isPeanutAllergy = isPeanutAllergy;
    }

    @Generated
    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    @Generated
    public void setReceiveOrderReminders(Boolean receiveOrderReminders) {
        this.receiveOrderReminders = receiveOrderReminders;
    }

    @Generated
    public void setReceiveNewHomemakerNotifications(Boolean receiveNewHomemakerNotifications) {
        this.receiveNewHomemakerNotifications = receiveNewHomemakerNotifications;
    }

    @Generated
    public void setReceiveDealsAndOffers(Boolean receiveDealsAndOffers) {
        this.receiveDealsAndOffers = receiveDealsAndOffers;
    }

    @Generated
    public void setPreferredNotificationTime(String preferredNotificationTime) {
        this.preferredNotificationTime = preferredNotificationTime;
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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserPreferenceDTO)) {
            return false;
        }
        UserPreferenceDTO other = (UserPreferenceDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !((Object)this$userId).equals(other$userId)) {
            return false;
        }
        Boolean this$vegetarianPreference = this.getVegetarianPreference();
        Boolean other$vegetarianPreference = other.getVegetarianPreference();
        if (this$vegetarianPreference == null ? other$vegetarianPreference != null : !((Object)this$vegetarianPreference).equals(other$vegetarianPreference)) {
            return false;
        }
        Boolean this$spicyPreference = this.getSpicyPreference();
        Boolean other$spicyPreference = other.getSpicyPreference();
        if (this$spicyPreference == null ? other$spicyPreference != null : !((Object)this$spicyPreference).equals(other$spicyPreference)) {
            return false;
        }
        Boolean this$dairyFreePreference = this.getDairyFreePreference();
        Boolean other$dairyFreePreference = other.getDairyFreePreference();
        if (this$dairyFreePreference == null ? other$dairyFreePreference != null : !((Object)this$dairyFreePreference).equals(other$dairyFreePreference)) {
            return false;
        }
        Boolean this$glutenFreePreference = this.getGlutenFreePreference();
        Boolean other$glutenFreePreference = other.getGlutenFreePreference();
        if (this$glutenFreePreference == null ? other$glutenFreePreference != null : !((Object)this$glutenFreePreference).equals(other$glutenFreePreference)) {
            return false;
        }
        Boolean this$isNutAllergy = this.getIsNutAllergy();
        Boolean other$isNutAllergy = other.getIsNutAllergy();
        if (this$isNutAllergy == null ? other$isNutAllergy != null : !((Object)this$isNutAllergy).equals(other$isNutAllergy)) {
            return false;
        }
        Boolean this$isDairyAllergy = this.getIsDairyAllergy();
        Boolean other$isDairyAllergy = other.getIsDairyAllergy();
        if (this$isDairyAllergy == null ? other$isDairyAllergy != null : !((Object)this$isDairyAllergy).equals(other$isDairyAllergy)) {
            return false;
        }
        Boolean this$isSeafoodAllergy = this.getIsSeafoodAllergy();
        Boolean other$isSeafoodAllergy = other.getIsSeafoodAllergy();
        if (this$isSeafoodAllergy == null ? other$isSeafoodAllergy != null : !((Object)this$isSeafoodAllergy).equals(other$isSeafoodAllergy)) {
            return false;
        }
        Boolean this$isPeanutAllergy = this.getIsPeanutAllergy();
        Boolean other$isPeanutAllergy = other.getIsPeanutAllergy();
        if (this$isPeanutAllergy == null ? other$isPeanutAllergy != null : !((Object)this$isPeanutAllergy).equals(other$isPeanutAllergy)) {
            return false;
        }
        Boolean this$receiveOrderReminders = this.getReceiveOrderReminders();
        Boolean other$receiveOrderReminders = other.getReceiveOrderReminders();
        if (this$receiveOrderReminders == null ? other$receiveOrderReminders != null : !((Object)this$receiveOrderReminders).equals(other$receiveOrderReminders)) {
            return false;
        }
        Boolean this$receiveNewHomemakerNotifications = this.getReceiveNewHomemakerNotifications();
        Boolean other$receiveNewHomemakerNotifications = other.getReceiveNewHomemakerNotifications();
        if (this$receiveNewHomemakerNotifications == null ? other$receiveNewHomemakerNotifications != null : !((Object)this$receiveNewHomemakerNotifications).equals(other$receiveNewHomemakerNotifications)) {
            return false;
        }
        Boolean this$receiveDealsAndOffers = this.getReceiveDealsAndOffers();
        Boolean other$receiveDealsAndOffers = other.getReceiveDealsAndOffers();
        if (this$receiveDealsAndOffers == null ? other$receiveDealsAndOffers != null : !((Object)this$receiveDealsAndOffers).equals(other$receiveDealsAndOffers)) {
            return false;
        }
        List<String> this$preferredCuisines = this.getPreferredCuisines();
        List<String> other$preferredCuisines = other.getPreferredCuisines();
        if (this$preferredCuisines == null ? other$preferredCuisines != null : !((Object)this$preferredCuisines).equals(other$preferredCuisines)) {
            return false;
        }
        List<String> this$allergies = this.getAllergies();
        List<String> other$allergies = other.getAllergies();
        if (this$allergies == null ? other$allergies != null : !((Object)this$allergies).equals(other$allergies)) {
            return false;
        }
        String this$dietaryRestrictions = this.getDietaryRestrictions();
        String other$dietaryRestrictions = other.getDietaryRestrictions();
        if (this$dietaryRestrictions == null ? other$dietaryRestrictions != null : !this$dietaryRestrictions.equals(other$dietaryRestrictions)) {
            return false;
        }
        String this$preferredNotificationTime = this.getPreferredNotificationTime();
        String other$preferredNotificationTime = other.getPreferredNotificationTime();
        if (this$preferredNotificationTime == null ? other$preferredNotificationTime != null : !this$preferredNotificationTime.equals(other$preferredNotificationTime)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !((Object)this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        return !(this$updatedAt == null ? other$updatedAt != null : !((Object)this$updatedAt).equals(other$updatedAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof UserPreferenceDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Boolean $vegetarianPreference = this.getVegetarianPreference();
        result = result * 59 + ($vegetarianPreference == null ? 43 : ((Object)$vegetarianPreference).hashCode());
        Boolean $spicyPreference = this.getSpicyPreference();
        result = result * 59 + ($spicyPreference == null ? 43 : ((Object)$spicyPreference).hashCode());
        Boolean $dairyFreePreference = this.getDairyFreePreference();
        result = result * 59 + ($dairyFreePreference == null ? 43 : ((Object)$dairyFreePreference).hashCode());
        Boolean $glutenFreePreference = this.getGlutenFreePreference();
        result = result * 59 + ($glutenFreePreference == null ? 43 : ((Object)$glutenFreePreference).hashCode());
        Boolean $isNutAllergy = this.getIsNutAllergy();
        result = result * 59 + ($isNutAllergy == null ? 43 : ((Object)$isNutAllergy).hashCode());
        Boolean $isDairyAllergy = this.getIsDairyAllergy();
        result = result * 59 + ($isDairyAllergy == null ? 43 : ((Object)$isDairyAllergy).hashCode());
        Boolean $isSeafoodAllergy = this.getIsSeafoodAllergy();
        result = result * 59 + ($isSeafoodAllergy == null ? 43 : ((Object)$isSeafoodAllergy).hashCode());
        Boolean $isPeanutAllergy = this.getIsPeanutAllergy();
        result = result * 59 + ($isPeanutAllergy == null ? 43 : ((Object)$isPeanutAllergy).hashCode());
        Boolean $receiveOrderReminders = this.getReceiveOrderReminders();
        result = result * 59 + ($receiveOrderReminders == null ? 43 : ((Object)$receiveOrderReminders).hashCode());
        Boolean $receiveNewHomemakerNotifications = this.getReceiveNewHomemakerNotifications();
        result = result * 59 + ($receiveNewHomemakerNotifications == null ? 43 : ((Object)$receiveNewHomemakerNotifications).hashCode());
        Boolean $receiveDealsAndOffers = this.getReceiveDealsAndOffers();
        result = result * 59 + ($receiveDealsAndOffers == null ? 43 : ((Object)$receiveDealsAndOffers).hashCode());
        List<String> $preferredCuisines = this.getPreferredCuisines();
        result = result * 59 + ($preferredCuisines == null ? 43 : ((Object)$preferredCuisines).hashCode());
        List<String> $allergies = this.getAllergies();
        result = result * 59 + ($allergies == null ? 43 : ((Object)$allergies).hashCode());
        String $dietaryRestrictions = this.getDietaryRestrictions();
        result = result * 59 + ($dietaryRestrictions == null ? 43 : $dietaryRestrictions.hashCode());
        String $preferredNotificationTime = this.getPreferredNotificationTime();
        result = result * 59 + ($preferredNotificationTime == null ? 43 : $preferredNotificationTime.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "UserPreferenceDTO(id=" + this.getId() + ", userId=" + this.getUserId() + ", vegetarianPreference=" + this.getVegetarianPreference() + ", spicyPreference=" + this.getSpicyPreference() + ", dairyFreePreference=" + this.getDairyFreePreference() + ", glutenFreePreference=" + this.getGlutenFreePreference() + ", preferredCuisines=" + String.valueOf(this.getPreferredCuisines()) + ", allergies=" + String.valueOf(this.getAllergies()) + ", isNutAllergy=" + this.getIsNutAllergy() + ", isDairyAllergy=" + this.getIsDairyAllergy() + ", isSeafoodAllergy=" + this.getIsSeafoodAllergy() + ", isPeanutAllergy=" + this.getIsPeanutAllergy() + ", dietaryRestrictions=" + this.getDietaryRestrictions() + ", receiveOrderReminders=" + this.getReceiveOrderReminders() + ", receiveNewHomemakerNotifications=" + this.getReceiveNewHomemakerNotifications() + ", receiveDealsAndOffers=" + this.getReceiveDealsAndOffers() + ", preferredNotificationTime=" + this.getPreferredNotificationTime() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ")";
    }

    @Generated
    public UserPreferenceDTO() {
    }

    @Generated
    public UserPreferenceDTO(Long id, Long userId, Boolean vegetarianPreference, Boolean spicyPreference, Boolean dairyFreePreference, Boolean glutenFreePreference, List<String> preferredCuisines, List<String> allergies, Boolean isNutAllergy, Boolean isDairyAllergy, Boolean isSeafoodAllergy, Boolean isPeanutAllergy, String dietaryRestrictions, Boolean receiveOrderReminders, Boolean receiveNewHomemakerNotifications, Boolean receiveDealsAndOffers, String preferredNotificationTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.vegetarianPreference = vegetarianPreference;
        this.spicyPreference = spicyPreference;
        this.dairyFreePreference = dairyFreePreference;
        this.glutenFreePreference = glutenFreePreference;
        this.preferredCuisines = preferredCuisines;
        this.allergies = allergies;
        this.isNutAllergy = isNutAllergy;
        this.isDairyAllergy = isDairyAllergy;
        this.isSeafoodAllergy = isSeafoodAllergy;
        this.isPeanutAllergy = isPeanutAllergy;
        this.dietaryRestrictions = dietaryRestrictions;
        this.receiveOrderReminders = receiveOrderReminders;
        this.receiveNewHomemakerNotifications = receiveNewHomemakerNotifications;
        this.receiveDealsAndOffers = receiveDealsAndOffers;
        this.preferredNotificationTime = preferredNotificationTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
