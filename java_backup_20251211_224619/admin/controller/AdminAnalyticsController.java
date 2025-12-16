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
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.admin.controller;

import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.order.repository.OrderRepository;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/admin/analytics"})
@CrossOrigin(origins={"*"}, maxAge=3600L)
public class AdminAnalyticsController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(AdminAnalyticsController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeMakerRepository homeMakerRepository;
    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value={"/dashboard"})
    public ResponseEntity<?> getDashboardAnalytics() {
        log.info("Fetching dashboard analytics");
        try {
            HashMap analytics = new HashMap();
            long totalUsers = this.userRepository.count();
            HashMap<String, Long> userStats = new HashMap<String, Long>();
            userStats.put("total", totalUsers);
            analytics.put("users", userStats);
            long totalHomemakers = this.homeMakerRepository.count();
            long approvedHomemakers = this.homeMakerRepository.findAll().stream().filter(h -> h.getApprovalStatus() == HomeMaker.ApprovalStatus.APPROVED).count();
            long pendingHomemakers = this.homeMakerRepository.findAll().stream().filter(h -> h.getApprovalStatus() == HomeMaker.ApprovalStatus.PENDING).count();
            long rejectedHomemakers = this.homeMakerRepository.findAll().stream().filter(h -> h.getApprovalStatus() == HomeMaker.ApprovalStatus.REJECTED).count();
            HashMap<String, Long> homemakerStats = new HashMap<String, Long>();
            homemakerStats.put("total", totalHomemakers);
            homemakerStats.put("approved", approvedHomemakers);
            homemakerStats.put("pending", pendingHomemakers);
            homemakerStats.put("rejected", rejectedHomemakers);
            analytics.put("homemakers", homemakerStats);
            long totalExecutives = this.deliveryExecutiveRepository.count();
            long approvedExecutives = this.deliveryExecutiveRepository.findAll().stream().filter(e -> e.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.APPROVED).count();
            long pendingExecutives = this.deliveryExecutiveRepository.findAll().stream().filter(e -> e.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.PENDING).count();
            HashMap<String, Long> executiveStats = new HashMap<String, Long>();
            executiveStats.put("total", totalExecutives);
            executiveStats.put("approved", approvedExecutives);
            executiveStats.put("pending", pendingExecutives);
            analytics.put("executives", executiveStats);
            long totalOrders = this.orderRepository.count();
            HashMap<String, Long> orderStats = new HashMap<String, Long>();
            orderStats.put("total", totalOrders);
            analytics.put("orders", orderStats);
            HashMap<String, Long> summary = new HashMap<String, Long>();
            summary.put("totalUsers", totalUsers);
            summary.put("totalHomemakers", totalHomemakers);
            summary.put("totalExecutives", totalExecutives);
            summary.put("totalOrders", totalOrders);
            summary.put("pendingApprovals", pendingHomemakers + pendingExecutives);
            analytics.put("summary", summary);
            return ResponseEntity.ok(analytics);
        }
        catch (Exception e2) {
            log.error("Error fetching dashboard analytics", (Throwable)e2);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch analytics"));
        }
    }

    @GetMapping(value={"/users"})
    public ResponseEntity<?> getUserAnalytics() {
        log.info("Fetching user analytics");
        try {
            HashMap<String, Object> analytics = new HashMap<String, Object>();
            List allUsers = this.userRepository.findAll();
            analytics.put("totalUsers", allUsers.size());
            analytics.put("userList", allUsers);
            return ResponseEntity.ok(analytics);
        }
        catch (Exception e) {
            log.error("Error fetching user analytics", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch user analytics"));
        }
    }

    @GetMapping(value={"/homemakers"})
    public ResponseEntity<?> getHomemakerAnalytics() {
        log.info("Fetching homemaker analytics");
        try {
            HashMap<String, Object> analytics = new HashMap<String, Object>();
            List allHomemakers = this.homeMakerRepository.findAll();
            long approved = allHomemakers.stream().filter(h -> h.getApprovalStatus() == HomeMaker.ApprovalStatus.APPROVED).count();
            long pending = allHomemakers.stream().filter(h -> h.getApprovalStatus() == HomeMaker.ApprovalStatus.PENDING).count();
            long rejected = allHomemakers.stream().filter(h -> h.getApprovalStatus() == HomeMaker.ApprovalStatus.REJECTED).count();
            analytics.put("totalHomemakers", allHomemakers.size());
            analytics.put("approved", approved);
            analytics.put("pending", pending);
            analytics.put("rejected", rejected);
            analytics.put("homemakerList", allHomemakers);
            return ResponseEntity.ok(analytics);
        }
        catch (Exception e) {
            log.error("Error fetching homemaker analytics", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch homemaker analytics"));
        }
    }

    @GetMapping(value={"/executives"})
    public ResponseEntity<?> getExecutiveAnalytics() {
        log.info("Fetching delivery executive analytics");
        try {
            HashMap<String, Object> analytics = new HashMap<String, Object>();
            List allExecutives = this.deliveryExecutiveRepository.findAll();
            long approved = allExecutives.stream().filter(e -> e.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.APPROVED).count();
            long pending = allExecutives.stream().filter(e -> e.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.PENDING).count();
            analytics.put("totalExecutives", allExecutives.size());
            analytics.put("approved", approved);
            analytics.put("pending", pending);
            analytics.put("executiveList", allExecutives);
            return ResponseEntity.ok(analytics);
        }
        catch (Exception e2) {
            log.error("Error fetching executive analytics", (Throwable)e2);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch executive analytics"));
        }
    }

    @GetMapping(value={"/orders"})
    public ResponseEntity<?> getOrderAnalytics() {
        log.info("Fetching order analytics");
        try {
            HashMap<String, Long> analytics = new HashMap<String, Long>();
            long totalOrders = this.orderRepository.count();
            analytics.put("totalOrders", totalOrders);
            return ResponseEntity.ok(analytics);
        }
        catch (Exception e) {
            log.error("Error fetching order analytics", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to fetch order analytics"));
        }
    }
}
