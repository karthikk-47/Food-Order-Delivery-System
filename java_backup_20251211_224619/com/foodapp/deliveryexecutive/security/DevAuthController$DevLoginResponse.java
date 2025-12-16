/*
 * Decompiled with CFR 0.152.
 */
package com.foodapp.deliveryexecutive.security;

import com.foodapp.deliveryexecutive.common.entity.Actor;

public static class DevAuthController.DevLoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long userId;
    private String mobile;
    private Actor.Role role;

    public DevAuthController.DevLoginResponse(String accessToken, Long userId, String mobile, Actor.Role role) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.mobile = mobile;
        this.role = role;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getMobile() {
        return this.mobile;
    }

    public Actor.Role getRole() {
        return this.role;
    }
}
