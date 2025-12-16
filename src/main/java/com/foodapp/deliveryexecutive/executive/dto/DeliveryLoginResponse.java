package com.foodapp.deliveryexecutive.executive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryLoginResponse {
    private String token;
    private String name;
    private String mobile;
    private String email;
    @JsonProperty(value="executiveId")
    private Long deliveryExecutiveId;
    private String status;
    private String message;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDeliveryExecutiveId() {
        return this.deliveryExecutiveId;
    }

    public void setDeliveryExecutiveId(Long deliveryExecutiveId) {
        this.deliveryExecutiveId = deliveryExecutiveId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
