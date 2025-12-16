package com.foodapp.deliveryexecutive.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordRequest {
    @NotBlank(message = "Mobile number is required")
    private String mobile;
}
