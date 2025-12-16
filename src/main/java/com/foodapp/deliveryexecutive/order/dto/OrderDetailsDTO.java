package com.foodapp.deliveryexecutive.order.dto;

import com.foodapp.deliveryexecutive.order.entity.Order;
import org.springframework.data.geo.Point;

public class OrderDetailsDTO {
    private long orderId;
    private long userId;
    private long homeMakerId;
    private String homemakerName;
    private Point pickupLocation;
    private Point deliveryLocation;
    private double amount;
    private double deliveryFee;
    private String orderDate;
    private Order.OrderStatus orderStatus;
    private double distance;
    private String paymentMethod;
    private double payment;
    private long executiveId;

    public String getHomemakerName() {
        return this.homemakerName;
    }

    public void setHomemakerName(String homemakerName) {
        this.homemakerName = homemakerName;
    }

    public double getDeliveryFee() {
        return this.deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPayment() {
        return this.payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Order.OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Order.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getExecutiveId() {
        return this.executiveId;
    }

    public void setExecutiveId(long executiveId) {
        this.executiveId = executiveId;
    }

    public long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getHomeMakerId() {
        return this.homeMakerId;
    }

    public void setHomeMakerId(long homeMakerId) {
        this.homeMakerId = homeMakerId;
    }

    public Point getPickupLocation() {
        return this.pickupLocation;
    }

    public void setPickupLocation(Point pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Point getDeliveryLocation() {
        return this.deliveryLocation;
    }

    public void setDeliveryLocation(Point deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
