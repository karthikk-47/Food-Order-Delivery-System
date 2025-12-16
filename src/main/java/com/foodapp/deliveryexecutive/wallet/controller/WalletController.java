/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.wallet.controller;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.wallet.dto.WalletDTO;
import com.foodapp.deliveryexecutive.wallet.entity.Wallet;
import com.foodapp.deliveryexecutive.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/wallet"})
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping(value={"/{customerId}"})
    public ResponseEntity<WalletDTO> getWallet(@PathVariable Long customerId, @RequestParam Actor.Role role) {
        WalletDTO wallet = this.walletService.getWalletDTO(customerId, role);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping(value={"/{customerId}/balance"})
    public ResponseEntity<Double> getBalance(@PathVariable Long customerId, @RequestParam Actor.Role role) {
        Double balance = this.walletService.getBalance(customerId, role);
        return ResponseEntity.ok(balance);
    }

    @PostMapping(value={"/{customerId}/create"})
    public ResponseEntity<Wallet> createWallet(@PathVariable Long customerId, @RequestParam Actor.Role role) {
        try {
            Wallet wallet = this.walletService.createWallet(customerId, role);
            return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{customerId}/add"})
    public ResponseEntity<Wallet> addBalance(@PathVariable Long customerId, @RequestParam Actor.Role role, @RequestParam Double amount) {
        try {
            Wallet wallet = this.walletService.addBalance(customerId, role, amount);
            return ResponseEntity.ok(wallet);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{customerId}/deduct"})
    public ResponseEntity<Wallet> deductBalance(@PathVariable Long customerId, @RequestParam Actor.Role role, @RequestParam Double amount) {
        try {
            Wallet wallet = this.walletService.deductBalance(customerId, role, amount);
            return ResponseEntity.ok(wallet);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/transfer"})
    public ResponseEntity<Wallet> transferBalance(@RequestParam Long fromCustomerId, @RequestParam Long toCustomerId, @RequestParam Actor.Role fromRole, @RequestParam Actor.Role toRole, @RequestParam Double amount) {
        try {
            Wallet wallet = this.walletService.transferBalance(fromCustomerId, toCustomerId, fromRole, toRole, amount);
            return ResponseEntity.ok(wallet);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{customerId}/has-balance"})
    public ResponseEntity<Boolean> hasBalance(@PathVariable Long customerId, @RequestParam Actor.Role role, @RequestParam Double amount) {
        boolean hasBalance = this.walletService.hasBalance(customerId, role, amount);
        return ResponseEntity.ok(hasBalance);
    }
}
