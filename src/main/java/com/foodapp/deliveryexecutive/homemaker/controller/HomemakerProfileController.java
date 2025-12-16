package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerProfileDTO;
import com.foodapp.deliveryexecutive.homemaker.service.HomemakerProfileService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/homemaker-profile"})
public class HomemakerProfileController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(HomemakerProfileController.class);
    @Autowired
    private HomemakerProfileService homemakerProfileService;

    @PostMapping(value={"/create"})
    public ResponseEntity<HomemakerProfileDTO> createProfile(@RequestBody HomemakerProfileDTO profileDTO) {
        log.info("Creating homemaker profile for user: {}", profileDTO.getUserId());
        try {
            HomemakerProfileDTO createdProfile = this.homemakerProfileService.createProfile(profileDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
        }
        catch (Exception e) {
            log.error("Error creating profile", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<HomemakerProfileDTO> getProfile(@PathVariable Long id) {
        log.info("Fetching profile with ID: {}", id);
        try {
            HomemakerProfileDTO profile = this.homemakerProfileService.getProfileById(id);
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            log.error("Error fetching profile", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value={"/user/{userId}"})
    public ResponseEntity<HomemakerProfileDTO> getProfileByUserId(@PathVariable Long userId) {
        log.info("Fetching profile for user: {}", userId);
        try {
            HomemakerProfileDTO profile = this.homemakerProfileService.getProfileByUserId(userId);
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            log.error("Error fetching profile for user", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<HomemakerProfileDTO> updateProfile(@PathVariable Long id, @RequestBody HomemakerProfileDTO profileDTO) {
        log.info("Updating profile with ID: {}", id);
        try {
            HomemakerProfileDTO updatedProfile = this.homemakerProfileService.updateProfile(id, profileDTO);
            return ResponseEntity.ok(updatedProfile);
        }
        catch (Exception e) {
            log.error("Error updating profile", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/verify"})
    public ResponseEntity<HomemakerProfileDTO> verifyHomemaker(@PathVariable Long id) {
        log.info("Verifying homemaker with ID: {}", id);
        try {
            HomemakerProfileDTO verifiedProfile = this.homemakerProfileService.verifyHomemaker(id);
            return ResponseEntity.ok(verifiedProfile);
        }
        catch (Exception e) {
            log.error("Error verifying homemaker", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/suspend"})
    public ResponseEntity<HomemakerProfileDTO> suspendAccount(@PathVariable Long id) {
        log.info("Suspending account for homemaker: {}", id);
        try {
            HomemakerProfileDTO suspendedProfile = this.homemakerProfileService.suspendAccount(id);
            return ResponseEntity.ok(suspendedProfile);
        }
        catch (Exception e) {
            log.error("Error suspending account", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/wallet/update"})
    public ResponseEntity<Void> updateWallet(@PathVariable Long id, @RequestParam Double amount) {
        log.info("Updating wallet for homemaker: {} with amount: {}", id, amount);
        try {
            this.homemakerProfileService.updateWalletBalance(id, amount);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            log.error("Error updating wallet", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
