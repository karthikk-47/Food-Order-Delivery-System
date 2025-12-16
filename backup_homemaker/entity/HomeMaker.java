/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.OneToMany
 *  lombok.Generated
 *  org.springframework.data.geo.Point
 */
package com.foodapp.deliveryexecutive.homemaker.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.order.entity.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Generated;
import org.springframework.data.geo.Point;

@Entity
public class HomeMaker
extends Actor {
    private String name;
    private String mobile;
    private String password;
    private String address;
    @Enumerated(value=EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;
    private String rejectionReason;
    private Point location;
    @OneToMany
    private List<Order> orders;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point getLocation() {
        return this.location;
    }

    public void setLocation(Point location) {
        this.location = location;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HomeMaker() {
        this.approvalStatus = ApprovalStatus.PENDING;
    }

    public ApprovalStatus getApprovalStatus() {
        return this.approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRejectionReason() {
        return this.rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Generated
    public HomeMaker(String name, String mobile, String password, String address, ApprovalStatus approvalStatus, String rejectionReason, Point location, List<Order> orders) {
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.address = address;
        this.approvalStatus = approvalStatus;
        this.rejectionReason = rejectionReason;
        this.location = location;
        this.orders = orders;
    }

    public static enum ApprovalStatus {
        PENDING,
        APPROVED,
        REJECTED;

    }
}
