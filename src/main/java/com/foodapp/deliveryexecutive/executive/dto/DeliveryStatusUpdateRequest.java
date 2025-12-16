package com.foodapp.deliveryexecutive.executive.dto;

public class DeliveryStatusUpdateRequest {
    private Long orderId;
    private String status;
    private String notes;
    private Double currentLatitude;
    private Double currentLongitude;
    private boolean isOnline;

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getCurrentLatitude() {
        return this.currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public Double getCurrentLongitude() {
        return this.currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    public void setOnline(boolean online) {
        this.isOnline = online;
    }
}
