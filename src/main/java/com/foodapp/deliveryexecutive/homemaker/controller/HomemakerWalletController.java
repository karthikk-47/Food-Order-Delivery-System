package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.payments.dto.BankAccountRequest;
import com.foodapp.deliveryexecutive.payments.dto.BankAccountResponse;
import com.foodapp.deliveryexecutive.payments.entity.UserBankAccount;
import com.foodapp.deliveryexecutive.payments.service.BankAccountService;
import com.foodapp.deliveryexecutive.wallet.dto.WalletDTO;
import com.foodapp.deliveryexecutive.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/homemaker/wallet")
@Slf4j
@RequiredArgsConstructor
public class HomemakerWalletController {

    private final WalletService walletService;
    private final BankAccountService bankAccountService;

    @GetMapping("/{homemakerId}")
    public ResponseEntity<?> getWallet(@PathVariable Long homemakerId) {
        log.info("Fetching wallet for homemaker: {}", homemakerId);
        try {
            WalletDTO wallet = walletService.getWalletDTO(homemakerId, Actor.Role.HOMEMAKER);
            return ResponseEntity.ok(wallet);
        } catch (Exception e) {
            log.error("Error fetching homemaker wallet", e);
            Map<String, Object> emptyWallet = new HashMap<>();
            emptyWallet.put("balance", 0.0);
            emptyWallet.put("totalEarnings", 0.0);
            emptyWallet.put("pendingAmount", 0.0);
            emptyWallet.put("transactions", List.of());
            return ResponseEntity.ok(emptyWallet);
        }
    }

    @PostMapping("/{homemakerId}/withdraw")
    public ResponseEntity<?> requestWithdrawal(
            @PathVariable Long homemakerId,
            @RequestBody Map<String, Object> request) {
        log.info("Processing withdrawal request for homemaker: {}", homemakerId);
        try {
            Double amount = ((Number) request.get("amount")).doubleValue();
            
            // Check if homemaker has a bank account
            UserBankAccount bankAccount = bankAccountService.getPrimaryBankAccountEntity(
                    homemakerId, Actor.Role.HOMEMAKER);
            if (bankAccount == null) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Please add a bank account before withdrawing"
                ));
            }
            
            // Check minimum withdrawal
            if (amount < 100) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Minimum withdrawal amount is ₹100"
                ));
            }
            
            // Check if homemaker has sufficient balance
            if (!walletService.hasBalance(homemakerId, Actor.Role.HOMEMAKER, amount)) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Insufficient balance"
                ));
            }
            
            // Deduct from wallet
            walletService.deductBalance(homemakerId, Actor.Role.HOMEMAKER, amount);
            
            // TODO: Integrate with Razorpay Payout API here
            // For now, just record the withdrawal
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Withdrawal request submitted successfully. Amount will be credited to your bank account within 24-48 hours.",
                "amount", amount,
                "bankAccount", bankAccount.getBankName() + " ****" + 
                    bankAccount.getAccountNumber().substring(bankAccount.getAccountNumber().length() - 4)
            ));
        } catch (Exception e) {
            log.error("Error processing withdrawal", e);
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Failed to process withdrawal: " + e.getMessage()
            ));
        }
    }

    @GetMapping("/{homemakerId}/bank-accounts")
    public ResponseEntity<List<BankAccountResponse>> getBankAccounts(@PathVariable Long homemakerId) {
        List<BankAccountResponse> accounts = bankAccountService.getBankAccounts(
                homemakerId, Actor.Role.HOMEMAKER);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/{homemakerId}/bank-accounts")
    public ResponseEntity<?> addBankAccount(
            @PathVariable Long homemakerId,
            @Valid @RequestBody BankAccountRequest request) {
        try {
            BankAccountResponse response = bankAccountService.addBankAccount(
                    homemakerId, Actor.Role.HOMEMAKER, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            log.error("Error adding bank account", e);
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Failed to add bank account"
            ));
        }
    }

    @DeleteMapping("/{homemakerId}/bank-accounts/{accountId}")
    public ResponseEntity<?> deleteBankAccount(
            @PathVariable Long homemakerId,
            @PathVariable Long accountId) {
        try {
            bankAccountService.deleteBankAccount(homemakerId, Actor.Role.HOMEMAKER, accountId);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Bank account deleted successfully"
            ));
        } catch (Exception e) {
            log.error("Error deleting bank account", e);
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Failed to delete bank account"
            ));
        }
    }

    @GetMapping("/{homemakerId}/withdrawals")
    public ResponseEntity<?> getWithdrawalHistory(@PathVariable Long homemakerId) {
        log.info("Fetching withdrawal history for homemaker: {}", homemakerId);
        return ResponseEntity.ok(List.of());
    }
}
