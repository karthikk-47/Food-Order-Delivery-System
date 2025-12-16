package com.foodapp.deliveryexecutive.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VerifyOTPRequest {
    @NotBlank(message = "Mobile number is required")
    private String mobile;
    
    @NotBlank(message = "OTP is required")
    private String otp;
}
