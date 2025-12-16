package com.foodapp.deliveryexecutive.payments.service;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.payments.dto.BankAccountRequest;
import com.foodapp.deliveryexecutive.payments.dto.BankAccountResponse;
import com.foodapp.deliveryexecutive.payments.entity.UserBankAccount;
import com.foodapp.deliveryexecutive.payments.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public BankAccountResponse addBankAccount(Long userId, Actor.Role userRole, BankAccountRequest request) {
        // Check if account already exists
        if (bankAccountRepository.existsByUserIdAndUserRoleAndAccountNumber(userId, userRole, request.getAccountNumber())) {
            throw new IllegalArgumentException("Bank account already exists");
        }

        // If this is primary, unset other primary accounts
        if (request.isPrimary()) {
            bankAccountRepository.findByUserIdAndUserRole(userId, userRole)
                    .forEach(acc -> {
                        acc.setPrimary(false);
                        bankAccountRepository.save(acc);
                    });
        }

        UserBankAccount account = UserBankAccount.builder()
                .userId(userId)
                .userRole(userRole)
                .accountHolderName(request.getAccountHolderName())
                .accountNumber(request.getAccountNumber())
                .ifscCode(request.getIfscCode().toUpperCase())
                .bankName(request.getBankName())
                .branchName(request.getBranchName())
                .accountType(request.getAccountType())
                .isPrimary(request.isPrimary())
                .isVerified(false)
                .build();

        account = bankAccountRepository.save(account);
        log.info("Added bank account for user {} with role {}", userId, userRole);

        return BankAccountResponse.fromEntity(account);
    }

    public List<BankAccountResponse> getBankAccounts(Long userId, Actor.Role userRole) {
        return bankAccountRepository.findByUserIdAndUserRole(userId, userRole)
                .stream()
                .map(BankAccountResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public BankAccountResponse getPrimaryBankAccount(Long userId, Actor.Role userRole) {
        return bankAccountRepository.findByUserIdAndUserRoleAndIsPrimaryTrue(userId, userRole)
                .map(BankAccountResponse::fromEntity)
                .orElse(null);
    }

    public UserBankAccount getPrimaryBankAccountEntity(Long userId, Actor.Role userRole) {
        return bankAccountRepository.findByUserIdAndUserRoleAndIsPrimaryTrue(userId, userRole)
                .orElse(null);
    }

    @Transactional
    public BankAccountResponse setPrimaryAccount(Long userId, Actor.Role userRole, Long accountId) {
        UserBankAccount account = bankAccountRepository.findByIdAndUserIdAndUserRole(accountId, userId, userRole)
                .orElseThrow(() -> new ResourceNotFoundException("Bank account not found"));

        // Unset other primary accounts
        bankAccountRepository.findByUserIdAndUserRole(userId, userRole)
                .forEach(acc -> {
                    acc.setPrimary(false);
                    bankAccountRepository.save(acc);
                });

        account.setPrimary(true);
        account = bankAccountRepository.save(account);
        log.info("Set primary bank account {} for user {}", accountId, userId);

        return BankAccountResponse.fromEntity(account);
    }

    @Transactional
    public void deleteBankAccount(Long userId, Actor.Role userRole, Long accountId) {
        UserBankAccount account = bankAccountRepository.findByIdAndUserIdAndUserRole(accountId, userId, userRole)
                .orElseThrow(() -> new ResourceNotFoundException("Bank account not found"));

        bankAccountRepository.delete(account);
        log.info("Deleted bank account {} for user {}", accountId, userId);

        // If deleted account was primary, set another as primary
        if (account.isPrimary()) {
            bankAccountRepository.findByUserIdAndUserRole(userId, userRole)
                    .stream()
                    .findFirst()
                    .ifPresent(acc -> {
                        acc.setPrimary(true);
                        bankAccountRepository.save(acc);
                    });
        }
    }

    @Transactional
    public void updateRazorpayIds(Long accountId, String contactId, String fundAccountId) {
        bankAccountRepository.findById(accountId).ifPresent(account -> {
            account.setRazorpayContactId(contactId);
            account.setRazorpayFundAccountId(fundAccountId);
            account.setVerified(true);
            bankAccountRepository.save(account);
            log.info("Updated Razorpay IDs for bank account {}", accountId);
        });
    }
}
