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
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.user.controller;

import com.foodapp.deliveryexecutive.user.dto.UserPreferenceDTO;
import com.foodapp.deliveryexecutive.user.service.UserPreferenceService;
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
@RequestMapping(value={"/api/user-preference"})
public class UserPreferenceController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(UserPreferenceController.class);
    @Autowired
    private UserPreferenceService userPreferenceService;

    @GetMapping(value={"/{userId}"})
    public ResponseEntity<UserPreferenceDTO> getPreferences(@PathVariable Long userId) {
        log.info("Fetching preferences for user: {}", (Object)userId);
        try {
            UserPreferenceDTO preferences = this.userPreferenceService.getOrCreatePreferences(userId);
            return ResponseEntity.ok((Object)preferences);
        }
        catch (Exception e) {
            log.error("Error fetching preferences", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value={"/{userId}"})
    public ResponseEntity<UserPreferenceDTO> updatePreferences(@PathVariable Long userId, @RequestBody UserPreferenceDTO preferenceDTO) {
        log.info("Updating preferences for user: {}", (Object)userId);
        try {
            UserPreferenceDTO updatedPreferences = this.userPreferenceService.updatePreferences(userId, preferenceDTO);
            return ResponseEntity.ok((Object)updatedPreferences);
        }
        catch (Exception e) {
            log.error("Error updating preferences", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{userId}/add-allergy"})
    public ResponseEntity<UserPreferenceDTO> addAllergy(@PathVariable Long userId, @RequestParam String allergy) {
        log.info("Adding allergy for user: {} - {}", (Object)userId, (Object)allergy);
        try {
            UserPreferenceDTO preferences = this.userPreferenceService.addAllergy(userId, allergy);
            return ResponseEntity.ok((Object)preferences);
        }
        catch (Exception e) {
            log.error("Error adding allergy", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{userId}/remove-allergy"})
    public ResponseEntity<UserPreferenceDTO> removeAllergy(@PathVariable Long userId, @RequestParam String allergy) {
        log.info("Removing allergy for user: {} - {}", (Object)userId, (Object)allergy);
        try {
            UserPreferenceDTO preferences = this.userPreferenceService.removeAllergy(userId, allergy);
            return ResponseEntity.ok((Object)preferences);
        }
        catch (Exception e) {
            log.error("Error removing allergy", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }
}
