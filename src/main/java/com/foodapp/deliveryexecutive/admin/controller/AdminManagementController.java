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
 *  org.springframework.web.bind.annotation.CrossOrigin
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.admin.controller;

import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.order.repository.OrderRepository;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/admin/manage"})
public class AdminManagementController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(AdminManagementController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeMakerRepository homeMakerRepository;
    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value={"/users"})
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(required=false) String search, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size) {
        log.info("Fetching users - search: {}, page: {}, size: {}", new Object[]{search, page, size});
        try {
            List<User> users = this.userRepository.findAll();
            if (search != null && !search.isEmpty()) {
                String searchLower = search.toLowerCase();
                users = users.stream().filter(u -> u.getName().toLowerCase().contains(searchLower) || u.getMobile().contains(searchLower) || u.getEmail().toLowerCase().contains(searchLower)).collect(Collectors.toList());
            }
            int total = users.size();
            int start = page * size;
            int end = Math.min(start + size, total);
            List<User> paginatedUsers = users.subList(start, Math.min(end, users.size()));
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("data", paginatedUsers);
            response.put("totalRecords", total);
            response.put("totalPages", (total + size - 1) / size);
            response.put("currentPage", page);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error fetching users", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch users"));
        }
    }

    @GetMapping(value={"/users/{id}"})
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        log.info("Fetching user with ID: {}", id);
        try {
            Optional user = this.userRepository.findById(id);
            if (user.isPresent()) {
                return ResponseEntity.ok(((User)user.get()));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "User not found"));
        }
        catch (Exception e) {
            log.error("Error fetching user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch user"));
        }
    }

    @DeleteMapping(value={"/users/{id}"})
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        try {
            User user = (User)this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
            this.userRepository.delete(user);
            log.info("User deleted successfully");
            return ResponseEntity.ok(Collections.singletonMap("message", "User deleted successfully"));
        }
        catch (IllegalArgumentException e) {
            log.warn("User not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
        catch (Exception e) {
            log.error("Error deleting user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to delete user"));
        }
    }

    @GetMapping(value={"/homemakers"})
    public ResponseEntity<Map<String, Object>> getAllHomemakers(@RequestParam(required=false) String status, @RequestParam(required=false) String search, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size) {
        log.info("Fetching homemakers - status: {}, search: {}", status, search);
        try {
            List<HomeMaker> homemakers = this.homeMakerRepository.findAll();
            if (status != null && !status.isEmpty()) {
                homemakers = homemakers.stream().filter(h -> h.getApprovalStatus().toString().equalsIgnoreCase(status)).collect(Collectors.toList());
            }
            if (search != null && !search.isEmpty()) {
                String searchLower = search.toLowerCase();
                homemakers = homemakers.stream().filter(h -> h.getName().toLowerCase().contains(searchLower) || h.getMobile().contains(searchLower)).collect(Collectors.toList());
            }
            int total = homemakers.size();
            int start = page * size;
            int end = Math.min(start + size, total);
            List<HomeMaker> paginatedHomemakers = homemakers.subList(start, Math.min(end, homemakers.size()));
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("data", paginatedHomemakers);
            response.put("totalRecords", total);
            response.put("totalPages", (total + size - 1) / size);
            response.put("currentPage", page);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error fetching homemakers", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch homemakers"));
        }
    }

    @GetMapping(value={"/homemakers/{id}"})
    public ResponseEntity<?> getHomemakerById(@PathVariable Long id) {
        log.info("Fetching homemaker with ID: {}", id);
        try {
            Optional homemaker = this.homeMakerRepository.findById(id);
            if (homemaker.isPresent()) {
                return ResponseEntity.ok(((HomeMaker)homemaker.get()));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Homemaker not found"));
        }
        catch (Exception e) {
            log.error("Error fetching homemaker", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch homemaker"));
        }
    }

    @PutMapping(value={"/homemakers/{id}/approval-status"})
    public ResponseEntity<?> updateHomemakerApprovalStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        log.info("Updating homemaker approval status for ID: {} to {}", id, request.get("status"));
        try {
            HomeMaker homemaker = (HomeMaker)this.homeMakerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Homemaker not found"));
            String newStatus = request.get("status");
            homemaker.setApprovalStatus(HomeMaker.ApprovalStatus.valueOf(newStatus.toUpperCase()));
            if ("REJECTED".equalsIgnoreCase(newStatus) && request.containsKey("reason")) {
                homemaker.setRejectionReason(request.get("reason"));
            }
            HomeMaker updatedHomemaker = (HomeMaker)this.homeMakerRepository.save(homemaker);
            log.info("Homemaker approval status updated successfully");
            return ResponseEntity.ok(updatedHomemaker);
        }
        catch (IllegalArgumentException e) {
            log.warn("Invalid homemaker or status: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
        catch (Exception e) {
            log.error("Error updating homemaker approval status", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to update homemaker"));
        }
    }

    @DeleteMapping(value={"/homemakers/{id}"})
    public ResponseEntity<?> deleteHomemaker(@PathVariable Long id) {
        log.info("Deleting homemaker with ID: {}", id);
        try {
            HomeMaker homemaker = (HomeMaker)this.homeMakerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Homemaker not found"));
            this.homeMakerRepository.delete(homemaker);
            log.info("Homemaker deleted successfully");
            return ResponseEntity.ok(Collections.singletonMap("message", "Homemaker deleted successfully"));
        }
        catch (Exception e) {
            log.error("Error deleting homemaker", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to delete homemaker"));
        }
    }

    @GetMapping(value={"/executives"})
    public ResponseEntity<Map<String, Object>> getAllExecutives(@RequestParam(required=false) String status, @RequestParam(required=false) String search, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size) {
        log.info("Fetching delivery executives - status: {}, search: {}", status, search);
        try {
            List<DeliveryExecutive> executives = this.deliveryExecutiveRepository.findAll();
            if (status != null && !status.isEmpty()) {
                executives = executives.stream().filter(e -> e.getApprovalStatus().toString().equalsIgnoreCase(status)).collect(Collectors.toList());
            }
            if (search != null && !search.isEmpty()) {
                String searchLower = search.toLowerCase();
                executives = executives.stream().filter(e -> e.getName().toLowerCase().contains(searchLower) || e.getMobile().contains(searchLower)).collect(Collectors.toList());
            }
            int total = executives.size();
            int start = page * size;
            int end = Math.min(start + size, total);
            List<DeliveryExecutive> paginatedExecutives = executives.subList(start, Math.min(end, executives.size()));
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("data", paginatedExecutives);
            response.put("totalRecords", total);
            response.put("totalPages", (total + size - 1) / size);
            response.put("currentPage", page);
            return ResponseEntity.ok(response);
        }
        catch (Exception e2) {
            log.error("Error fetching delivery executives", e2);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch executives"));
        }
    }

    @GetMapping(value={"/executives/{id}"})
    public ResponseEntity<?> getExecutiveById(@PathVariable Long id) {
        log.info("Fetching delivery executive with ID: {}", id);
        try {
            Optional executive = this.deliveryExecutiveRepository.findById(id);
            if (executive.isPresent()) {
                return ResponseEntity.ok(((DeliveryExecutive)executive.get()));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Executive not found"));
        }
        catch (Exception e) {
            log.error("Error fetching executive", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch executive"));
        }
    }

    @PutMapping(value={"/executives/{id}/approval-status"})
    public ResponseEntity<?> updateExecutiveApprovalStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        log.info("Updating executive approval status for ID: {} to {}", id, request.get("status"));
        try {
            DeliveryExecutive executive = (DeliveryExecutive)this.deliveryExecutiveRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Executive not found"));
            String newStatus = request.get("status");
            executive.setApprovalStatus(DeliveryExecutive.ApprovalStatus.valueOf(newStatus.toUpperCase()));
            if ("REJECTED".equalsIgnoreCase(newStatus) && request.containsKey("reason")) {
                executive.setRejectionReason(request.get("reason"));
            }
            DeliveryExecutive updatedExecutive = (DeliveryExecutive)this.deliveryExecutiveRepository.save(executive);
            log.info("Executive approval status updated successfully");
            return ResponseEntity.ok(updatedExecutive);
        }
        catch (IllegalArgumentException e) {
            log.warn("Invalid executive or status: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
        catch (Exception e) {
            log.error("Error updating executive approval status", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to update executive"));
        }
    }

    @DeleteMapping(value={"/executives/{id}"})
    public ResponseEntity<?> deleteExecutive(@PathVariable Long id) {
        log.info("Deleting delivery executive with ID: {}", id);
        try {
            DeliveryExecutive executive = (DeliveryExecutive)this.deliveryExecutiveRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Executive not found"));
            this.deliveryExecutiveRepository.delete(executive);
            log.info("Executive deleted successfully");
            return ResponseEntity.ok(Collections.singletonMap("message", "Executive deleted successfully"));
        }
        catch (Exception e) {
            log.error("Error deleting executive", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to delete executive"));
        }
    }
}
