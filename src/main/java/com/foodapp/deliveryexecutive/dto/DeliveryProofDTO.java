package com.foodapp.deliveryexecutive.dto;

public class DeliveryProofDTO {

	private String customerOtp;
	public String getCustomerOtp() {
		return customerOtp;
	}
	public void setCustomerOtp(String customerOtp) {
		this.customerOtp = customerOtp;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	private String photoUrl;
}
