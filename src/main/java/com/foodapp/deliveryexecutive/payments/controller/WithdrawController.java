/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.format.annotation.DateTimeFormat
 *  org.springframework.format.annotation.DateTimeFormat$ISO
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.validation.annotation.Validated
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.payments.controller;

import com.foodapp.deliveryexecutive.payments.dto.WithdrawHistoryResponse;
import com.foodapp.deliveryexecutive.payments.dto.WithdrawRequest;
import com.foodapp.deliveryexecutive.payments.dto.WithdrawResponse;
import com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction;
import com.foodapp.deliveryexecutive.payments.service.WithdrawService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/withdraw"})
@Validated
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;

    @PostMapping(value={"/process"})
    public ResponseEntity<WithdrawResponse> processWithdraw(@Valid @RequestBody WithdrawRequest request) {
        WithdrawResponse response = this.withdrawService.processWithdraw(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping(value={"/history/{customerId}"})
    public ResponseEntity<List<WithdrawHistoryResponse>> getWithdrawHistory(@PathVariable Long customerId) {
        List<WithdrawHistoryResponse> history = this.withdrawService.getWithdrawHistory(customerId);
        return ResponseEntity.ok(history);
    }

    @GetMapping(value={"/history/{customerId}/status/{status}"})
    public ResponseEntity<List<WithdrawHistoryResponse>> getWithdrawHistoryByStatus(@PathVariable Long customerId, @PathVariable WithdrawTransaction.WithdrawStatus status) {
        List<WithdrawHistoryResponse> history = this.withdrawService.getWithdrawHistoryByStatus(customerId, status);
        return ResponseEntity.ok(history);
    }

    @GetMapping(value={"/history/{customerId}/daterange"})
    public ResponseEntity<List<WithdrawHistoryResponse>> getWithdrawHistoryByDateRange(@PathVariable Long customerId, @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<WithdrawHistoryResponse> history = this.withdrawService.getWithdrawHistoryByDateRange(customerId, startDate, endDate);
        return ResponseEntity.ok(history);
    }

    @PutMapping(value={"/status/{payoutId}"})
    public ResponseEntity<WithdrawResponse> updateWithdrawStatus(@PathVariable String payoutId) {
        WithdrawResponse response = this.withdrawService.updateWithdrawStatus(payoutId);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping(value={"/cancel/{payoutId}"})
    public ResponseEntity<WithdrawResponse> cancelWithdraw(@PathVariable String payoutId) {
        WithdrawResponse response = this.withdrawService.cancelWithdraw(payoutId);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping(value={"/total/{customerId}"})
    public ResponseEntity<Double> getTotalWithdrawn(@PathVariable Long customerId) {
        Double total = this.withdrawService.getTotalWithdrawn(customerId);
        return ResponseEntity.ok(total);
    }

    @GetMapping(value={"/count/{customerId}"})
    public ResponseEntity<Long> getWithdrawCount(@PathVariable Long customerId, @RequestParam WithdrawTransaction.WithdrawStatus status) {
        Long count = this.withdrawService.getWithdrawCount(customerId, status);
        return ResponseEntity.ok(count);
    }
}
