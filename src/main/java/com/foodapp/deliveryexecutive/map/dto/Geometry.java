/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.map.dto;

import com.foodapp.deliveryexecutive.map.dto.Location;
import com.foodapp.deliveryexecutive.map.dto.Viewport;
import lombok.Generated;

public class Geometry {
    private Viewport viewport;
    private Location location;
    private String location_type;

    @Generated
    public Geometry() {
    }

    @Generated
    public Viewport getViewport() {
        return this.viewport;
    }

    @Generated
    public Location getLocation() {
        return this.location;
    }

    @Generated
    public String getLocation_type() {
        return this.location_type;
    }

    @Generated
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    @Generated
    public void setLocation(Location location) {
        this.location = location;
    }

    @Generated
    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Geometry)) {
            return false;
        }
        Geometry other = (Geometry)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Viewport this$viewport = this.getViewport();
        Viewport other$viewport = other.getViewport();
        if (this$viewport == null ? other$viewport != null : !(this$viewport).equals(other$viewport)) {
            return false;
        }
        Location this$location = this.getLocation();
        Location other$location = other.getLocation();
        if (this$location == null ? other$location != null : !(this$location).equals(other$location)) {
            return false;
        }
        String this$location_type = this.getLocation_type();
        String other$location_type = other.getLocation_type();
        return !(this$location_type == null ? other$location_type != null : !this$location_type.equals(other$location_type));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Geometry;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Viewport $viewport = this.getViewport();
        result = result * 59 + ($viewport == null ? 43 : ((Object)$viewport).hashCode());
        Location $location = this.getLocation();
        result = result * 59 + ($location == null ? 43 : ((Object)$location).hashCode());
        String $location_type = this.getLocation_type();
        result = result * 59 + ($location_type == null ? 43 : $location_type.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Geometry(viewport=" + String.valueOf(this.getViewport()) + ", location=" + String.valueOf(this.getLocation()) + ", location_type=" + this.getLocation_type() + ")";
    }
}
