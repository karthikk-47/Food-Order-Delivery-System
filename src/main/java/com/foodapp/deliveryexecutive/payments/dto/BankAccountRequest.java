package com.foodapp.deliveryexecutive.payments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BankAccountRequest {
    @NotBlank(message = "Account holder name is required")
    private String accountHolderName;

    @NotBlank(message = "Account number is required")
    @Pattern(regexp = "^[0-9]{9,18}$", message = "Invalid account number")
    private String accountNumber;

    @NotBlank(message = "IFSC code is required")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code")
    private String ifscCode;

    @NotBlank(message = "Bank name is required")
    private String bankName;

    private String branchName;

    @NotBlank(message = "Account type is required")
    private String accountType = "savings";

    private boolean isPrimary = true;
}
