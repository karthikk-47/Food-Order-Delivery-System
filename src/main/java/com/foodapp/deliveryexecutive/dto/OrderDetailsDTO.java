package com.foodapp.deliveryexecutive.dto;

import com.foodapp.deliveryexecutive.entity.Order.OrderStatus;

public class OrderDetailsDTO {
	private long orderId;
	private long userId;
	private long homeMakerId;
	private String pickupAddress;
	private String deliveryAddress;
	private double amount; 
	private OrderStatus orderStatus;
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public long getExecutiveId() {
		return executiveId;
	}
	public void setExecutiveId(long executiveId) {
		this.executiveId = executiveId;
	}
	private long executiveId;
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getHomeMakerId() {
		return homeMakerId;
	}
	public void setHomeMakerId(long homeMakerId) {
		this.homeMakerId = homeMakerId;
	}
	public String getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}
