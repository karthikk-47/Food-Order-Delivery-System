package com.foodapp.deliveryexecutive.map.dto;

public class Element {
    int duration;
    int distance;
    String polyline;
    String status;

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getPolyline() {
        return this.polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
