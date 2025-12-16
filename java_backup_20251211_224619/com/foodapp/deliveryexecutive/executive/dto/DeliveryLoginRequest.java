/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.NotBlank
 */
package com.foodapp.deliveryexecutive.executive.dto;

import jakarta.validation.constraints.NotBlank;

public class DeliveryLoginRequest {
    @NotBlank(message="Mobile number is required")
    private @NotBlank(message="Mobile number is required") String mobile;
    @NotBlank(message="Password is required")
    private @NotBlank(message="Password is required") String password;

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
