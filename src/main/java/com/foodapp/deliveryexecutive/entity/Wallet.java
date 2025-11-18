package com.foodapp.deliveryexecutive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Wallet {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long customerId;
	@Enumerated(EnumType.STRING)
	private CustomerRole customerRole;
	private double balance;
public enum CustomerRole{
	HOMEMAKER, DELIVERYEXECUTIVE
}
	
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
	public void setCustomerRole(CustomerRole role) {
		this.customerRole = role;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
