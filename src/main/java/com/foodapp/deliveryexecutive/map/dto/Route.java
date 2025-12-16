package com.foodapp.deliveryexecutive.map.dto;

import com.foodapp.deliveryexecutive.map.dto.Leg;
import java.util.List;

public class Route {
    public List<Leg> legs;
    public String summary;
    public List<String> warnings;
    public String overview_polyline;
    public String travel_advisory;
    public Object bounds;
    public String copyrights;

    public String toString() {
        return "Route [legs=" + String.valueOf(this.legs) + ", summary=" + this.summary + ", warnings=" + String.valueOf(this.warnings) + ", overview_polyline=" + this.overview_polyline + ", travel_advisory=" + this.travel_advisory + ", bounds=" + String.valueOf(this.bounds) + ", copyrights=" + this.copyrights + "]";
    }
}
