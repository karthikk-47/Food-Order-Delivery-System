package com.foodapp.deliveryexecutive.map.dto;

import lombok.Generated;

public class Location {
    private double lng;
    private double lat;

    @Generated
    public Location() {
    }

    @Generated
    public double getLng() {
        return this.lng;
    }

    @Generated
    public double getLat() {
        return this.lat;
    }

    @Generated
    public void setLng(double lng) {
        this.lng = lng;
    }

    @Generated
    public void setLat(double lat) {
        this.lat = lat;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        Location other = (Location)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (Double.compare(this.getLng(), other.getLng()) != 0) {
            return false;
        }
        return Double.compare(this.getLat(), other.getLat()) == 0;
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Location;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $lng = Double.doubleToLongBits(this.getLng());
        result = result * 59 + (int)($lng >>> 32 ^ $lng);
        long $lat = Double.doubleToLongBits(this.getLat());
        result = result * 59 + (int)($lat >>> 32 ^ $lat);
        return result;
    }

    @Generated
    public String toString() {
        return "Location(lng=" + this.getLng() + ", lat=" + this.getLat() + ")";
    }
}
