package com.foodapp.deliveryexecutive.map.dto;

import java.util.List;

public class GeocodedWaypoint {
    public String geocoder_status;
    public String place_id;
    public List<String> types;

    public String toString() {
        return "GeocodedWaypoint [geocoder_status=" + this.geocoder_status + ", place_id=" + this.place_id + ", types=" + String.valueOf(this.types) + "]";
    }
}
