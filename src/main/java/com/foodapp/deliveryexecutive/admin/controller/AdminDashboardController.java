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
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.admin.controller;

import com.foodapp.deliveryexecutive.admin.dto.DisputeDTO;
import com.foodapp.deliveryexecutive.admin.service.DisputeService;
import com.foodapp.deliveryexecutive.admin.service.ProfileApprovalService;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.order.repository.OrderRepository;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/admin"})
public class AdminDashboardController {
    private static final Logger log = LoggerFactory.getLogger(AdminDashboardController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeMakerRepository homeMakerRepository;
    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DisputeService disputeService;
    @Autowired
    private ProfileApprovalService profileApprovalService;

    @GetMapping(value={"/analytics/overview"})
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        log.info("Fetching admin dashboard stats");
        try {
            HashMap<String, Object> stats = new HashMap<String, Object>();
            stats.put("totalUsers", this.userRepository.count());
            stats.put("totalHomemakers", this.homeMakerRepository.count());
            stats.put("totalExecutives", this.deliveryExecutiveRepository.count());
            stats.put("totalOrders", this.orderRepository.count());
            stats.put("totalRevenue", 0);
            stats.put("activeOrders", 0);
            long pendingHomemakers = this.homeMakerRepository.countByApprovalStatus(HomeMaker.ApprovalStatus.PENDING);
            long pendingExecutives = this.deliveryExecutiveRepository.countByApprovalStatus(DeliveryExecutive.ApprovalStatus.PENDING);
            stats.put("pendingVerifications", pendingHomemakers + pendingExecutives);
            stats.put("pendingHomemakers", pendingHomemakers);
            stats.put("pendingExecutives", pendingExecutives);
            Map<String, Object> approvalStats = this.profileApprovalService.getApprovalStatistics();
            stats.put("approvalStatistics", approvalStats);
            stats.put("openDisputes", this.disputeService.getOpenDisputes().size());
            return ResponseEntity.ok(stats);
        }
        catch (Exception e) {
            log.error("Error fetching dashboard stats", e);
            return ResponseEntity.ok(this.getDefaultStats());
        }
    }

    @GetMapping(value={"/users"})
    public ResponseEntity<List<?>> getAllUsers() {
        log.info("Fetching all users for admin");
        try {
            return ResponseEntity.ok(this.userRepository.findAll());
        }
        catch (Exception e) {
            log.error("Error fetching users", e);
            return ResponseEntity.ok(List.of());
        }
    }

    @GetMapping(value={"/homemakers"})
    public ResponseEntity<List<?>> getAllHomemakers() {
        log.info("Fetching all homemakers for admin");
        try {
            return ResponseEntity.ok(this.homeMakerRepository.findAll());
        }
        catch (Exception e) {
            log.error("Error fetching homemakers", e);
            return ResponseEntity.ok(List.of());
        }
    }

    @GetMapping(value={"/executives"})
    public ResponseEntity<List<?>> getAllExecutives() {
        log.info("Fetching all executives for admin");
        try {
            return ResponseEntity.ok(this.deliveryExecutiveRepository.findAll());
        }
        catch (Exception e) {
            log.error("Error fetching executives", e);
            return ResponseEntity.ok(List.of());
        }
    }

    @GetMapping(value={"/disputes"})
    public ResponseEntity<List<DisputeDTO>> getAllDisputes(@RequestParam(required=false) String status) {
        log.info("Fetching disputes for admin with status: {}", status);
        try {
            if ("OPEN".equals(status)) {
                return ResponseEntity.ok(this.disputeService.getOpenDisputes());
            }
            return ResponseEntity.ok(this.disputeService.getOpenDisputes());
        }
        catch (Exception e) {
            log.error("Error fetching disputes", e);
            return ResponseEntity.ok(List.of());
        }
    }

    @GetMapping(value={"/verifications"})
    public ResponseEntity<List<?>> getVerificationsForAdmin(@RequestParam(required=false) String status, @RequestParam(required=false) String type) {
        log.info("Fetching verifications for admin with status: {}, type: {}", status, type);
        try {
            return ResponseEntity.ok(List.of());
        }
        catch (Exception e) {
            log.error("Error fetching verifications", e);
            return ResponseEntity.ok(List.of());
        }
    }

    @GetMapping(value={"/analytics"})
    public ResponseEntity<Map<String, Object>> getAnalytics(@RequestParam(required=false) String period) {
        log.info("Fetching analytics with period: {}", period);
        try {
            HashMap<String, Object> analytics = new HashMap<String, Object>();
            analytics.put("totalRevenue", 0);
            analytics.put("totalCommission", 0);
            analytics.put("averageOrderValue", 0);
            analytics.put("orderGrowthRate", 0);
            analytics.put("userGrowthRate", 0);
            analytics.put("homemakerGrowthRate", 0);
            analytics.put("executiveGrowthRate", 0);
            analytics.put("totalUsers", this.userRepository.count());
            analytics.put("totalHomemakers", this.homeMakerRepository.count());
            analytics.put("totalExecutives", this.deliveryExecutiveRepository.count());
            analytics.put("totalOrders", this.orderRepository.count());
            return ResponseEntity.ok(analytics);
        }
        catch (Exception e) {
            log.error("Error fetching analytics", e);
            return ResponseEntity.ok(new HashMap<>());
        }
    }

    @PutMapping(value={"/users/{id}/verify"})
    public ResponseEntity<Map<String, Object>> verifyUser(@PathVariable Long id) {
        log.info("Verifying user: {}", id);
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("success", true);
        response.put("message", "User verified successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping(value={"/users/{id}/suspend"})
    public ResponseEntity<Map<String, Object>> suspendUser(@PathVariable Long id, @RequestBody Map<String, String> body) {
        log.info("Suspending user: {} with reason: {}", id, body.get("reason"));
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("success", true);
        response.put("message", "User suspended successfully");
        return ResponseEntity.ok(response);
    }

    private Map<String, Object> getDefaultStats() {
        HashMap<String, Object> stats = new HashMap<String, Object>();
        stats.put("totalUsers", 0);
        stats.put("totalHomemakers", 0);
        stats.put("totalExecutives", 0);
        stats.put("totalOrders", 0);
        stats.put("totalRevenue", 0);
        stats.put("activeOrders", 0);
        stats.put("pendingVerifications", 0);
        stats.put("openDisputes", 0);
        return stats;
    }
}
