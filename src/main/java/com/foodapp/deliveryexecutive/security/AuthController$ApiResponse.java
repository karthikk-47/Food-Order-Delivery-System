/*
 * Decompiled with CFR 0.152.
 */
package com.foodapp.deliveryexecutive.security;

public static class AuthController.ApiResponse {
    private Boolean success;
    private String message;

    public AuthController.ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
