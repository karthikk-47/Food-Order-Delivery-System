/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.messaging.simp.SimpMessagingTemplate
 *  org.springframework.stereotype.Service
 */
package com.foodapp.deliveryexecutive.order.service;

import com.foodapp.deliveryexecutive.order.dto.LocationUpdateDTO;
import com.foodapp.deliveryexecutive.order.dto.OrderTrackingDTO;
import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.order.repository.OrderRepository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderTrackingService {
    private final OrderRepository orderRepository;
    @Autowired(required=false)
    private SimpMessagingTemplate messagingTemplate;
    private final Map<Long, LocationUpdateDTO> executiveLocations = new ConcurrentHashMap<Long, LocationUpdateDTO>();

    public OrderTrackingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderTrackingDTO getOrderTracking(Long orderId) {
        Order order = (Order)this.orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return this.mapToTrackingDTO(order);
    }

    public void updateExecutiveLocation(LocationUpdateDTO locationUpdate) {
        this.executiveLocations.put(locationUpdate.getOrderId(), locationUpdate);
        if (this.messagingTemplate != null) {
            this.messagingTemplate.convertAndSend((Object)("/topic/order/" + locationUpdate.getOrderId() + "/location"), (Object)locationUpdate);
        }
    }

    public void notifyStatusChange(Long orderId) {
        Order order = this.orderRepository.findById(orderId).orElse(null);
        if (order != null && this.messagingTemplate != null) {
            OrderTrackingDTO tracking = this.mapToTrackingDTO(order);
            this.messagingTemplate.convertAndSend((Object)("/topic/order/" + orderId + "/status"), (Object)tracking);
        }
    }

    private OrderTrackingDTO mapToTrackingDTO(Order order) {
        OrderTrackingDTO dto = new OrderTrackingDTO();
        dto.setOrderId(order.getId());
        dto.setStatus(order.getOrderStatus().name());
        dto.setStatusMessage(this.getStatusMessage(order.getOrderStatus()));
        dto.setStepNumber(this.getStepNumber(order.getOrderStatus()));
        dto.setTotalSteps(5);
        if (order.getPickupLocation() != null) {
            dto.setPickupLatitude(order.getPickupLocation().getX());
            dto.setPickupLongitude(order.getPickupLocation().getY());
        }
        if (order.getDeliveryLocation() != null) {
            dto.setDeliveryLatitude(order.getDeliveryLocation().getX());
            dto.setDeliveryLongitude(order.getDeliveryLocation().getY());
        }
        if (order.getExecutive() != null) {
            dto.setExecutiveId(order.getExecutive().getId());
            dto.setExecutiveName(order.getExecutive().getName());
            dto.setExecutivePhone(order.getExecutive().getMobile());
            LocationUpdateDTO liveLocation = this.executiveLocations.get(order.getId());
            if (liveLocation != null) {
                dto.setExecutiveLatitude(liveLocation.getLatitude());
                dto.setExecutiveLongitude(liveLocation.getLongitude());
            }
        }
        dto.setEstimatedPrepTime(order.getEstimatedPrepTime());
        dto.setEstimatedDeliveryTime(order.getEstimatedDeliveryTime());
        dto.setRemainingMinutes(this.calculateRemainingMinutes(order));
        dto.setCreatedAt(order.getCreatedAt());
        dto.setAcceptedAt(order.getAcceptedAt());
        dto.setPickedUpAt(order.getPickedUpAt());
        dto.setDeliveredAt(order.getDeliveredAt());
        dto.setLastUpdated(order.getUpdatedAt());
        if (order.getHomeMaker() != null) {
            dto.setHomemakerName(order.getHomeMaker().getName());
        }
        dto.setAmount(order.getAmount());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setCustomerOtp(order.getCustomerOtp());
        return dto;
    }

    private String getStatusMessage(Order.OrderStatus status) {
        return switch (status) {
            default -> throw new IncompatibleClassChangeError();
            case Order.OrderStatus.PENDING -> "Order placed, waiting for confirmation";
            case Order.OrderStatus.PREPARING -> "Your food is being prepared";
            case Order.OrderStatus.PREPARED -> "Food is ready, waiting for pickup";
            case Order.OrderStatus.ACCEPTED -> "Delivery partner assigned";
            case Order.OrderStatus.OUTFORDELIVERY -> "Your order is on the way!";
            case Order.OrderStatus.DELIVERED -> "Order delivered successfully";
            case Order.OrderStatus.CANCELLED -> "Order was cancelled";
        };
    }

    private int getStepNumber(Order.OrderStatus status) {
        return switch (status) {
            default -> throw new IncompatibleClassChangeError();
            case Order.OrderStatus.PENDING -> 1;
            case Order.OrderStatus.PREPARING -> 2;
            case Order.OrderStatus.PREPARED, Order.OrderStatus.ACCEPTED -> 3;
            case Order.OrderStatus.OUTFORDELIVERY -> 4;
            case Order.OrderStatus.DELIVERED -> 5;
            case Order.OrderStatus.CANCELLED -> 0;
        };
    }

    private Integer calculateRemainingMinutes(Order order) {
        if (order.getOrderStatus() == Order.OrderStatus.DELIVERED || order.getOrderStatus() == Order.OrderStatus.CANCELLED) {
            return 0;
        }
        int totalEstimate = (order.getEstimatedPrepTime() != null ? order.getEstimatedPrepTime() : 20) + (order.getEstimatedDeliveryTime() != null ? order.getEstimatedDeliveryTime() : 15);
        long minutesPassed = ChronoUnit.MINUTES.between(order.getCreatedAt(), LocalDateTime.now());
        int remaining = totalEstimate - (int)minutesPassed;
        return Math.max(remaining, 1);
    }
}
