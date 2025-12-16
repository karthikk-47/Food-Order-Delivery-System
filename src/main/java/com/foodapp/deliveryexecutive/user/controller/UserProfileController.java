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
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.user.controller;

import com.foodapp.deliveryexecutive.user.dto.UserProfileDTO;
import com.foodapp.deliveryexecutive.user.service.UserProfileService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/user-profile"})
public class UserProfileController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(UserProfileController.class);
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping(value={"/create"})
    public ResponseEntity<UserProfileDTO> createProfile(@RequestBody UserProfileDTO profileDTO) {
        log.info("Creating user profile for user: {}", profileDTO.getUserId());
        try {
            UserProfileDTO createdProfile = this.userProfileService.createProfile(profileDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
        }
        catch (Exception e) {
            log.error("Error creating profile", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Long id) {
        log.info("Fetching profile with ID: {}", id);
        try {
            UserProfileDTO profile = this.userProfileService.getProfileById(id);
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            log.error("Error fetching profile", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value={"/user/{userId}"})
    public ResponseEntity<UserProfileDTO> getProfileByUserId(@PathVariable Long userId) {
        log.info("Fetching profile for user: {}", userId);
        try {
            UserProfileDTO profile = this.userProfileService.getProfileByUserId(userId);
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            log.error("Error fetching profile for user", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable Long id, @RequestBody UserProfileDTO profileDTO) {
        log.info("Updating profile with ID: {}", id);
        try {
            UserProfileDTO updatedProfile = this.userProfileService.updateProfile(id, profileDTO);
            return ResponseEntity.ok(updatedProfile);
        }
        catch (Exception e) {
            log.error("Error updating profile", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/verify-email"})
    public ResponseEntity<UserProfileDTO> verifyEmail(@PathVariable Long id) {
        log.info("Verifying email for profile: {}", id);
        try {
            UserProfileDTO verifiedProfile = this.userProfileService.verifyEmail(id);
            return ResponseEntity.ok(verifiedProfile);
        }
        catch (Exception e) {
            log.error("Error verifying email", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/verify-phone"})
    public ResponseEntity<UserProfileDTO> verifyPhone(@PathVariable Long id) {
        log.info("Verifying phone for profile: {}", id);
        try {
            UserProfileDTO verifiedProfile = this.userProfileService.verifyPhone(id);
            return ResponseEntity.ok(verifiedProfile);
        }
        catch (Exception e) {
            log.error("Error verifying phone", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/active/all"})
    public ResponseEntity<List<UserProfileDTO>> getAllActiveUsers() {
        log.info("Fetching all active users");
        try {
            List<UserProfileDTO> users = this.userProfileService.getAllActiveUsers();
            return ResponseEntity.ok(users);
        }
        catch (Exception e) {
            log.error("Error fetching active users", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
