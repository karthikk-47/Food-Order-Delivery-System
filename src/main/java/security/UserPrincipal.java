/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.security.core.GrantedAuthority
 *  org.springframework.security.core.authority.SimpleGrantedAuthority
 *  org.springframework.security.core.userdetails.UserDetails
 */
package com.foodapp.deliveryexecutive.security;

import com.foodapp.deliveryexecutive.admin.entity.Admin;
import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.user.entity.User;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal
implements UserDetails {
    private Long id;
    private String mobile;
    private String password;
    private Actor.Role role;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String mobile, String password, Actor.Role role, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    public static UserPrincipal create(DeliveryExecutive executive) {
        return new UserPrincipal(executive.getId(), executive.getMobile(), executive.getPassword(), Actor.Role.DELIVERYEXECUTIVE, Collections.singletonList(new SimpleGrantedAuthority("ROLE_DELIVERYEXECUTIVE")));
    }

    public static UserPrincipal create(Admin admin) {
        return new UserPrincipal(admin.getId(), admin.getUsername(), admin.getPassword(), Actor.Role.ADMIN, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user.getId(), user.getMobile(), user.getPassword(), Actor.Role.USER, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public static UserPrincipal create(HomeMaker homeMaker) {
        return new UserPrincipal(homeMaker.getId(), homeMaker.getMobile(), homeMaker.getPassword(), Actor.Role.HOMEMAKER, Collections.singletonList(new SimpleGrantedAuthority("ROLE_HOMEMAKER")));
    }

    public Long getId() {
        return this.id;
    }

    public String getMobile() {
        return this.mobile;
    }

    public Actor.Role getRole() {
        return this.role;
    }

    public String getUsername() {
        return this.mobile;
    }

    public String getPassword() {
        return this.password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
