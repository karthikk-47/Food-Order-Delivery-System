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
package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerProfileDTO;
import com.foodapp.deliveryexecutive.homemaker.service.HomemakerProfileService;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
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
        log.info("Creating homemaker profile for user: {}", (Object)profileDTO.getUserId());
        try {
            HomemakerProfileDTO createdProfile = this.homemakerProfileService.createProfile(profileDTO);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body((Object)createdProfile);
        }
        catch (Exception e) {
            log.error("Error creating profile", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<HomemakerProfileDTO> getProfile(@PathVariable Long id) {
        log.info("Fetching profile with ID: {}", (Object)id);
        try {
            HomemakerProfileDTO profile = this.homemakerProfileService.getProfileById(id);
            return ResponseEntity.ok((Object)profile);
        }
        catch (Exception e) {
            log.error("Error fetching profile", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value={"/user/{userId}"})
    public ResponseEntity<HomemakerProfileDTO> getProfileByUserId(@PathVariable Long userId) {
        log.info("Fetching profile for user: {}", (Object)userId);
        try {
            HomemakerProfileDTO profile = this.homemakerProfileService.getProfileByUserId(userId);
            return ResponseEntity.ok((Object)profile);
        }
        catch (Exception e) {
            log.error("Error fetching profile for user", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<HomemakerProfileDTO> updateProfile(@PathVariable Long id, @RequestBody HomemakerProfileDTO profileDTO) {
        log.info("Updating profile with ID: {}", (Object)id);
        try {
            HomemakerProfileDTO updatedProfile = this.homemakerProfileService.updateProfile(id, profileDTO);
            return ResponseEntity.ok((Object)updatedProfile);
        }
        catch (Exception e) {
            log.error("Error updating profile", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/verify"})
    public ResponseEntity<HomemakerProfileDTO> verifyHomemaker(@PathVariable Long id) {
        log.info("Verifying homemaker with ID: {}", (Object)id);
        try {
            HomemakerProfileDTO verifiedProfile = this.homemakerProfileService.verifyHomemaker(id);
            return ResponseEntity.ok((Object)verifiedProfile);
        }
        catch (Exception e) {
            log.error("Error verifying homemaker", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/suspend"})
    public ResponseEntity<HomemakerProfileDTO> suspendAccount(@PathVariable Long id) {
        log.info("Suspending account for homemaker: {}", (Object)id);
        try {
            HomemakerProfileDTO suspendedProfile = this.homemakerProfileService.suspendAccount(id);
            return ResponseEntity.ok((Object)suspendedProfile);
        }
        catch (Exception e) {
            log.error("Error suspending account", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/wallet/update"})
    public ResponseEntity<Void> updateWallet(@PathVariable Long id, @RequestParam Double amount) {
        log.info("Updating wallet for homemaker: {} with amount: {}", (Object)id, (Object)amount);
        try {
            this.homemakerProfileService.updateWalletBalance(id, amount);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            log.error("Error updating wallet", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}/location"})
    public ResponseEntity<Map<String, Object>> getHomemakerLocation(@PathVariable Long id) {
        log.info("Fetching location for homemaker ID: {}", (Object)id);
        try {
            HomemakerProfileDTO profile = this.homemakerProfileService.getProfileById(id);
            if (profile == null) {
                return ResponseEntity.notFound().build();
            }
            HashMap<String, Object> locationData = new HashMap<String, Object>();
            if (profile.getLocation() != null) {
                locationData.put("latitude", profile.getLocation().getX());
                locationData.put("longitude", profile.getLocation().getY());
                locationData.put("address", profile.getAddress());
            } else {
                locationData.put("latitude", null);
                locationData.put("longitude", null);
                locationData.put("address", profile.getAddress());
            }
            return ResponseEntity.ok(locationData);
        }
        catch (Exception e) {
            log.error("Error fetching homemaker location", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/location"})
    public ResponseEntity<Map<String, Object>> updateHomemakerLocation(@PathVariable Long id, @RequestBody Map<String, Object> locationData) {
        log.info("Updating location for homemaker ID: {}", (Object)id);
        try {
            if (locationData.containsKey("latitude") && locationData.containsKey("longitude")) {
                double latitude = Double.parseDouble(locationData.get("latitude").toString());
                double longitude = Double.parseDouble(locationData.get("longitude").toString());
                Point location = new Point(latitude, longitude);

                HomemakerProfileDTO profileDTO = new HomemakerProfileDTO();
                profileDTO.setLocation(location);

                if (locationData.containsKey("address")) {
                    profileDTO.setAddress((String)locationData.get("address"));
                }

                HomemakerProfileDTO updatedProfile = this.homemakerProfileService.updateProfile(id, profileDTO);

                HashMap<String, Object> response = new HashMap<String, Object>();
                response.put("success", true);
                response.put("message", "Location updated successfully");
                return ResponseEntity.ok(response);
            } else {
                HashMap<String, Object> response = new HashMap<String, Object>();
                response.put("success", false);
                response.put("message", "Latitude and longitude are required");
                return ResponseEntity.badRequest().body(response);
            }
        }
        catch (Exception e) {
            log.error("Error updating homemaker location", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }
}
