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
        log.info("Fetching wallet for homemaker: {}", (Object)homemakerId);
        try {
            HomemakerWalletDTO wallet = this.homemakerWalletService.getWallet(homemakerId);
            return ResponseEntity.ok((Object)wallet);
        }
        catch (Exception e) {
            log.error("Error fetching wallet", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value={"/{homemakerId}/withdraw"})
    public ResponseEntity<HomemakerWithdrawalDTO> requestWithdrawal(@PathVariable Long homemakerId, @RequestBody HomemakerWithdrawalDTO withdrawalDTO) {
        log.info("Processing withdrawal request for homemaker: {}, amount: \u20b9{}", (Object)homemakerId, (Object)withdrawalDTO.getAmount());
        try {
            HomemakerWithdrawalDTO result = this.homemakerWalletService.requestWithdrawal(homemakerId, withdrawalDTO);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body((Object)result);
        }
        catch (IllegalArgumentException e) {
            log.error("Invalid withdrawal request: {}", (Object)e.getMessage());
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
        catch (Exception e) {
            log.error("Error processing withdrawal", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{homemakerId}/withdrawals"})
    public ResponseEntity<List<HomemakerWithdrawalDTO>> getWithdrawalHistory(@PathVariable Long homemakerId) {
        log.info("Fetching withdrawal history for homemaker: {}", (Object)homemakerId);
        try {
            List<HomemakerWithdrawalDTO> withdrawals = this.homemakerWalletService.getWithdrawalHistory(homemakerId);
            return ResponseEntity.ok(withdrawals);
        }
        catch (Exception e) {
            log.error("Error fetching withdrawal history", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/withdrawal/{withdrawalId}"})
    public ResponseEntity<HomemakerWithdrawalDTO> getWithdrawal(@PathVariable Long withdrawalId) {
        log.info("Fetching withdrawal: {}", (Object)withdrawalId);
        try {
            HomemakerWithdrawalDTO withdrawal = this.homemakerWalletService.getWithdrawal(withdrawalId);
            return ResponseEntity.ok((Object)withdrawal);
        }
        catch (Exception e) {
            log.error("Error fetching withdrawal", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).build();
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
            log.error("Error fetching pending withdrawals", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/admin/withdrawal/{withdrawalId}/approve"})
    public ResponseEntity<HomemakerWithdrawalDTO> approveWithdrawal(@PathVariable Long withdrawalId, @RequestParam String transactionId) {
        log.info("Approving withdrawal: {}", (Object)withdrawalId);
        try {
            HomemakerWithdrawalDTO result = this.homemakerWalletService.approveWithdrawal(withdrawalId, transactionId);
            return ResponseEntity.ok((Object)result);
        }
        catch (Exception e) {
            log.error("Error approving withdrawal", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/admin/withdrawal/{withdrawalId}/complete"})
    public ResponseEntity<HomemakerWithdrawalDTO> completeWithdrawal(@PathVariable Long withdrawalId) {
        log.info("Completing withdrawal: {}", (Object)withdrawalId);
        try {
            HomemakerWithdrawalDTO result = this.homemakerWalletService.completeWithdrawal(withdrawalId);
            return ResponseEntity.ok((Object)result);
        }
        catch (Exception e) {
            log.error("Error completing withdrawal", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/admin/withdrawal/{withdrawalId}/reject"})
    public ResponseEntity<HomemakerWithdrawalDTO> rejectWithdrawal(@PathVariable Long withdrawalId, @RequestParam String reason) {
        log.info("Rejecting withdrawal: {}", (Object)withdrawalId);
        try {
            HomemakerWithdrawalDTO result = this.homemakerWalletService.rejectWithdrawal(withdrawalId, reason);
            return ResponseEntity.ok((Object)result);
        }
        catch (Exception e) {
            log.error("Error rejecting withdrawal", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @Generated
    public HomemakerWalletController(HomemakerWalletService homemakerWalletService) {
        this.homemakerWalletService = homemakerWalletService;
    }
}
