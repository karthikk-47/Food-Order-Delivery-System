package com.foodapp.deliveryexecutive.order.dto;

import lombok.Generated;

public class LocationUpdateDTO {
    private Long orderId;
    private Long executiveId;
    private Double latitude;
    private Double longitude;

    @Generated
    public Long getOrderId() {
        return this.orderId;
    }

    @Generated
    public Long getExecutiveId() {
        return this.executiveId;
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
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Generated
    public void setExecutiveId(Long executiveId) {
        this.executiveId = executiveId;
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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LocationUpdateDTO)) {
            return false;
        }
        LocationUpdateDTO other = (LocationUpdateDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$orderId = this.getOrderId();
        Long other$orderId = other.getOrderId();
        if (this$orderId == null ? other$orderId != null : !(this$orderId).equals(other$orderId)) {
            return false;
        }
        Long this$executiveId = this.getExecutiveId();
        Long other$executiveId = other.getExecutiveId();
        if (this$executiveId == null ? other$executiveId != null : !(this$executiveId).equals(other$executiveId)) {
            return false;
        }
        Double this$latitude = this.getLatitude();
        Double other$latitude = other.getLatitude();
        if (this$latitude == null ? other$latitude != null : !(this$latitude).equals(other$latitude)) {
            return false;
        }
        Double this$longitude = this.getLongitude();
        Double other$longitude = other.getLongitude();
        return !(this$longitude == null ? other$longitude != null : !(this$longitude).equals(other$longitude));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof LocationUpdateDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $orderId = this.getOrderId();
        result = result * 59 + ($orderId == null ? 43 : ((Object)$orderId).hashCode());
        Long $executiveId = this.getExecutiveId();
        result = result * 59 + ($executiveId == null ? 43 : ((Object)$executiveId).hashCode());
        Double $latitude = this.getLatitude();
        result = result * 59 + ($latitude == null ? 43 : ((Object)$latitude).hashCode());
        Double $longitude = this.getLongitude();
        result = result * 59 + ($longitude == null ? 43 : ((Object)$longitude).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "LocationUpdateDTO(orderId=" + this.getOrderId() + ", executiveId=" + this.getExecutiveId() + ", latitude=" + this.getLatitude() + ", longitude=" + this.getLongitude() + ")";
    }

    @Generated
    public LocationUpdateDTO() {
    }

    @Generated
    public LocationUpdateDTO(Long orderId, Long executiveId, Double latitude, Double longitude) {
        this.orderId = orderId;
        this.executiveId = executiveId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
