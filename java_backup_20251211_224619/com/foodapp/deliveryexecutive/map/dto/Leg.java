/*
 * Decompiled with CFR 0.152.
 */
package com.foodapp.deliveryexecutive.map.dto;

import com.foodapp.deliveryexecutive.map.dto.Step;
import java.util.List;

public class Leg {
    public int distance;
    public String readable_distance;
    public int duration;
    public String readable_duration;
    public String start_address;
    public String end_address;
    public Object start_location;
    public Object end_location;
    public List<Step> steps;

    public String toString() {
        return "Leg [distance=" + this.distance + ", readable_distance=" + this.readable_distance + ", duration=" + this.duration + ", readable_duration=" + this.readable_duration + ", start_address=" + this.start_address + ", end_address=" + this.end_address + ", start_location=" + String.valueOf(this.start_location) + ", end_location=" + String.valueOf(this.end_location) + "]";
    }
}
