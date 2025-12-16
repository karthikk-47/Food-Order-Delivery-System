/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/homemakers"})
public class HomemakerController {
    private static final Logger log = LoggerFactory.getLogger(HomemakerController.class);
    @Autowired
    private HomeMakerRepository homeMakerRepository;

    @GetMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> getHomemakerProfile(@PathVariable Long id) {
        log.info("Fetching homemaker profile for ID: {}", (Object)id);
        try {
            HomeMaker homeMaker = this.homeMakerRepository.findById(id).orElse(null);
            if (homeMaker == null) {
                return ResponseEntity.notFound().build();
            }
            HashMap<String, Object> profile = new HashMap<String, Object>();
            profile.put("id", homeMaker.getId());
            profile.put("name", homeMaker.getName());
            profile.put("mobile", homeMaker.getMobile());
            profile.put("address", homeMaker.getAddress());
            profile.put("approvalStatus", (Object)homeMaker.getApprovalStatus());
            profile.put("role", (Object)homeMaker.getRole());
            profile.put("totalOrders", 0);
            profile.put("totalEarnings", 0);
            profile.put("rating", 4.5);
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            log.error("Error fetching homemaker profile", (Throwable)e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> updateHomemakerProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        log.info("Updating homemaker profile for ID: {}", (Object)id);
        try {
            HomeMaker homeMaker = this.homeMakerRepository.findById(id).orElse(null);
            if (homeMaker == null) {
                return ResponseEntity.notFound().build();
            }
            if (updates.containsKey("name")) {
                homeMaker.setName((String)updates.get("name"));
            }
            if (updates.containsKey("address")) {
                homeMaker.setAddress((String)updates.get("address"));
            }
            this.homeMakerRepository.save(homeMaker);
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("success", true);
            response.put("message", "Profile updated successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error updating homemaker profile", (Throwable)e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<HomeMaker>> getAllHomemakers() {
        log.info("Fetching all homemakers");
        try {
            List homemakers = this.homeMakerRepository.findAll();
            return ResponseEntity.ok((Object)homemakers);
        }
        catch (Exception e) {
            log.error("Error fetching homemakers", (Throwable)e);
            return ResponseEntity.ok(List.of());
        }
    }

    @GetMapping(value={"/nearby"})
    public ResponseEntity<List<HomeMaker>> getNearbyHomemakers() {
        log.info("Fetching nearby homemakers");
        try {
            List<HomeMaker> homemakers = this.homeMakerRepository.findAll().stream().filter(h -> h.getApprovalStatus() == HomeMaker.ApprovalStatus.APPROVED).toList();
            return ResponseEntity.ok(homemakers);
        }
        catch (Exception e) {
            log.error("Error fetching nearby homemakers", (Throwable)e);
            return ResponseEntity.ok(List.of());
        }
    }
}
