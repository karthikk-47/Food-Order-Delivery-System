/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.homemaker.service;

import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerAnalyticsDTO;
import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerAnalytics;
import com.foodapp.deliveryexecutive.homemaker.repository.HomemakerAnalyticsRepository;
import java.time.LocalDateTime;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HomemakerAnalyticsService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(HomemakerAnalyticsService.class);
    @Autowired
    private HomemakerAnalyticsRepository analyticsRepository;

    public HomemakerAnalyticsDTO getAnalytics(Long homemakerId) {
        log.debug("Fetching analytics for homemaker: {}", (Object)homemakerId);
        HomemakerAnalytics analytics = this.analyticsRepository.findByHomemakerId(homemakerId).orElseGet(() -> this.createAnalytics(homemakerId));
        return this.convertToDTO(analytics);
    }

    private HomemakerAnalytics createAnalytics(Long homemakerId) {
        log.info("Creating analytics record for homemaker: {}", (Object)homemakerId);
        HomemakerAnalytics analytics = new HomemakerAnalytics();
        analytics.setHomemakerId(homemakerId);
        return (HomemakerAnalytics)this.analyticsRepository.save(analytics);
    }

    public void recordOrderCompletion(Long homemakerId, Double orderValue) {
        log.info("Recording order completion for homemaker: {} with value: {}", (Object)homemakerId, (Object)orderValue);
        HomemakerAnalytics analytics = this.analyticsRepository.findByHomemakerId(homemakerId).orElseGet(() -> this.createAnalytics(homemakerId));
        analytics.setTotalOrdersThisMonth(analytics.getTotalOrdersThisMonth() + 1);
        analytics.setTotalOrdersThisWeek(analytics.getTotalOrdersThisWeek() + 1);
        analytics.setTotalOrdersToday(analytics.getTotalOrdersToday() + 1);
        analytics.setTotalRevenueThisMonth(analytics.getTotalRevenueThisMonth() + orderValue);
        analytics.setTotalRevenueThisWeek(analytics.getTotalRevenueThisWeek() + orderValue);
        analytics.setTotalRevenueToday(analytics.getTotalRevenueToday() + orderValue);
        analytics.setOrdersCompleted(analytics.getOrdersCompleted() + 1);
        analytics.setLastUpdated(LocalDateTime.now());
        this.analyticsRepository.save(analytics);
    }

    public void recordOrderCancellation(Long homemakerId) {
        log.info("Recording order cancellation for homemaker: {}", (Object)homemakerId);
        HomemakerAnalytics analytics = this.analyticsRepository.findByHomemakerId(homemakerId).orElseGet(() -> this.createAnalytics(homemakerId));
        analytics.setOrdersCancelled(analytics.getOrdersCancelled() + 1);
        analytics.setLastUpdated(LocalDateTime.now());
        this.updateCompletionRate(analytics);
        this.analyticsRepository.save(analytics);
    }

    public void updateRatingAndReviews(Long homemakerId, Double rating, Boolean isPositive) {
        log.info("Updating rating for homemaker: {} with rating: {}", (Object)homemakerId, (Object)rating);
        HomemakerAnalytics analytics = this.analyticsRepository.findByHomemakerId(homemakerId).orElseGet(() -> this.createAnalytics(homemakerId));
        analytics.setTotalReviews(analytics.getTotalReviews() + 1);
        if (isPositive.booleanValue()) {
            analytics.setPositiveReviews(analytics.getPositiveReviews() + 1);
        } else {
            analytics.setNegativeReviews(analytics.getNegativeReviews() + 1);
        }
        int totalRatings = analytics.getPositiveReviews() + analytics.getNegativeReviews();
        Double avgRating = (double)analytics.getPositiveReviews().intValue() * 5.0 / (double)totalRatings;
        analytics.setAverageRating(avgRating);
        analytics.setLastUpdated(LocalDateTime.now());
        this.analyticsRepository.save(analytics);
    }

    private void updateCompletionRate(HomemakerAnalytics analytics) {
        int total = analytics.getOrdersCompleted() + analytics.getOrdersCancelled();
        if (total > 0) {
            double rate = (double)analytics.getOrdersCompleted().intValue() * 100.0 / (double)total;
            analytics.setCompletionRate(rate);
        }
    }

    private HomemakerAnalyticsDTO convertToDTO(HomemakerAnalytics analytics) {
        HomemakerAnalyticsDTO dto = new HomemakerAnalyticsDTO();
        dto.setId(analytics.getId());
        dto.setHomemakerId(analytics.getHomemakerId());
        dto.setTotalOrdersThisMonth(analytics.getTotalOrdersThisMonth());
        dto.setTotalOrdersThisWeek(analytics.getTotalOrdersThisWeek());
        dto.setTotalOrdersToday(analytics.getTotalOrdersToday());
        dto.setTotalRevenueThisMonth(analytics.getTotalRevenueThisMonth());
        dto.setTotalRevenueThisWeek(analytics.getTotalRevenueThisWeek());
        dto.setTotalRevenueToday(analytics.getTotalRevenueToday());
        dto.setAverageOrderValue(analytics.getAverageOrderValue());
        dto.setAverageRating(analytics.getAverageRating());
        dto.setTotalCustomers(analytics.getTotalCustomers());
        dto.setRepeatCustomers(analytics.getRepeatCustomers());
        dto.setOrdersCompleted(analytics.getOrdersCompleted());
        dto.setOrdersCancelled(analytics.getOrdersCancelled());
        dto.setOrdersRefunded(analytics.getOrdersRefunded());
        dto.setCompletionRate(analytics.getCompletionRate());
        dto.setCancellationRate(analytics.getCancellationRate());
        dto.setTotalReviews(analytics.getTotalReviews());
        dto.setPositiveReviews(analytics.getPositiveReviews());
        dto.setNegativeReviews(analytics.getNegativeReviews());
        dto.setLastUpdated(analytics.getLastUpdated());
        dto.setCreatedAt(analytics.getCreatedAt());
        return dto;
    }
}
