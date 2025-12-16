package com.foodapp.deliveryexecutive.payments.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Embeddable class for Razorpay bank account details.
 * Used in FundAccount and CreateFundAccountRequest for Razorpay API integration.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private String name;
    private String bank_name;
    private String ifsc;
    private String account_number;
}
