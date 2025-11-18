package com.foodapp.deliveryexecutive.dto;

import com.foodapp.deliveryexecutive.entity.Order.OrderStatus;

public class OrderSummaryDTO {

	private long orderId;
	private OrderStatus orderStatus;
	private double amount;
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
