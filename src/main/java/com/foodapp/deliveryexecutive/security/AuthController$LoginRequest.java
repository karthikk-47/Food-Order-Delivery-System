package com.foodapp.deliveryexecutive.security;

public static class AuthController.LoginRequest {
    private String mobile;
    private String password;

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
