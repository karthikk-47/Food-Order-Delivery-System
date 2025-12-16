package com.foodapp.deliveryexecutive.map.dto;

public class Step {
    public int distance;
    public String readable_distance;
    public int duration;
    public String readable_duration;
    public String instructions;
    public String maneuver;
    public int bearing_before;
    public int bearing_after;
    public Object start_location;
    public Object end_location;

    public String toString() {
        return "Step [distance=" + this.distance + ", readable_distance=" + this.readable_distance + ", duration=" + this.duration + ", readable_duration=" + this.readable_duration + ", instructions=" + this.instructions + ", maneuver=" + this.maneuver + ", bearing_before=" + this.bearing_before + ", bearing_after=" + this.bearing_after + ", start_location=" + String.valueOf(this.start_location) + ", end_location=" + String.valueOf(this.end_location) + "]";
    }
}
