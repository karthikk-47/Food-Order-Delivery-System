package com.foodapp.deliveryexecutive.user.dto;

import java.time.LocalDateTime;
import lombok.Generated;

public class UserAddressDTO {
    private Long id;
    private Long userId;
    private String addressLabel;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private Boolean isDefault;
    private Boolean isActive;
    private String deliveryInstructions;
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
    public String getAddressLabel() {
        return this.addressLabel;
    }

    @Generated
    public String getStreet() {
        return this.street;
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
    public String getPhoneNumber() {
        return this.phoneNumber;
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
    public Boolean getIsDefault() {
        return this.isDefault;
    }

    @Generated
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Generated
    public String getDeliveryInstructions() {
        return this.deliveryInstructions;
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
    public void setAddressLabel(String addressLabel) {
        this.addressLabel = addressLabel;
    }

    @Generated
    public void setStreet(String street) {
        this.street = street;
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
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Generated
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Generated
    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
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
        if (!(o instanceof UserAddressDTO)) {
            return false;
        }
        UserAddressDTO other = (UserAddressDTO)o;
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
        Boolean this$isDefault = this.getIsDefault();
        Boolean other$isDefault = other.getIsDefault();
        if (this$isDefault == null ? other$isDefault != null : !(this$isDefault).equals(other$isDefault)) {
            return false;
        }
        Boolean this$isActive = this.getIsActive();
        Boolean other$isActive = other.getIsActive();
        if (this$isActive == null ? other$isActive != null : !(this$isActive).equals(other$isActive)) {
            return false;
        }
        String this$addressLabel = this.getAddressLabel();
        String other$addressLabel = other.getAddressLabel();
        if (this$addressLabel == null ? other$addressLabel != null : !this$addressLabel.equals(other$addressLabel)) {
            return false;
        }
        String this$street = this.getStreet();
        String other$street = other.getStreet();
        if (this$street == null ? other$street != null : !this$street.equals(other$street)) {
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
        String this$phoneNumber = this.getPhoneNumber();
        String other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber)) {
            return false;
        }
        String this$deliveryInstructions = this.getDeliveryInstructions();
        String other$deliveryInstructions = other.getDeliveryInstructions();
        if (this$deliveryInstructions == null ? other$deliveryInstructions != null : !this$deliveryInstructions.equals(other$deliveryInstructions)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !(this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        return !(this$updatedAt == null ? other$updatedAt != null : !(this$updatedAt).equals(other$updatedAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof UserAddressDTO;
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
        Boolean $isDefault = this.getIsDefault();
        result = result * 59 + ($isDefault == null ? 43 : ((Object)$isDefault).hashCode());
        Boolean $isActive = this.getIsActive();
        result = result * 59 + ($isActive == null ? 43 : ((Object)$isActive).hashCode());
        String $addressLabel = this.getAddressLabel();
        result = result * 59 + ($addressLabel == null ? 43 : $addressLabel.hashCode());
        String $street = this.getStreet();
        result = result * 59 + ($street == null ? 43 : $street.hashCode());
        String $city = this.getCity();
        result = result * 59 + ($city == null ? 43 : $city.hashCode());
        String $state = this.getState();
        result = result * 59 + ($state == null ? 43 : $state.hashCode());
        String $zipCode = this.getZipCode();
        result = result * 59 + ($zipCode == null ? 43 : $zipCode.hashCode());
        String $phoneNumber = this.getPhoneNumber();
        result = result * 59 + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        String $deliveryInstructions = this.getDeliveryInstructions();
        result = result * 59 + ($deliveryInstructions == null ? 43 : $deliveryInstructions.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "UserAddressDTO(id=" + this.getId() + ", userId=" + this.getUserId() + ", addressLabel=" + this.getAddressLabel() + ", street=" + this.getStreet() + ", city=" + this.getCity() + ", state=" + this.getState() + ", zipCode=" + this.getZipCode() + ", phoneNumber=" + this.getPhoneNumber() + ", latitude=" + this.getLatitude() + ", longitude=" + this.getLongitude() + ", isDefault=" + this.getIsDefault() + ", isActive=" + this.getIsActive() + ", deliveryInstructions=" + this.getDeliveryInstructions() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ")";
    }

    @Generated
    public UserAddressDTO() {
    }

    @Generated
    public UserAddressDTO(Long id, Long userId, String addressLabel, String street, String city, String state, String zipCode, String phoneNumber, Double latitude, Double longitude, Boolean isDefault, Boolean isActive, String deliveryInstructions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.addressLabel = addressLabel;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isDefault = isDefault;
        this.isActive = isActive;
        this.deliveryInstructions = deliveryInstructions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
