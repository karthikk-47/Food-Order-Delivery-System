package com.foodapp.deliveryexecutive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long homeMakerId;
	private long userId;
	private String pickupAddress;
	private String deliveryAddress;
	private double amount;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	private String customerOtp;
	//private long executiveId;
	@ManyToOne
	private DeliveryExecutive executive;
	
//	public long getExecutiveId() {
//		return executiveId;
//	}
//
//	public void setExecutiveId(long executiveId) {
//		this.executiveId = executiveId;
//	}

	public enum OrderStatus {
		PENDING, PREPARING, PREPARED, ACCEPTED, OUTFORDELIVERY, DELIVERED, CANCELLED
}	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHomeMakerId() {
		return homeMakerId;
	}

	public void setHomeMakerId(long homeMakerId) {
		this.homeMakerId = homeMakerId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCustomerOtp() {
		return customerOtp;
	}

	public void setCustomerOtp(String customerOtp) {
		this.customerOtp = customerOtp;
	}

//	public DeliveryExecutive getDeliveryExecutive() {
//		return deliveryExecutive;
//	}
//
//	public void setDeliveryExecutive(DeliveryExecutive deliveryExecutive) {
//		this.deliveryExecutive = deliveryExecutive;
//	}
//

	

}
