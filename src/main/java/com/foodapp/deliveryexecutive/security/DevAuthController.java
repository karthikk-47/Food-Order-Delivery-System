/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.context.annotation.Profile
 *  org.springframework.http.ResponseEntity
 *  org.springframework.security.authentication.UsernamePasswordAuthenticationToken
 *  org.springframework.security.core.Authentication
 *  org.springframework.security.core.userdetails.UserDetails
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.security;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.security.CustomUserDetailsService;
import com.foodapp.deliveryexecutive.security.JwtTokenProvider;
import com.foodapp.deliveryexecutive.security.UserPrincipal;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/auth"})
@Profile(value={"dev"})
public class DevAuthController {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping(value={"/dev-login/{mobile}"})
    public ResponseEntity<?> devLogin(@PathVariable(value="mobile") String mobile) {
        try {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(mobile);
            if (Objects.isNull(userDetails)) {
                return ResponseEntity.badRequest().body(new SimpleApiResponse(false, "User not found"));
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            String jwt = this.tokenProvider.createToken((Authentication)authentication);
            DevLoginResponse resp = new DevLoginResponse(jwt, ((UserPrincipal)userDetails).getId(), ((UserPrincipal)userDetails).getMobile(), ((UserPrincipal)userDetails).getRole());
            return ResponseEntity.ok(resp);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new SimpleApiResponse(false, "Dev login failed"));
        }
    }

    public static class SimpleApiResponse {
        private Boolean success;
        private String message;

        public SimpleApiResponse(Boolean success, String message) {
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

    public static class DevLoginResponse {
        private String accessToken;
        private String tokenType = "Bearer";
        private Long userId;
        private String mobile;
        private Actor.Role role;

        public DevLoginResponse(String accessToken, Long userId, String mobile, Actor.Role role) {
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
}
