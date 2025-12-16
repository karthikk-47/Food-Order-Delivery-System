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
package com.foodapp.deliveryexecutive.user.controller;

import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import java.util.HashMap;
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
@RequestMapping(value={"/api/users"})
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable Long id) {
        log.info("Fetching user profile for ID: {}", (Object)id);
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            HashMap<String, Object> profile = new HashMap<String, Object>();
            profile.put("id", user.getId());
            profile.put("name", user.getName());
            profile.put("mobile", user.getMobile());
            profile.put("email", user.getEmail());
            profile.put("address", user.getAddress());
            profile.put("role", (Object)user.getRole());
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            log.error("Error fetching user profile", (Throwable)e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> updateUserProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        log.info("Updating user profile for ID: {}", (Object)id);
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            if (updates.containsKey("name")) {
                user.setName((String)updates.get("name"));
            }
            if (updates.containsKey("email")) {
                user.setEmail((String)updates.get("email"));
            }
            if (updates.containsKey("address")) {
                user.setAddress((String)updates.get("address"));
            }
            this.userRepository.save(user);
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("success", true);
            response.put("message", "Profile updated successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error updating user profile", (Throwable)e);
            return ResponseEntity.badRequest().build();
        }
    }
}
