package com.foodapp.deliveryexecutive.security;

public static class DevAuthController.SimpleApiResponse {
    private Boolean success;
    private String message;

    public DevAuthController.SimpleApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }
}
