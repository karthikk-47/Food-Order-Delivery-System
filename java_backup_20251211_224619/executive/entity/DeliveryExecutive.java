/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.ElementCollection
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.MapKeyColumn
 *  jakarta.persistence.OneToMany
 *  org.springframework.data.geo.Point
 */
package com.foodapp.deliveryexecutive.executive.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import org.springframework.data.geo.Point;

@Entity
public class DeliveryExecutive
extends Actor {
    private String name;
    private String mobile;
    private String password;
    private String aadharNo;
    private String licenseNo;
    @Enumerated(value=EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;
    private String rejectionReason;
    @ElementCollection
    @MapKeyColumn(name="timestamp")
    @Column(name="location")
    private Map<LocalTime, Point> location;
    boolean online = false;
    @OneToMany
    private List<Order> orders = null;

    public void addLocation(LocalTime time, Point location) {
        this.location.put(time, location);
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    public String getAadharNo() {
        return this.aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getLicenseNo() {
        return this.licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public boolean isOnline() {
        return this.online;
    }

    public void setOnline(boolean online) {
        this.online = online;
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

    public static enum ApprovalStatus {
        PENDING,
        APPROVED,
        REJECTED;

    }
}
