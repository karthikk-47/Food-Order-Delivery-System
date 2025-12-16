package com.foodapp.deliveryexecutive.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardAnalyticsDTO {
    private UserStatsDTO users;
    private HomemakerStatsDTO homemakers;
    private ExecutiveStatsDTO executives;
    private OrderStatsDTO orders;
    private SummaryDTO summary;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserStatsDTO {
        private long total;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomemakerStatsDTO {
        private long total;
        private long approved;
        private long pending;
        private long rejected;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExecutiveStatsDTO {
        private long total;
        private long approved;
        private long pending;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderStatsDTO {
        private long total;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryDTO {
        private long totalUsers;
        private long totalHomemakers;
        private long totalExecutives;
        private long totalOrders;
        private long pendingApprovals;
    }
}
