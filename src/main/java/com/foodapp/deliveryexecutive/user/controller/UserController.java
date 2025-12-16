package com.foodapp.deliveryexecutive.user.controller;

import com.foodapp.deliveryexecutive.admin.entity.ActivityLog;
import com.foodapp.deliveryexecutive.admin.service.ActivityLogService;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
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
    
    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable Long id) {
        log.info("Fetching user profile for ID: {}", id);
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
            profile.put("role", user.getRole());
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            log.error("Error fetching user profile", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> updateUserProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        log.info("Updating user profile for ID: {}", id);
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Track changes for activity logging
            List<String> changedFields = new ArrayList<>();
            String oldAddress = user.getAddress();
            
            if (updates.containsKey("name")) {
                String oldName = user.getName();
                String newName = (String)updates.get("name");
                if (oldName == null || !oldName.equals(newName)) {
                    changedFields.add("name");
                }
                user.setName(newName);
            }
            if (updates.containsKey("email")) {
                String oldEmail = user.getEmail();
                String newEmail = (String)updates.get("email");
                if (oldEmail == null || !oldEmail.equals(newEmail)) {
                    changedFields.add("email");
                }
                user.setEmail(newEmail);
            }
            if (updates.containsKey("address")) {
                String newAddress = (String)updates.get("address");
                if (oldAddress == null || !oldAddress.equals(newAddress)) {
                    changedFields.add("address");
                }
                user.setAddress(newAddress);
            }
            this.userRepository.save(user);
            
            // Log the profile update activity
            if (!changedFields.isEmpty()) {
                String changeDetails = String.join(", ", changedFields);
                activityLogService.log(
                    ActivityLog.LogLevel.INFO,
                    ActivityLog.LogCategory.USER,
                    "PROFILE_UPDATE",
                    "User updated profile fields: " + changeDetails,
                    id,
                    "USER",
                    user.getMobile(),
                    null,
                    "User",
                    id,
                    "Changed fields: " + changeDetails
                );
            }
            
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("success", true);
            response.put("message", "Profile updated successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error updating user profile", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
