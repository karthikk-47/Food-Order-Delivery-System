package com.foodapp.deliveryexecutive.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.deliveryexecutive.admin.dto.DashboardAnalyticsDTO;
import com.foodapp.deliveryexecutive.admin.dto.ExecutiveAnalyticsDTO;
import com.foodapp.deliveryexecutive.admin.dto.HomemakerAnalyticsDTO;
import com.foodapp.deliveryexecutive.admin.dto.OrderAnalyticsDTO;
import com.foodapp.deliveryexecutive.admin.dto.UserAnalyticsDTO;
import com.foodapp.deliveryexecutive.admin.service.AdminAnalyticsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admin/analytics")
@Slf4j
@RequiredArgsConstructor
public class AdminAnalyticsController {

    private final AdminAnalyticsService analyticsService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardAnalyticsDTO> getDashboardAnalytics() {
        log.info("Fetching dashboard analytics");
        DashboardAnalyticsDTO analytics = analyticsService.getDashboardAnalytics();
        return ResponseEntity.ok(analytics);
    }

    @GetMapping("/users")
    public ResponseEntity<UserAnalyticsDTO> getUserAnalytics() {
        log.info("Fetching user analytics");
        UserAnalyticsDTO analytics = analyticsService.getUserAnalytics();
        return ResponseEntity.ok(analytics);
    }

    @GetMapping("/homemakers")
    public ResponseEntity<HomemakerAnalyticsDTO> getHomemakerAnalytics() {
        log.info("Fetching homemaker analytics");
        HomemakerAnalyticsDTO analytics = analyticsService.getHomemakerAnalytics();
        return ResponseEntity.ok(analytics);
    }

    @GetMapping("/executives")
    public ResponseEntity<ExecutiveAnalyticsDTO> getExecutiveAnalytics() {
        log.info("Fetching delivery executive analytics");
        ExecutiveAnalyticsDTO analytics = analyticsService.getExecutiveAnalytics();
        return ResponseEntity.ok(analytics);
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderAnalyticsDTO> getOrderAnalytics() {
        log.info("Fetching order analytics");
        OrderAnalyticsDTO analytics = analyticsService.getOrderAnalytics();
        return ResponseEntity.ok(analytics);
    }
}
