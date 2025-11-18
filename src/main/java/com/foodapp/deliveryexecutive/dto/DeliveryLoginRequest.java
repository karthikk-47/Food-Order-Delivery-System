package com.foodapp.deliveryexecutive.dto;

public class DeliveryLoginRequest {

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	private String mobile;
	private String otp;
}
