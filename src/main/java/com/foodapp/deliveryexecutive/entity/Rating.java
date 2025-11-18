package com.foodapp.deliveryexecutive.entity;

import com.foodapp.deliveryexecutive.entity.Wallet.CustomerRole;

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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public CustomerRole getCustomerRole() {
		return customerRole;
	}
	public void setCustomerRole(CustomerRole customerRole) {
		this.customerRole = customerRole;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	private long customerId;
	@Enumerated(EnumType.STRING)
	private CustomerRole customerRole;
	private int stars;
	private String comment;
}
