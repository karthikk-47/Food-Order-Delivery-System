package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.admin.entity.ActivityLog;
import com.foodapp.deliveryexecutive.admin.service.ActivityLogService;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.entity.Menu;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.homemaker.repository.MenuRepository;
import com.foodapp.deliveryexecutive.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value={"/api/homemakers"})
public class HomemakerController {
    private static final Logger log = LoggerFactory.getLogger(HomemakerController.class);
    @Autowired
    private HomeMakerRepository homeMakerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired(required = false)
    private MenuRepository menuRepository;
    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> getHomemakerProfile(@PathVariable Long id) {
        log.info("Fetching homemaker profile for ID: {}", id);
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
            profile.put("approvalStatus", homeMaker.getApprovalStatus());
            profile.put("role", homeMaker.getRole());
            profile.put("totalOrders", 0);
            profile.put("totalEarnings", 0);
            profile.put("rating", 4.5);
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            log.error("Error fetching homemaker profile", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> updateHomemakerProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        log.info("Updating homemaker profile for ID: {}", id);
        try {
            HomeMaker homeMaker = this.homeMakerRepository.findById(id).orElse(null);
            if (homeMaker == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Track changes for activity logging
            List<String> changedFields = new ArrayList<>();
            
            if (updates.containsKey("name")) {
                String oldName = homeMaker.getName();
                String newName = (String)updates.get("name");
                if (oldName == null || !oldName.equals(newName)) {
                    changedFields.add("name");
                }
                homeMaker.setName(newName);
            }
            if (updates.containsKey("address")) {
                String oldAddress = homeMaker.getAddress();
                String newAddress = (String)updates.get("address");
                if (oldAddress == null || !oldAddress.equals(newAddress)) {
                    changedFields.add("address");
                }
                homeMaker.setAddress(newAddress);
            }
            this.homeMakerRepository.save(homeMaker);
            
            // Log the profile update activity
            if (!changedFields.isEmpty()) {
                String changeDetails = String.join(", ", changedFields);
                activityLogService.log(
                    ActivityLog.LogLevel.INFO,
                    ActivityLog.LogCategory.HOMEMAKER,
                    "PROFILE_UPDATE",
                    "Homemaker updated profile fields: " + changeDetails,
                    id,
                    "HOMEMAKER",
                    homeMaker.getMobile(),
                    null,
                    "HomeMaker",
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
            log.error("Error updating homemaker profile", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<HomeMaker>> getAllHomemakers() {
        log.info("Fetching all homemakers");
        try {
            List homemakers = this.homeMakerRepository.findAll();
            return ResponseEntity.ok(homemakers);
        }
        catch (Exception e) {
            log.error("Error fetching homemakers", e);
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
            log.error("Error fetching nearby homemakers", e);
            return ResponseEntity.ok(List.of());
        }
    }

    // Global homemaker stats endpoint
    @GetMapping(value={"/stats"})
    public ResponseEntity<Map<String, Object>> getGlobalStats() {
        log.info("Fetching global homemaker stats");
        try {
            Map<String, Object> stats = new HashMap<>();
            long totalHomemakers = homeMakerRepository.count();
            long approvedHomemakers = homeMakerRepository.countByApprovalStatus(HomeMaker.ApprovalStatus.APPROVED);
            long totalOrders = orderRepository.count();
            
            stats.put("totalHomemakers", totalHomemakers);
            stats.put("approvedHomemakers", approvedHomemakers);
            stats.put("totalOrders", totalOrders);
            stats.put("averageRating", 4.5);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            log.error("Error fetching global stats", e);
            return ResponseEntity.ok(Map.of());
        }
    }

    // Individual homemaker stats endpoint
    @GetMapping(value={"/{id}/stats"})
    public ResponseEntity<Map<String, Object>> getHomemakerStats(@PathVariable Long id) {
        log.info("Fetching stats for homemaker ID: {}", id);
        try {
            HomeMaker homeMaker = homeMakerRepository.findById(id).orElse(null);
            if (homeMaker == null) {
                return ResponseEntity.notFound().build();
            }
            
            long totalOrders = orderRepository.countByHomeMakerId(id);
            long completedOrders = orderRepository.countByHomeMakerIdAndOrderStatus(id, "DELIVERED");
            double totalEarnings = orderRepository.sumAmountByHomeMakerIdAndOrderStatus(id, "DELIVERED");
            long pendingOrders = totalOrders - completedOrders - orderRepository.countByHomeMakerIdAndOrderStatus(id, "CANCELLED");
            
            Map<String, Object> stats = new HashMap<>();
            stats.put("totalOrders", totalOrders);
            stats.put("completedOrders", completedOrders);
            stats.put("pendingOrders", Math.max(0, pendingOrders));
            stats.put("totalEarnings", totalEarnings);
            stats.put("averageRating", 4.5);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            log.error("Error fetching homemaker stats", e);
            return ResponseEntity.ok(Map.of());
        }
    }

    // Get homemaker menus
    @GetMapping(value={"/{id}/menus"})
    public ResponseEntity<?> getHomemakerMenus(@PathVariable Long id) {
        log.info("Fetching menus for homemaker ID: {}", id);
        try {
            if (menuRepository == null) {
                return ResponseEntity.ok(List.of());
            }
            List<Menu> menus = menuRepository.findByHomemakerId(id);
            return ResponseEntity.ok(menus);
        } catch (Exception e) {
            log.error("Error fetching homemaker menus", e);
            return ResponseEntity.ok(List.of());
        }
    }

    // Get homemaker active menu (single)
    @GetMapping(value={"/{id}/menu"})
    public ResponseEntity<?> getHomemakerMenu(@PathVariable Long id) {
        log.info("Fetching active menu for homemaker ID: {}", id);
        try {
            if (menuRepository == null) {
                return ResponseEntity.ok(Map.of());
            }
            List<Menu> menus = menuRepository.findByHomemakerIdAndStatus(id, Menu.MenuStatus.ACTIVE);
            if (menus.isEmpty()) {
                return ResponseEntity.ok(Map.of());
            }
            return ResponseEntity.ok(menus.get(0));
        } catch (Exception e) {
            log.error("Error fetching homemaker menu", e);
            return ResponseEntity.ok(Map.of());
        }
    }
}
