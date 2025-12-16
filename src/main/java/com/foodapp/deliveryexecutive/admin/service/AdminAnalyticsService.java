package com.foodapp.deliveryexecutive.admin.service;

import com.foodapp.deliveryexecutive.admin.dto.*;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.order.repository.OrderRepository;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminAnalyticsService {

    private final UserRepository userRepository;
    private final HomeMakerRepository homeMakerRepository;
    private final DeliveryExecutiveRepository deliveryExecutiveRepository;
    private final OrderRepository orderRepository;

    public DashboardAnalyticsDTO getDashboardAnalytics() {
        log.debug("Fetching dashboard analytics");

        // User stats
        long totalUsers = userRepository.count();
        DashboardAnalyticsDTO.UserStatsDTO userStats = DashboardAnalyticsDTO.UserStatsDTO.builder()
                .total(totalUsers)
                .build();

        // Homemaker stats
        List<HomeMaker> allHomemakers = homeMakerRepository.findAll();
        long approvedHomemakers = countHomemakersByStatus(allHomemakers, HomeMaker.ApprovalStatus.APPROVED);
        long pendingHomemakers = countHomemakersByStatus(allHomemakers, HomeMaker.ApprovalStatus.PENDING);
        long rejectedHomemakers = countHomemakersByStatus(allHomemakers, HomeMaker.ApprovalStatus.REJECTED);
        
        DashboardAnalyticsDTO.HomemakerStatsDTO homemakerStats = DashboardAnalyticsDTO.HomemakerStatsDTO.builder()
                .total(allHomemakers.size())
                .approved(approvedHomemakers)
                .pending(pendingHomemakers)
                .rejected(rejectedHomemakers)
                .build();

        // Executive stats
        List<DeliveryExecutive> allExecutives = deliveryExecutiveRepository.findAll();
        long approvedExecutives = countExecutivesByStatus(allExecutives, "APPROVED");
        long pendingExecutives = countExecutivesByStatus(allExecutives, "PENDING");
        
        DashboardAnalyticsDTO.ExecutiveStatsDTO executiveStats = DashboardAnalyticsDTO.ExecutiveStatsDTO.builder()
                .total(allExecutives.size())
                .approved(approvedExecutives)
                .pending(pendingExecutives)
                .build();

        // Order stats
        long totalOrders = orderRepository.count();
        DashboardAnalyticsDTO.OrderStatsDTO orderStats = DashboardAnalyticsDTO.OrderStatsDTO.builder()
                .total(totalOrders)
                .build();

        // Summary
        DashboardAnalyticsDTO.SummaryDTO summary = DashboardAnalyticsDTO.SummaryDTO.builder()
                .totalUsers(totalUsers)
                .totalHomemakers(allHomemakers.size())
                .totalExecutives(allExecutives.size())
                .totalOrders(totalOrders)
                .pendingApprovals(pendingHomemakers + pendingExecutives)
                .build();

        return DashboardAnalyticsDTO.builder()
                .users(userStats)
                .homemakers(homemakerStats)
                .executives(executiveStats)
                .orders(orderStats)
                .summary(summary)
                .build();
    }

    public UserAnalyticsDTO getUserAnalytics() {
        log.debug("Fetching user analytics");
        List<User> allUsers = userRepository.findAll();
        
        return UserAnalyticsDTO.builder()
                .totalUsers(allUsers.size())
                .userList(allUsers)
                .build();
    }

    public HomemakerAnalyticsDTO getHomemakerAnalytics() {
        log.debug("Fetching homemaker analytics");
        List<HomeMaker> allHomemakers = homeMakerRepository.findAll();
        
        return HomemakerAnalyticsDTO.builder()
                .totalHomemakers(allHomemakers.size())
                .approved(countHomemakersByStatus(allHomemakers, HomeMaker.ApprovalStatus.APPROVED))
                .pending(countHomemakersByStatus(allHomemakers, HomeMaker.ApprovalStatus.PENDING))
                .rejected(countHomemakersByStatus(allHomemakers, HomeMaker.ApprovalStatus.REJECTED))
                .homemakerList(allHomemakers)
                .build();
    }

    public ExecutiveAnalyticsDTO getExecutiveAnalytics() {
        log.debug("Fetching executive analytics");
        List<DeliveryExecutive> allExecutives = deliveryExecutiveRepository.findAll();
        
        return ExecutiveAnalyticsDTO.builder()
                .totalExecutives(allExecutives.size())
                .approved(countExecutivesByStatus(allExecutives, "APPROVED"))
                .pending(countExecutivesByStatus(allExecutives, "PENDING"))
                .executiveList(allExecutives)
                .build();
    }

    public OrderAnalyticsDTO getOrderAnalytics() {
        log.debug("Fetching order analytics");
        return OrderAnalyticsDTO.builder()
                .totalOrders(orderRepository.count())
                .build();
    }

    private long countHomemakersByStatus(List<HomeMaker> homemakers, HomeMaker.ApprovalStatus status) {
        return homemakers.stream()
                .filter(h -> h.getApprovalStatus() == status)
                .count();
    }

    private long countExecutivesByStatus(List<DeliveryExecutive> executives, String status) {
        return executives.stream()
                .filter(e -> e.getApprovalStatus() != null && e.getApprovalStatus().name().equals(status))
                .count();
    }
}
