/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.user.dto;

import com.foodapp.deliveryexecutive.user.entity.UserProfile;
import java.time.LocalDateTime;
import lombok.Generated;

public class UserProfileDTO {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String profileImage;
    private String primaryAddress;
    private String city;
    private String state;
    private String zipCode;
    private Double latitude;
    private Double longitude;
    private Boolean hasAddressSaved;
    private Integer addressCount;
    private UserProfile.AccountStatus accountStatus;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private LocalDateTime emailVerifiedAt;
    private LocalDateTime phoneVerifiedAt;
    private String preferredPaymentMethod;
    private Boolean notificationsEnabled;
    private Boolean promotionalEmailsEnabled;
    private Integer totalOrders;
    private Integer totalFavouriteHomemakers;
    private Double totalSpent;
    private Double averageOrderValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getUserId() {
        return this.userId;
    }

    @Generated
    public String getFirstName() {
        return this.firstName;
    }

    @Generated
    public String getLastName() {
        return this.lastName;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Generated
    public String getProfileImage() {
        return this.profileImage;
    }

    @Generated
    public String getPrimaryAddress() {
        return this.primaryAddress;
    }

    @Generated
    public String getCity() {
        return this.city;
    }

    @Generated
    public String getState() {
        return this.state;
    }

    @Generated
    public String getZipCode() {
        return this.zipCode;
    }

    @Generated
    public Double getLatitude() {
        return this.latitude;
    }

    @Generated
    public Double getLongitude() {
        return this.longitude;
    }

    @Generated
    public Boolean getHasAddressSaved() {
        return this.hasAddressSaved;
    }

    @Generated
    public Integer getAddressCount() {
        return this.addressCount;
    }

    @Generated
    public UserProfile.AccountStatus getAccountStatus() {
        return this.accountStatus;
    }

    @Generated
    public Boolean getEmailVerified() {
        return this.emailVerified;
    }

    @Generated
    public Boolean getPhoneVerified() {
        return this.phoneVerified;
    }

    @Generated
    public LocalDateTime getEmailVerifiedAt() {
        return this.emailVerifiedAt;
    }

    @Generated
    public LocalDateTime getPhoneVerifiedAt() {
        return this.phoneVerifiedAt;
    }

    @Generated
    public String getPreferredPaymentMethod() {
        return this.preferredPaymentMethod;
    }

    @Generated
    public Boolean getNotificationsEnabled() {
        return this.notificationsEnabled;
    }

    @Generated
    public Boolean getPromotionalEmailsEnabled() {
        return this.promotionalEmailsEnabled;
    }

    @Generated
    public Integer getTotalOrders() {
        return this.totalOrders;
    }

    @Generated
    public Integer getTotalFavouriteHomemakers() {
        return this.totalFavouriteHomemakers;
    }

    @Generated
    public Double getTotalSpent() {
        return this.totalSpent;
    }

    @Generated
    public Double getAverageOrderValue() {
        return this.averageOrderValue;
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
    public LocalDateTime getLastLogin() {
        return this.lastLogin;
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
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Generated
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Generated
    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    @Generated
    public void setCity(String city) {
        this.city = city;
    }

    @Generated
    public void setState(String state) {
        this.state = state;
    }

    @Generated
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Generated
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Generated
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Generated
    public void setHasAddressSaved(Boolean hasAddressSaved) {
        this.hasAddressSaved = hasAddressSaved;
    }

    @Generated
    public void setAddressCount(Integer addressCount) {
        this.addressCount = addressCount;
    }

    @Generated
    public void setAccountStatus(UserProfile.AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Generated
    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Generated
    public void setPhoneVerified(Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    @Generated
    public void setEmailVerifiedAt(LocalDateTime emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    @Generated
    public void setPhoneVerifiedAt(LocalDateTime phoneVerifiedAt) {
        this.phoneVerifiedAt = phoneVerifiedAt;
    }

    @Generated
    public void setPreferredPaymentMethod(String preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    @Generated
    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    @Generated
    public void setPromotionalEmailsEnabled(Boolean promotionalEmailsEnabled) {
        this.promotionalEmailsEnabled = promotionalEmailsEnabled;
    }

    @Generated
    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    @Generated
    public void setTotalFavouriteHomemakers(Integer totalFavouriteHomemakers) {
        this.totalFavouriteHomemakers = totalFavouriteHomemakers;
    }

    @Generated
    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    @Generated
    public void setAverageOrderValue(Double averageOrderValue) {
        this.averageOrderValue = averageOrderValue;
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
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserProfileDTO)) {
            return false;
        }
        UserProfileDTO other = (UserProfileDTO)o;
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
        Double this$latitude = this.getLatitude();
        Double other$latitude = other.getLatitude();
        if (this$latitude == null ? other$latitude != null : !(this$latitude).equals(other$latitude)) {
            return false;
        }
        Double this$longitude = this.getLongitude();
        Double other$longitude = other.getLongitude();
        if (this$longitude == null ? other$longitude != null : !(this$longitude).equals(other$longitude)) {
            return false;
        }
        Boolean this$hasAddressSaved = this.getHasAddressSaved();
        Boolean other$hasAddressSaved = other.getHasAddressSaved();
        if (this$hasAddressSaved == null ? other$hasAddressSaved != null : !(this$hasAddressSaved).equals(other$hasAddressSaved)) {
            return false;
        }
        Integer this$addressCount = this.getAddressCount();
        Integer other$addressCount = other.getAddressCount();
        if (this$addressCount == null ? other$addressCount != null : !(this$addressCount).equals(other$addressCount)) {
            return false;
        }
        Boolean this$emailVerified = this.getEmailVerified();
        Boolean other$emailVerified = other.getEmailVerified();
        if (this$emailVerified == null ? other$emailVerified != null : !(this$emailVerified).equals(other$emailVerified)) {
            return false;
        }
        Boolean this$phoneVerified = this.getPhoneVerified();
        Boolean other$phoneVerified = other.getPhoneVerified();
        if (this$phoneVerified == null ? other$phoneVerified != null : !(this$phoneVerified).equals(other$phoneVerified)) {
            return false;
        }
        Boolean this$notificationsEnabled = this.getNotificationsEnabled();
        Boolean other$notificationsEnabled = other.getNotificationsEnabled();
        if (this$notificationsEnabled == null ? other$notificationsEnabled != null : !(this$notificationsEnabled).equals(other$notificationsEnabled)) {
            return false;
        }
        Boolean this$promotionalEmailsEnabled = this.getPromotionalEmailsEnabled();
        Boolean other$promotionalEmailsEnabled = other.getPromotionalEmailsEnabled();
        if (this$promotionalEmailsEnabled == null ? other$promotionalEmailsEnabled != null : !(this$promotionalEmailsEnabled).equals(other$promotionalEmailsEnabled)) {
            return false;
        }
        Integer this$totalOrders = this.getTotalOrders();
        Integer other$totalOrders = other.getTotalOrders();
        if (this$totalOrders == null ? other$totalOrders != null : !(this$totalOrders).equals(other$totalOrders)) {
            return false;
        }
        Integer this$totalFavouriteHomemakers = this.getTotalFavouriteHomemakers();
        Integer other$totalFavouriteHomemakers = other.getTotalFavouriteHomemakers();
        if (this$totalFavouriteHomemakers == null ? other$totalFavouriteHomemakers != null : !(this$totalFavouriteHomemakers).equals(other$totalFavouriteHomemakers)) {
            return false;
        }
        Double this$totalSpent = this.getTotalSpent();
        Double other$totalSpent = other.getTotalSpent();
        if (this$totalSpent == null ? other$totalSpent != null : !(this$totalSpent).equals(other$totalSpent)) {
            return false;
        }
        Double this$averageOrderValue = this.getAverageOrderValue();
        Double other$averageOrderValue = other.getAverageOrderValue();
        if (this$averageOrderValue == null ? other$averageOrderValue != null : !(this$averageOrderValue).equals(other$averageOrderValue)) {
            return false;
        }
        String this$firstName = this.getFirstName();
        String other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) {
            return false;
        }
        String this$lastName = this.getLastName();
        String other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$phoneNumber = this.getPhoneNumber();
        String other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber)) {
            return false;
        }
        String this$profileImage = this.getProfileImage();
        String other$profileImage = other.getProfileImage();
        if (this$profileImage == null ? other$profileImage != null : !this$profileImage.equals(other$profileImage)) {
            return false;
        }
        String this$primaryAddress = this.getPrimaryAddress();
        String other$primaryAddress = other.getPrimaryAddress();
        if (this$primaryAddress == null ? other$primaryAddress != null : !this$primaryAddress.equals(other$primaryAddress)) {
            return false;
        }
        String this$city = this.getCity();
        String other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) {
            return false;
        }
        String this$state = this.getState();
        String other$state = other.getState();
        if (this$state == null ? other$state != null : !this$state.equals(other$state)) {
            return false;
        }
        String this$zipCode = this.getZipCode();
        String other$zipCode = other.getZipCode();
        if (this$zipCode == null ? other$zipCode != null : !this$zipCode.equals(other$zipCode)) {
            return false;
        }
        UserProfile.AccountStatus this$accountStatus = this.getAccountStatus();
        UserProfile.AccountStatus other$accountStatus = other.getAccountStatus();
        if (this$accountStatus == null ? other$accountStatus != null : !((Object)(this$accountStatus)).equals(other$accountStatus)) {
            return false;
        }
        LocalDateTime this$emailVerifiedAt = this.getEmailVerifiedAt();
        LocalDateTime other$emailVerifiedAt = other.getEmailVerifiedAt();
        if (this$emailVerifiedAt == null ? other$emailVerifiedAt != null : !(this$emailVerifiedAt).equals(other$emailVerifiedAt)) {
            return false;
        }
        LocalDateTime this$phoneVerifiedAt = this.getPhoneVerifiedAt();
        LocalDateTime other$phoneVerifiedAt = other.getPhoneVerifiedAt();
        if (this$phoneVerifiedAt == null ? other$phoneVerifiedAt != null : !(this$phoneVerifiedAt).equals(other$phoneVerifiedAt)) {
            return false;
        }
        String this$preferredPaymentMethod = this.getPreferredPaymentMethod();
        String other$preferredPaymentMethod = other.getPreferredPaymentMethod();
        if (this$preferredPaymentMethod == null ? other$preferredPaymentMethod != null : !this$preferredPaymentMethod.equals(other$preferredPaymentMethod)) {
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
        LocalDateTime this$lastLogin = this.getLastLogin();
        LocalDateTime other$lastLogin = other.getLastLogin();
        return !(this$lastLogin == null ? other$lastLogin != null : !(this$lastLogin).equals(other$lastLogin));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof UserProfileDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Double $latitude = this.getLatitude();
        result = result * 59 + ($latitude == null ? 43 : ((Object)$latitude).hashCode());
        Double $longitude = this.getLongitude();
        result = result * 59 + ($longitude == null ? 43 : ((Object)$longitude).hashCode());
        Boolean $hasAddressSaved = this.getHasAddressSaved();
        result = result * 59 + ($hasAddressSaved == null ? 43 : ((Object)$hasAddressSaved).hashCode());
        Integer $addressCount = this.getAddressCount();
        result = result * 59 + ($addressCount == null ? 43 : ((Object)$addressCount).hashCode());
        Boolean $emailVerified = this.getEmailVerified();
        result = result * 59 + ($emailVerified == null ? 43 : ((Object)$emailVerified).hashCode());
        Boolean $phoneVerified = this.getPhoneVerified();
        result = result * 59 + ($phoneVerified == null ? 43 : ((Object)$phoneVerified).hashCode());
        Boolean $notificationsEnabled = this.getNotificationsEnabled();
        result = result * 59 + ($notificationsEnabled == null ? 43 : ((Object)$notificationsEnabled).hashCode());
        Boolean $promotionalEmailsEnabled = this.getPromotionalEmailsEnabled();
        result = result * 59 + ($promotionalEmailsEnabled == null ? 43 : ((Object)$promotionalEmailsEnabled).hashCode());
        Integer $totalOrders = this.getTotalOrders();
        result = result * 59 + ($totalOrders == null ? 43 : ((Object)$totalOrders).hashCode());
        Integer $totalFavouriteHomemakers = this.getTotalFavouriteHomemakers();
        result = result * 59 + ($totalFavouriteHomemakers == null ? 43 : ((Object)$totalFavouriteHomemakers).hashCode());
        Double $totalSpent = this.getTotalSpent();
        result = result * 59 + ($totalSpent == null ? 43 : ((Object)$totalSpent).hashCode());
        Double $averageOrderValue = this.getAverageOrderValue();
        result = result * 59 + ($averageOrderValue == null ? 43 : ((Object)$averageOrderValue).hashCode());
        String $firstName = this.getFirstName();
        result = result * 59 + ($firstName == null ? 43 : $firstName.hashCode());
        String $lastName = this.getLastName();
        result = result * 59 + ($lastName == null ? 43 : $lastName.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $phoneNumber = this.getPhoneNumber();
        result = result * 59 + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        String $profileImage = this.getProfileImage();
        result = result * 59 + ($profileImage == null ? 43 : $profileImage.hashCode());
        String $primaryAddress = this.getPrimaryAddress();
        result = result * 59 + ($primaryAddress == null ? 43 : $primaryAddress.hashCode());
        String $city = this.getCity();
        result = result * 59 + ($city == null ? 43 : $city.hashCode());
        String $state = this.getState();
        result = result * 59 + ($state == null ? 43 : $state.hashCode());
        String $zipCode = this.getZipCode();
        result = result * 59 + ($zipCode == null ? 43 : $zipCode.hashCode());
        UserProfile.AccountStatus $accountStatus = this.getAccountStatus();
        result = result * 59 + ($accountStatus == null ? 43 : ((Object)((Object)$accountStatus)).hashCode());
        LocalDateTime $emailVerifiedAt = this.getEmailVerifiedAt();
        result = result * 59 + ($emailVerifiedAt == null ? 43 : ((Object)$emailVerifiedAt).hashCode());
        LocalDateTime $phoneVerifiedAt = this.getPhoneVerifiedAt();
        result = result * 59 + ($phoneVerifiedAt == null ? 43 : ((Object)$phoneVerifiedAt).hashCode());
        String $preferredPaymentMethod = this.getPreferredPaymentMethod();
        result = result * 59 + ($preferredPaymentMethod == null ? 43 : $preferredPaymentMethod.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        LocalDateTime $lastLogin = this.getLastLogin();
        result = result * 59 + ($lastLogin == null ? 43 : ((Object)$lastLogin).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "UserProfileDTO(id=" + this.getId() + ", userId=" + this.getUserId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", email=" + this.getEmail() + ", phoneNumber=" + this.getPhoneNumber() + ", profileImage=" + this.getProfileImage() + ", primaryAddress=" + this.getPrimaryAddress() + ", city=" + this.getCity() + ", state=" + this.getState() + ", zipCode=" + this.getZipCode() + ", latitude=" + this.getLatitude() + ", longitude=" + this.getLongitude() + ", hasAddressSaved=" + this.getHasAddressSaved() + ", addressCount=" + this.getAddressCount() + ", accountStatus=" + String.valueOf(this.getAccountStatus()) + ", emailVerified=" + this.getEmailVerified() + ", phoneVerified=" + this.getPhoneVerified() + ", emailVerifiedAt=" + String.valueOf(this.getEmailVerifiedAt()) + ", phoneVerifiedAt=" + String.valueOf(this.getPhoneVerifiedAt()) + ", preferredPaymentMethod=" + this.getPreferredPaymentMethod() + ", notificationsEnabled=" + this.getNotificationsEnabled() + ", promotionalEmailsEnabled=" + this.getPromotionalEmailsEnabled() + ", totalOrders=" + this.getTotalOrders() + ", totalFavouriteHomemakers=" + this.getTotalFavouriteHomemakers() + ", totalSpent=" + this.getTotalSpent() + ", averageOrderValue=" + this.getAverageOrderValue() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ", lastLogin=" + String.valueOf(this.getLastLogin()) + ")";
    }

    @Generated
    public UserProfileDTO() {
    }

    @Generated
    public UserProfileDTO(Long id, Long userId, String firstName, String lastName, String email, String phoneNumber, String profileImage, String primaryAddress, String city, String state, String zipCode, Double latitude, Double longitude, Boolean hasAddressSaved, Integer addressCount, UserProfile.AccountStatus accountStatus, Boolean emailVerified, Boolean phoneVerified, LocalDateTime emailVerifiedAt, LocalDateTime phoneVerifiedAt, String preferredPaymentMethod, Boolean notificationsEnabled, Boolean promotionalEmailsEnabled, Integer totalOrders, Integer totalFavouriteHomemakers, Double totalSpent, Double averageOrderValue, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastLogin) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.primaryAddress = primaryAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hasAddressSaved = hasAddressSaved;
        this.addressCount = addressCount;
        this.accountStatus = accountStatus;
        this.emailVerified = emailVerified;
        this.phoneVerified = phoneVerified;
        this.emailVerifiedAt = emailVerifiedAt;
        this.phoneVerifiedAt = phoneVerifiedAt;
        this.preferredPaymentMethod = preferredPaymentMethod;
        this.notificationsEnabled = notificationsEnabled;
        this.promotionalEmailsEnabled = promotionalEmailsEnabled;
        this.totalOrders = totalOrders;
        this.totalFavouriteHomemakers = totalFavouriteHomemakers;
        this.totalSpent = totalSpent;
        this.averageOrderValue = averageOrderValue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
    }
}
