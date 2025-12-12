/*
 * Decompiled with CFR 0.152.
 */
package com.foodapp.deliveryexecutive.security;

import com.foodapp.deliveryexecutive.common.entity.Actor;

public static class AuthController.JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long userId;
    private String mobile;
    private Actor.Role role;

    public AuthController.JwtAuthenticationResponse(String accessToken, Long userId, String mobile, Actor.Role role) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.mobile = mobile;
        this.role = role;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Actor.Role getRole() {
        return this.role;
    }

    public void setRole(Actor.Role role) {
        this.role = role;
    }
}
