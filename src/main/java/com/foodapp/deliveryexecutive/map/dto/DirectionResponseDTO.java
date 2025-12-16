package com.foodapp.deliveryexecutive.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foodapp.deliveryexecutive.map.dto.GeocodedWaypoint;
import com.foodapp.deliveryexecutive.map.dto.Route;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DirectionResponseDTO {
    long id;
    public List<GeocodedWaypoint> geocoded_waypoints;
    public List<Route> routes;
    public String status;
    public String source_from;

    public String toString() {
        return "DirectionResponseDTO [id" + this.id + "geocoded_waypoints=" + String.valueOf(this.geocoded_waypoints) + ", routes=" + String.valueOf(this.routes) + ", status=" + this.status + ", source_from=" + this.source_from + "]";
    }
}
