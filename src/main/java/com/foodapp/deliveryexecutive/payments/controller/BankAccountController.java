package com.foodapp.deliveryexecutive.payments.controller;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.payments.dto.BankAccountRequest;
import com.foodapp.deliveryexecutive.payments.dto.BankAccountResponse;
import com.foodapp.deliveryexecutive.payments.service.BankAccountService;
import com.foodapp.deliveryexecutive.security.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bank-accounts")
@Slf4j
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<?> addBankAccount(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody BankAccountRequest request) {
        try {
            Actor.Role role = Actor.Role.valueOf(principal.getRole().toString());
            BankAccountResponse response = bankAccountService.addBankAccount(
                    principal.getId(), role, request);
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

    @GetMapping
    public ResponseEntity<List<BankAccountResponse>> getBankAccounts(
            @AuthenticationPrincipal UserPrincipal principal) {
        Actor.Role role = Actor.Role.valueOf(principal.getRole().toString());
        List<BankAccountResponse> accounts = bankAccountService.getBankAccounts(
                principal.getId(), role);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/primary")
    public ResponseEntity<?> getPrimaryBankAccount(
            @AuthenticationPrincipal UserPrincipal principal) {
        Actor.Role role = Actor.Role.valueOf(principal.getRole().toString());
        BankAccountResponse account = bankAccountService.getPrimaryBankAccount(
                principal.getId(), role);
        if (account == null) {
            return ResponseEntity.ok(Map.of("hasPrimaryAccount", false));
        }
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{accountId}/primary")
    public ResponseEntity<?> setPrimaryAccount(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long accountId) {
        try {
            Actor.Role role = Actor.Role.valueOf(principal.getRole().toString());
            BankAccountResponse response = bankAccountService.setPrimaryAccount(
                    principal.getId(), role, accountId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error setting primary account", e);
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Failed to set primary account"
            ));
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteBankAccount(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long accountId) {
        try {
            Actor.Role role = Actor.Role.valueOf(principal.getRole().toString());
            bankAccountService.deleteBankAccount(principal.getId(), role, accountId);
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
}
