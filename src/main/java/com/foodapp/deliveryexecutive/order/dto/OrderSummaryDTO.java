package com.foodapp.deliveryexecutive.order.dto;

import com.foodapp.deliveryexecutive.order.entity.Order;
import org.springframework.data.geo.Point;

public class OrderSummaryDTO {
    private Long orderId;
    private Order.OrderStatus orderStatus;
    private int distance;
    private double amount;
    private Point pickupLocation;
    private Point deliveryLocation;
    private double payment;
    private double deliveryFee;
    private double commissionRate;
    private double executiveEarning;
    private Order.OrderPriority priority;
    private boolean isSurgePricing;
    private double surgeMultiplier;
    private Integer estimatedDeliveryTime;
    private String paymentMethod;
    private double optimalScore;

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Order.OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Order.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public double getPayment() {
        return this.payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getDeliveryFee() {
        return this.deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getCommissionRate() {
        return this.commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getExecutiveEarning() {
        return this.executiveEarning;
    }

    public void setExecutiveEarning(double executiveEarning) {
        this.executiveEarning = executiveEarning;
    }

    public Order.OrderPriority getPriority() {
        return this.priority;
    }

    public void setPriority(Order.OrderPriority priority) {
        this.priority = priority;
    }

    public boolean isSurgePricing() {
        return this.isSurgePricing;
    }

    public void setSurgePricing(boolean surgePricing) {
        this.isSurgePricing = surgePricing;
    }

    public double getSurgeMultiplier() {
        return this.surgeMultiplier;
    }

    public void setSurgeMultiplier(double surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
    }

    public Integer getEstimatedDeliveryTime() {
        return this.estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Integer estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getOptimalScore() {
        return this.optimalScore;
    }

    public void setOptimalScore(double optimalScore) {
        this.optimalScore = optimalScore;
    }
}
