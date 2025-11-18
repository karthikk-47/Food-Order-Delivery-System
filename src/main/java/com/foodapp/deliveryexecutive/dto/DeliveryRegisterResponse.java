package com.foodapp.deliveryexecutive.dto;

public class DeliveryRegisterResponse {

	private Long executiveId;
	public Long getExecutiveId() {
		return executiveId;
	}
	public void setExecutiveId(Long executiveId) {
		this.executiveId = executiveId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
}
