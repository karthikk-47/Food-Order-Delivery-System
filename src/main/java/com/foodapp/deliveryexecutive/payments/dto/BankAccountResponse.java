package com.foodapp.deliveryexecutive.payments.dto;

import com.foodapp.deliveryexecutive.payments.entity.UserBankAccount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccountResponse {
    private Long id;
    private String accountHolderName;
    private String accountNumberMasked;
    private String ifscCode;
    private String bankName;
    private String branchName;
    private String accountType;
    private boolean isPrimary;
    private boolean isVerified;

    public static BankAccountResponse fromEntity(UserBankAccount account) {
        String maskedNumber = maskAccountNumber(account.getAccountNumber());
        return BankAccountResponse.builder()
                .id(account.getId())
                .accountHolderName(account.getAccountHolderName())
                .accountNumberMasked(maskedNumber)
                .ifscCode(account.getIfscCode())
                .bankName(account.getBankName())
                .branchName(account.getBranchName())
                .accountType(account.getAccountType())
                .isPrimary(account.isPrimary())
                .isVerified(account.isVerified())
                .build();
    }

    private static String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) {
            return "****";
        }
        int len = accountNumber.length();
        return "****" + accountNumber.substring(len - 4);
    }
}
