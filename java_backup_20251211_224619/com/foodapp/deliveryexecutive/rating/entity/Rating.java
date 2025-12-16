/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 */
package com.foodapp.deliveryexecutive.rating.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private long customerId;
    @Enumerated(value=EnumType.STRING)
    private Actor.Role role;
    private int stars;
    private String comment;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Actor.Role getCustomerRole() {
        return this.role;
    }

    public void setCustomerRole(Actor.Role role) {
        this.role = role;
    }

    public Actor.Role getRole() {
        return this.role;
    }

    public void setRole(Actor.Role role) {
        this.role = role;
    }

    public int getStars() {
        return this.stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
