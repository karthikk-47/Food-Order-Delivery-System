/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
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
package com.foodapp.deliveryexecutive.admin.controller;

import com.foodapp.deliveryexecutive.admin.dto.VerificationDTO;
import com.foodapp.deliveryexecutive.admin.entity.Verification;
import com.foodapp.deliveryexecutive.admin.service.VerificationService;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value={"/api/verification"})
public class VerificationController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(VerificationController.class);
    @Autowired
    private VerificationService verificationService;

    @PostMapping(value={"/submit"})
    public ResponseEntity<VerificationDTO> submitVerification(@RequestBody VerificationDTO verificationDTO) {
        log.info("Submitting verification for user: {}", (Object)verificationDTO.getUserId());
        try {
            VerificationDTO submitted = this.verificationService.submitVerification(verificationDTO);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body((Object)submitted);
        }
        catch (Exception e) {
            log.error("Error submitting verification", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<VerificationDTO> getVerification(@PathVariable Long id) {
        log.info("Fetching verification with ID: {}", (Object)id);
        try {
            VerificationDTO verification = this.verificationService.getVerificationById(id);
            return ResponseEntity.ok((Object)verification);
        }
        catch (Exception e) {
            log.error("Error fetching verification", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value={"/{id}/approve"})
    public ResponseEntity<VerificationDTO> approveVerification(@PathVariable Long id, @RequestParam Long adminId) {
        log.info("Approving verification with ID: {} by admin: {}", (Object)id, (Object)adminId);
        try {
            VerificationDTO approved = this.verificationService.approveVerification(id, adminId);
            return ResponseEntity.ok((Object)approved);
        }
        catch (Exception e) {
            log.error("Error approving verification", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/reject"})
    public ResponseEntity<VerificationDTO> rejectVerification(@PathVariable Long id, @RequestParam Long adminId, @RequestParam String reason) {
        log.info("Rejecting verification with ID: {} by admin: {}", (Object)id, (Object)adminId);
        try {
            VerificationDTO rejected = this.verificationService.rejectVerification(id, adminId, reason);
            return ResponseEntity.ok((Object)rejected);
        }
        catch (Exception e) {
            log.error("Error rejecting verification", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/user/{userId}/{userType}"})
    public ResponseEntity<List<VerificationDTO>> getUserVerifications(@PathVariable Long userId, @PathVariable String userType) {
        log.info("Fetching verifications for user: {} of type: {}", (Object)userId, (Object)userType);
        try {
            List<VerificationDTO> verifications = this.verificationService.getUserVerifications(userId, userType);
            return ResponseEntity.ok(verifications);
        }
        catch (Exception e) {
            log.error("Error fetching user verifications", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/pending/all"})
    public ResponseEntity<List<VerificationDTO>> getPendingVerifications() {
        log.info("Fetching all pending verifications");
        try {
            List<VerificationDTO> verifications = this.verificationService.getPendingVerifications();
            return ResponseEntity.ok(verifications);
        }
        catch (Exception e) {
            log.error("Error fetching pending verifications", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/pending/{userType}"})
    public ResponseEntity<List<VerificationDTO>> getPendingVerificationsByUserType(@PathVariable String userType) {
        log.info("Fetching pending verifications for user type: {}", (Object)userType);
        try {
            List<VerificationDTO> verifications = this.verificationService.getPendingVerificationsByUserType(userType);
            return ResponseEntity.ok(verifications);
        }
        catch (Exception e) {
            log.error("Error fetching pending verifications", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/check/{userId}/{userType}/{verificationType}"})
    public ResponseEntity<Boolean> checkVerification(@PathVariable Long userId, @PathVariable String userType, @PathVariable Verification.VerificationType verificationType) {
        log.info("Checking verification for user: {} of type: {} - verification: {}", new Object[]{userId, userType, verificationType});
        try {
            boolean hasVerification = this.verificationService.hasRequiredVerification(userId, userType, verificationType);
            return ResponseEntity.ok((Object)hasVerification);
        }
        catch (Exception e) {
            log.error("Error checking verification", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }
}
