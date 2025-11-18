package com.foodapp.deliveryexecutive.dto;

public class DeliveryLoginResponse {
	private Long executiveId;
	public Long getExecutiveId() {
		return executiveId;
	}
	public void setExecutiveId(Long executiveId) {
		this.executiveId = executiveId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	private String name;
	private String token;

}
