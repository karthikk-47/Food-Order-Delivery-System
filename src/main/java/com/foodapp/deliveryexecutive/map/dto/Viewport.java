package com.foodapp.deliveryexecutive.map.dto;

import com.foodapp.deliveryexecutive.map.dto.Northeast;
import com.foodapp.deliveryexecutive.map.dto.Southwest;
import lombok.Generated;

public class Viewport {
    private Southwest southwest;
    private Northeast northeast;

    @Generated
    public Viewport() {
    }

    @Generated
    public Southwest getSouthwest() {
        return this.southwest;
    }

    @Generated
    public Northeast getNortheast() {
        return this.northeast;
    }

    @Generated
    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

    @Generated
    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Viewport)) {
            return false;
        }
        Viewport other = (Viewport)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Southwest this$southwest = this.getSouthwest();
        Southwest other$southwest = other.getSouthwest();
        if (this$southwest == null ? other$southwest != null : !(this$southwest).equals(other$southwest)) {
            return false;
        }
        Northeast this$northeast = this.getNortheast();
        Northeast other$northeast = other.getNortheast();
        return !(this$northeast == null ? other$northeast != null : !(this$northeast).equals(other$northeast));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Viewport;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Southwest $southwest = this.getSouthwest();
        result = result * 59 + ($southwest == null ? 43 : ((Object)$southwest).hashCode());
        Northeast $northeast = this.getNortheast();
        result = result * 59 + ($northeast == null ? 43 : ((Object)$northeast).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Viewport(southwest=" + String.valueOf(this.getSouthwest()) + ", northeast=" + String.valueOf(this.getNortheast()) + ")";
    }
}
