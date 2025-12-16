/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.validation.annotation.Validated
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.profile.controller;

import com.foodapp.deliveryexecutive.profile.dto.AddBankAccountRequest;
import com.foodapp.deliveryexecutive.profile.dto.BankAccountDTO;
import com.foodapp.deliveryexecutive.profile.dto.ExecutiveProfileDTO;
import com.foodapp.deliveryexecutive.profile.dto.UpdateProfileRequest;
import com.foodapp.deliveryexecutive.profile.service.ExecutiveProfileService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/profile"})
@Validated
public class ExecutiveProfileController {
    @Autowired
    private ExecutiveProfileService profileService;

    @GetMapping(value={"/{executiveId}"})
    public ResponseEntity<ExecutiveProfileDTO> getProfile(@PathVariable Long executiveId) {
        ExecutiveProfileDTO profile = this.profileService.getProfile(executiveId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping(value={"/{executiveId}"})
    public ResponseEntity<ExecutiveProfileDTO> updateProfile(@PathVariable Long executiveId, @Valid @RequestBody UpdateProfileRequest request) {
        ExecutiveProfileDTO profile = this.profileService.updateProfile(executiveId, request);
        return ResponseEntity.ok(profile);
    }

    @PostMapping(value={"/{executiveId}/change-password"})
    public ResponseEntity<Void> changePassword(@PathVariable Long executiveId, @RequestParam String oldPassword, @RequestParam String newPassword) {
        try {
            this.profileService.changePassword(executiveId, oldPassword, newPassword);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{executiveId}/bank-accounts"})
    public ResponseEntity<List<BankAccountDTO>> getBankAccounts(@PathVariable Long executiveId) {
        List<BankAccountDTO> accounts = this.profileService.getBankAccounts(executiveId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping(value={"/{executiveId}/bank-accounts/primary"})
    public ResponseEntity<BankAccountDTO> getPrimaryBankAccount(@PathVariable Long executiveId) {
        BankAccountDTO account = this.profileService.getPrimaryBankAccount(executiveId);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping(value={"/{executiveId}/bank-accounts"})
    public ResponseEntity<BankAccountDTO> addBankAccount(@PathVariable Long executiveId, @Valid @RequestBody AddBankAccountRequest request) {
        try {
            BankAccountDTO account = this.profileService.addBankAccount(executiveId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(account);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value={"/{executiveId}/bank-accounts/{bankAccountId}/set-primary"})
    public ResponseEntity<BankAccountDTO> setPrimaryBankAccount(@PathVariable Long executiveId, @PathVariable Long bankAccountId) {
        try {
            BankAccountDTO account = this.profileService.setPrimaryBankAccount(executiveId, bankAccountId);
            return ResponseEntity.ok(account);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value={"/{executiveId}/bank-accounts/{bankAccountId}"})
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long executiveId, @PathVariable Long bankAccountId) {
        try {
            this.profileService.deleteBankAccount(executiveId, bankAccountId);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
