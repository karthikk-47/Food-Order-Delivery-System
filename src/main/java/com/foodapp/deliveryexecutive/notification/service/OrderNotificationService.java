package com.foodapp.deliveryexecutive.notification.service;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.notification.dto.PushNotificationRequest;
import com.foodapp.deliveryexecutive.notification.entity.Notification.NotificationType;
import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.websocket.dto.NewOrderMessage;
import com.foodapp.deliveryexecutive.websocket.dto.OrderStatusMessage;
import com.foodapp.deliveryexecutive.websocket.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderNotificationService {

    private final NotificationService notificationService;
    private final DeliveryExecutiveRepository deliveryExecutiveRepository;
    private final WebSocketService webSocketService;

    // ==================== USER NOTIFICATIONS ====================

    public void notifyUserOrderPlaced(Order order) {
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Order Placed Successfully!")
                .message("Your order #" + order.getId() + " has been placed. We'll notify you when it's confirmed.")
                .type(NotificationType.ORDER_PLACED)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getUser().getId(), Actor.Role.USER, request);
        
        // Send WebSocket notification
        sendOrderStatusWebSocket(order, "PENDING", "Order placed successfully");
        log.info("Sent order placed notification to user {}", order.getUser().getId());
    }

    public void notifyUserOrderConfirmed(Order order) {
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Order Confirmed!")
                .message("Your order #" + order.getId() + " has been confirmed by " + order.getHomeMaker().getName())
                .type(NotificationType.ORDER_CONFIRMED)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getUser().getId(), Actor.Role.USER, request);
        
        // Send WebSocket notification
        sendOrderStatusWebSocket(order, "CONFIRMED", "Order confirmed by " + order.getHomeMaker().getName());
    }

    public void notifyUserOrderPreparing(Order order) {
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Food Being Prepared")
                .message("Your order #" + order.getId() + " is now being prepared. Estimated time: " + 
                        order.getEstimatedPrepTime() + " mins")
                .type(NotificationType.ORDER_PREPARING)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getUser().getId(), Actor.Role.USER, request);
        
        // Send WebSocket notification
        sendOrderStatusWebSocket(order, "PREPARING", "Food is being prepared");
    }

    public void notifyUserOrderReady(Order order) {
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Food Ready!")
                .message("Your order #" + order.getId() + " is ready and waiting for pickup")
                .type(NotificationType.ORDER_READY)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getUser().getId(), Actor.Role.USER, request);
        
        // Send WebSocket notification
        sendOrderStatusWebSocket(order, "PREPARED", "Food is ready for pickup");
    }

    public void notifyUserOrderPickedUp(Order order) {
        String executiveName = order.getExecutive() != null ? order.getExecutive().getName() : "Delivery partner";
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Order Picked Up!")
                .message(executiveName + " has picked up your order #" + order.getId())
                .type(NotificationType.ORDER_PICKED_UP)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getUser().getId(), Actor.Role.USER, request);
        
        // Send WebSocket notification with executive info
        OrderStatusMessage wsMessage = buildOrderStatusMessage(order, "PICKED_UP", executiveName + " has picked up your order");
        if (order.getExecutive() != null) {
            wsMessage.setExecutiveId(order.getExecutive().getId());
            wsMessage.setExecutiveName(executiveName);
        }
        webSocketService.notifyCustomer(order.getUser().getId(), wsMessage);
        webSocketService.broadcastOrderStatus(order.getId(), wsMessage);
    }

    public void notifyUserOrderOutForDelivery(Order order) {
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Out for Delivery!")
                .message("Your order #" + order.getId() + " is on its way! Estimated arrival: " + 
                        order.getEstimatedDeliveryTime() + " mins")
                .type(NotificationType.ORDER_OUT_FOR_DELIVERY)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getUser().getId(), Actor.Role.USER, request);
        
        // Send WebSocket notification with executive info
        OrderStatusMessage wsMessage = buildOrderStatusMessage(order, "OUTFORDELIVERY", "Your order is on the way!");
        if (order.getExecutive() != null) {
            wsMessage.setExecutiveId(order.getExecutive().getId());
            wsMessage.setExecutiveName(order.getExecutive().getName());
        }
        if (order.getEstimatedDeliveryTime() != null) {
            wsMessage.setEstimatedTime(order.getEstimatedDeliveryTime() + " mins");
        }
        webSocketService.notifyCustomer(order.getUser().getId(), wsMessage);
        webSocketService.broadcastOrderStatus(order.getId(), wsMessage);
    }

    public void notifyUserOrderDelivered(Order order) {
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Order Delivered!")
                .message("Your order #" + order.getId() + " has been delivered. Enjoy your meal!")
                .type(NotificationType.ORDER_DELIVERED)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getUser().getId(), Actor.Role.USER, request);
        
        // Send WebSocket notification
        sendOrderStatusWebSocket(order, "DELIVERED", "Order delivered successfully!");
    }

    public void notifyUserOrderCancelled(Order order, String reason) {
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Order Cancelled")
                .message("Your order #" + order.getId() + " has been cancelled. " + 
                        (reason != null ? "Reason: " + reason : ""))
                .type(NotificationType.ORDER_CANCELLED)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getUser().getId(), Actor.Role.USER, request);
        
        // Send WebSocket notification
        sendOrderStatusWebSocket(order, "CANCELLED", reason != null ? reason : "Order cancelled");
    }

    // ==================== HOMEMAKER NOTIFICATIONS ====================

    public void notifyHomemakerNewOrder(Order order) {
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("New Order Received!")
                .message("You have a new order #" + order.getId() + " worth ₹" + order.getAmount())
                .type(NotificationType.ORDER_PLACED)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getHomeMaker().getId(), Actor.Role.HOMEMAKER, request);
        
        // Send WebSocket notification to homemaker
        OrderStatusMessage wsMessage = buildOrderStatusMessage(order, "NEW_ORDER", "New order received worth ₹" + order.getAmount());
        webSocketService.notifyHomemaker(order.getHomeMaker().getId(), wsMessage);
        
        log.info("Sent new order notification to homemaker {}", order.getHomeMaker().getId());
    }

    public void notifyHomemakerOrderCancelled(Order order, String reason) {
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Order Cancelled")
                .message("Order #" + order.getId() + " has been cancelled by the customer")
                .type(NotificationType.ORDER_CANCELLED)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getHomeMaker().getId(), Actor.Role.HOMEMAKER, request);
        
        // Send WebSocket notification
        OrderStatusMessage wsMessage = buildOrderStatusMessage(order, "CANCELLED", "Order cancelled by customer");
        webSocketService.notifyHomemaker(order.getHomeMaker().getId(), wsMessage);
    }

    // ==================== DELIVERY EXECUTIVE NOTIFICATIONS ====================

    public void notifyNearbyExecutivesNewOrder(Order order, double pickupLat, double pickupLng, double radiusKm) {
        // Find online executives within radius
        List<DeliveryExecutive> nearbyExecutives = findNearbyOnlineExecutives(pickupLat, pickupLng, radiusKm);
        
        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("New Delivery Available!")
                .message("New order #" + order.getId() + " nearby. Distance: " + 
                        calculateDistance(pickupLat, pickupLng, order) + " km")
                .type(NotificationType.NEW_ORDER_NEARBY)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        // Build WebSocket message for new order
        NewOrderMessage newOrderMessage = NewOrderMessage.builder()
                .orderId(order.getId())
                .homemakerId(order.getHomeMaker().getId())
                .homemakerName(order.getHomeMaker().getName())
                .pickupLatitude(pickupLat)
                .pickupLongitude(pickupLng)
                .deliveryFee(order.getDeliveryFee())
                .priority(order.getPriority() != null ? order.getPriority().name() : "MEDIUM")
                .build();
        
        // Set delivery location if available
        if (order.getDeliveryLocation() != null) {
            newOrderMessage.setDeliveryLatitude(order.getDeliveryLocation().getX());
            newOrderMessage.setDeliveryLongitude(order.getDeliveryLocation().getY());
        }
        newOrderMessage.setDistance(order.getDistance());

        // Broadcast to all executives via WebSocket
        webSocketService.broadcastNewOrderToExecutives(newOrderMessage);

        for (DeliveryExecutive executive : nearbyExecutives) {
            notificationService.sendNotification(executive.getId(), Actor.Role.DELIVERYEXECUTIVE, request);
            // Also send targeted WebSocket notification
            webSocketService.sendNewOrderToExecutive(executive.getId(), newOrderMessage);
        }
        log.info("Notified {} nearby executives about order {}", nearbyExecutives.size(), order.getId());
    }

    public void notifyExecutiveOrderAssigned(Order order) {
        if (order.getExecutive() == null) return;

        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Order Assigned!")
                .message("Order #" + order.getId() + " has been assigned to you. Pickup from: " + 
                        order.getHomeMaker().getName())
                .type(NotificationType.ORDER_CONFIRMED)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getExecutive().getId(), Actor.Role.DELIVERYEXECUTIVE, request);
        
        // Send WebSocket notification
        OrderStatusMessage wsMessage = buildOrderStatusMessage(order, "ASSIGNED", "Order assigned - Pickup from " + order.getHomeMaker().getName());
        webSocketService.sendOrderStatusToUser(order.getExecutive().getId(), "DELIVERYEXECUTIVE", wsMessage);
    }

    public void notifyExecutiveOrderReady(Order order) {
        if (order.getExecutive() == null) return;

        PushNotificationRequest request = PushNotificationRequest.builder()
                .title("Order Ready for Pickup!")
                .message("Order #" + order.getId() + " is ready. Head to " + order.getHomeMaker().getName())
                .type(NotificationType.ORDER_READY)
                .referenceId(order.getId())
                .data(buildOrderData(order))
                .build();

        notificationService.sendNotification(order.getExecutive().getId(), Actor.Role.DELIVERYEXECUTIVE, request);
        
        // Send WebSocket notification
        OrderStatusMessage wsMessage = buildOrderStatusMessage(order, "PREPARED", "Order ready for pickup at " + order.getHomeMaker().getName());
        webSocketService.sendOrderStatusToUser(order.getExecutive().getId(), "DELIVERYEXECUTIVE", wsMessage);
    }

    // ==================== HELPER METHODS ====================

    private void sendOrderStatusWebSocket(Order order, String status, String message) {
        OrderStatusMessage wsMessage = buildOrderStatusMessage(order, status, message);
        webSocketService.notifyCustomer(order.getUser().getId(), wsMessage);
        webSocketService.broadcastOrderStatus(order.getId(), wsMessage);
    }

    private OrderStatusMessage buildOrderStatusMessage(Order order, String status, String message) {
        return OrderStatusMessage.builder()
                .orderId(order.getId())
                .status(status)
                .previousStatus(order.getOrderStatus() != null ? order.getOrderStatus().name() : null)
                .message(message)
                .build();
    }

    private Map<String, String> buildOrderData(Order order) {
        Map<String, String> data = new HashMap<>();
        data.put("orderId", order.getId().toString());
        data.put("status", order.getOrderStatus() != null ? order.getOrderStatus().name() : "PENDING");
        data.put("amount", String.valueOf(order.getAmount()));
        return data;
    }

    private List<DeliveryExecutive> findNearbyOnlineExecutives(double lat, double lng, double radiusKm) {
        // For now, return all online executives
        // TODO: Implement proper geospatial query
        return deliveryExecutiveRepository.findAll().stream()
                .filter(DeliveryExecutive::isOnline)
                .filter(e -> e.getApprovalStatus() != null && 
                        e.getApprovalStatus().name().equals("APPROVED"))
                .toList();
    }

    private String calculateDistance(double lat, double lng, Order order) {
        if (order.getPickupLocation() != null) {
            double pickupLat = order.getPickupLocation().getX();
            double pickupLng = order.getPickupLocation().getY();
            double distance = haversineDistance(lat, lng, pickupLat, pickupLng);
            return String.format("%.1f", distance);
        }
        return "N/A";
    }

    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
