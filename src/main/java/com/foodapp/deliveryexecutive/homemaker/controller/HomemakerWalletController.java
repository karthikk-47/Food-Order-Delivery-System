/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerWalletDTO;
import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerWithdrawalDTO;
import com.foodapp.deliveryexecutive.homemaker.service.HomemakerWalletService;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/homemaker/wallet"})
public class HomemakerWalletController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(HomemakerWalletController.class);
    private final HomemakerWalletService homemakerWalletService;

    @GetMapping(value={"/{homemakerId}"})
    public ResponseEntity<HomemakerWalletDTO> getWallet(@PathVariable Long homemakerId) {
        log.info("Fetching wallet for homemaker: {}", homemakerId);
        try {
            HomemakerWalletDTO wallet = this.homemakerWalletService.getWallet(homemakerId);
            return ResponseEntity.ok(wallet);
        }
        catch (Exception e) {
            log.error("Error fetching wallet", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value={"/{homemakerId}/withdraw"})
    public ResponseEntity<HomemakerWithdrawalDTO> requestWithdrawal(@PathVariable Long homemakerId, @RequestBody HomemakerWithdrawalDTO withdrawalDTO) {
        log.info("Processing withdrawal request for homemaker: {}, amount: \u20b9{}", homemakerId, withdrawalDTO.getAmount());
        try {
            HomemakerWithdrawalDTO result = this.homemakerWalletService.requestWithdrawal(homemakerId, withdrawalDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        catch (IllegalArgumentException e) {
            log.error("Invalid withdrawal request: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (Exception e) {
            log.error("Error processing withdrawal", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{homemakerId}/withdrawals"})
    public ResponseEntity<List<HomemakerWithdrawalDTO>> getWithdrawalHistory(@PathVariable Long homemakerId) {
        log.info("Fetching withdrawal history for homemaker: {}", homemakerId);
        try {
            List<HomemakerWithdrawalDTO> withdrawals = this.homemakerWalletService.getWithdrawalHistory(homemakerId);
            return ResponseEntity.ok(withdrawals);
        }
        catch (Exception e) {
            log.error("Error fetching withdrawal history", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/withdrawal/{withdrawalId}"})
    public ResponseEntity<HomemakerWithdrawalDTO> getWithdrawal(@PathVariable Long withdrawalId) {
        log.info("Fetching withdrawal: {}", withdrawalId);
        try {
            HomemakerWithdrawalDTO withdrawal = this.homemakerWalletService.getWithdrawal(withdrawalId);
            return ResponseEntity.ok(withdrawal);
        }
        catch (Exception e) {
            log.error("Error fetching withdrawal", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value={"/admin/pending"})
    public ResponseEntity<List<HomemakerWithdrawalDTO>> getPendingWithdrawals() {
        log.info("Fetching all pending withdrawals");
        try {
            List<HomemakerWithdrawalDTO> withdrawals = this.homemakerWalletService.getPendingWithdrawals();
            return ResponseEntity.ok(withdrawals);
        }
        catch (Exception e) {
            log.error("Error fetching pending withdrawals", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/admin/withdrawal/{withdrawalId}/approve"})
    public ResponseEntity<HomemakerWithdrawalDTO> approveWithdrawal(@PathVariable Long withdrawalId, @RequestParam String transactionId) {
        log.info("Approving withdrawal: {}", withdrawalId);
        try {
            HomemakerWithdrawalDTO result = this.homemakerWalletService.approveWithdrawal(withdrawalId, transactionId);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            log.error("Error approving withdrawal", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/admin/withdrawal/{withdrawalId}/complete"})
    public ResponseEntity<HomemakerWithdrawalDTO> completeWithdrawal(@PathVariable Long withdrawalId) {
        log.info("Completing withdrawal: {}", withdrawalId);
        try {
            HomemakerWithdrawalDTO result = this.homemakerWalletService.completeWithdrawal(withdrawalId);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            log.error("Error completing withdrawal", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/admin/withdrawal/{withdrawalId}/reject"})
    public ResponseEntity<HomemakerWithdrawalDTO> rejectWithdrawal(@PathVariable Long withdrawalId, @RequestParam String reason) {
        log.info("Rejecting withdrawal: {}", withdrawalId);
        try {
            HomemakerWithdrawalDTO result = this.homemakerWalletService.rejectWithdrawal(withdrawalId, reason);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            log.error("Error rejecting withdrawal", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Generated
    public HomemakerWalletController(HomemakerWalletService homemakerWalletService) {
        this.homemakerWalletService = homemakerWalletService;
    }
}
