package com.foodapp.deliveryexecutive.order.service;

import com.foodapp.deliveryexecutive.order.dto.OrderSummaryDTO;
import com.foodapp.deliveryexecutive.order.entity.Order;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderSortingService {
    private static final Logger logger = LoggerFactory.getLogger(OrderSortingService.class);
    private static final double COMMISSION_WEIGHT = 0.35;
    private static final double DISTANCE_WEIGHT = 0.25;
    private static final double PRIORITY_WEIGHT = 0.2;
    private static final double SURGE_WEIGHT = 0.1;
    private static final double TIME_WEIGHT = 0.1;

    public List<OrderSummaryDTO> sortOrdersByOptimalParameters(List<Order> orders, Map<Long, Integer> distances) {
        return orders.stream().map(order -> {
            Integer distance = (Integer)distances.get(order.getId());
            if (distance == null) {
                return null;
            }
            OrderSummaryDTO dto = this.mapToOrderSummaryDTO((Order)order, distance);
            double score = this.calculateOrderScore((Order)order, distance);
            dto.setOptimalScore(score);
            return dto;
        }).filter(dto -> dto != null).sorted(Comparator.comparingDouble(OrderSummaryDTO::getOptimalScore).reversed()).collect(Collectors.toList());
    }

    private double calculateOrderScore(Order order, int distance) {
        double commissionScore = this.calculateCommissionScore(order);
        double distanceScore = this.calculateDistanceScore(distance);
        double priorityScore = this.calculatePriorityScore(order);
        double surgeScore = this.calculateSurgeScore(order);
        double timeScore = this.calculateTimeScore(order);
        double totalScore = commissionScore * 0.35 + distanceScore * 0.25 + priorityScore * 0.2 + surgeScore * 0.1 + timeScore * 0.1;
        logger.debug("Order {} score breakdown: commission={}, distance={}, priority={}, surge={}, time={}, total={}", new Object[]{order.getId(), commissionScore, distanceScore, priorityScore, surgeScore, timeScore, totalScore});
        return totalScore;
    }

    private double calculateCommissionScore(Order order) {
        double maxEarning = 500.0;
        double earning = order.getExecutiveEarning();
        return Math.min(earning / maxEarning * 100.0, 100.0);
    }

    private double calculateDistanceScore(int distance) {
        double maxDistance = 5000.0;
        double normalizedDistance = Math.min((double)distance / maxDistance, 1.0);
        return (1.0 - normalizedDistance) * 100.0;
    }

    private double calculatePriorityScore(Order order) {
        if (order.getPriority() == null) {
            return 50.0;
        }
        switch (order.getPriority()) {
            case HIGH: {
                return 100.0;
            }
            case MEDIUM: {
                return 50.0;
            }
            case LOW: {
                return 25.0;
            }
        }
        return 50.0;
    }

    private double calculateSurgeScore(Order order) {
        if (order.isSurgePricing() && order.getSurgeMultiplier() > 1.0) {
            return Math.min((order.getSurgeMultiplier() - 1.0) * 100.0, 100.0);
        }
        return 0.0;
    }

    private double calculateTimeScore(Order order) {
        if (order.getEstimatedDeliveryTime() == null) {
            return 50.0;
        }
        double maxTime = 60.0;
        double normalizedTime = Math.min((double)order.getEstimatedDeliveryTime().intValue() / maxTime, 1.0);
        return (1.0 - normalizedTime) * 100.0;
    }

    public List<OrderSummaryDTO> sortByCommission(List<Order> orders, Map<Long, Integer> distances) {
        return orders.stream().map(order -> {
            Integer distance = (Integer)distances.get(order.getId());
            if (distance == null) {
                return null;
            }
            return this.mapToOrderSummaryDTO((Order)order, distance);
        }).filter(dto -> dto != null).sorted(Comparator.comparingDouble(OrderSummaryDTO::getExecutiveEarning).reversed()).collect(Collectors.toList());
    }

    public List<OrderSummaryDTO> sortByDistance(List<Order> orders, Map<Long, Integer> distances) {
        return orders.stream().map(order -> {
            Integer distance = (Integer)distances.get(order.getId());
            if (distance == null) {
                return null;
            }
            return this.mapToOrderSummaryDTO((Order)order, distance);
        }).filter(dto -> dto != null).sorted(Comparator.comparingInt(OrderSummaryDTO::getDistance)).collect(Collectors.toList());
    }

    public List<OrderSummaryDTO> sortByPriority(List<Order> orders, Map<Long, Integer> distances) {
        return orders.stream().map(order -> {
            Integer distance = (Integer)distances.get(order.getId());
            if (distance == null) {
                return null;
            }
            return this.mapToOrderSummaryDTO((Order)order, distance);
        }).filter(dto -> dto != null).sorted(Comparator.comparing(OrderSummaryDTO::getPriority, Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
    }

    private OrderSummaryDTO mapToOrderSummaryDTO(Order order, int distance) {
        OrderSummaryDTO dto = new OrderSummaryDTO();
        dto.setOrderId(order.getId());
        dto.setAmount(order.getAmount());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setDistance(distance);
        dto.setPickupLocation(order.getPickupLocation());
        dto.setDeliveryLocation(order.getDeliveryLocation());
        dto.setPayment(order.getPayment());
        dto.setDeliveryFee(order.getDeliveryFee());
        dto.setCommissionRate(order.getCommissionRate());
        dto.setExecutiveEarning(order.getExecutiveEarning());
        dto.setPriority(order.getPriority());
        dto.setSurgePricing(order.isSurgePricing());
        dto.setSurgeMultiplier(order.getSurgeMultiplier());
        dto.setEstimatedDeliveryTime(order.getEstimatedDeliveryTime());
        dto.setPaymentMethod(order.getPaymentMethod());
        return dto;
    }
}
