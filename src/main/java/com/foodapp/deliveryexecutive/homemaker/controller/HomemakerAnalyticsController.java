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
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerAnalyticsDTO;
import com.foodapp.deliveryexecutive.homemaker.service.HomemakerAnalyticsService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/homemaker-analytics"})
public class HomemakerAnalyticsController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(HomemakerAnalyticsController.class);
    @Autowired
    private HomemakerAnalyticsService analyticsService;

    @GetMapping(value={"/{homemakerId}"})
    public ResponseEntity<HomemakerAnalyticsDTO> getAnalytics(@PathVariable Long homemakerId) {
        log.info("Fetching analytics for homemaker: {}", homemakerId);
        try {
            HomemakerAnalyticsDTO analytics = this.analyticsService.getAnalytics(homemakerId);
            return ResponseEntity.ok(analytics);
        }
        catch (Exception e) {
            log.error("Error fetching analytics", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value={"/{homemakerId}/order-completed"})
    public ResponseEntity<Void> recordOrderCompletion(@PathVariable Long homemakerId, @RequestParam Double orderValue) {
        log.info("Recording order completion for homemaker: {}", homemakerId);
        try {
            this.analyticsService.recordOrderCompletion(homemakerId, orderValue);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            log.error("Error recording order completion", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{homemakerId}/order-cancelled"})
    public ResponseEntity<Void> recordOrderCancellation(@PathVariable Long homemakerId) {
        log.info("Recording order cancellation for homemaker: {}", homemakerId);
        try {
            this.analyticsService.recordOrderCancellation(homemakerId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            log.error("Error recording order cancellation", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{homemakerId}/update-rating"})
    public ResponseEntity<Void> updateRatingAndReviews(@PathVariable Long homemakerId, @RequestParam Double rating, @RequestParam Boolean isPositive) {
        log.info("Updating rating for homemaker: {} with rating: {}", homemakerId, rating);
        try {
            this.analyticsService.updateRatingAndReviews(homemakerId, rating, isPositive);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            log.error("Error updating rating", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
