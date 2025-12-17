package com.foodapp.deliveryexecutive.websocket.service;

import com.foodapp.deliveryexecutive.websocket.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    // Send order status update to specific user
    public void sendOrderStatusToUser(Long userId, String role, OrderStatusMessage message) {
        String destination = "/queue/orders/" + message.getOrderId() + "/status";
        message.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSendToUser(userId + "_" + role, destination, message);
        log.info("Sent order status update to user {}_{}: {}", userId, role, message.getStatus());
    }

    // Broadcast order status to topic (for order tracking page)
    public void broadcastOrderStatus(Long orderId, OrderStatusMessage message) {
        String destination = "/topic/order/" + orderId + "/status";
        message.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSend(destination, message);
        log.info("Broadcast order {} status: {}", orderId, message.getStatus());
    }

    // Send location update for live tracking
    public void sendLocationUpdate(Long orderId, LocationUpdateMessage message) {
        String destination = "/topic/order/" + orderId + "/location";
        message.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSend(destination, message);
        log.debug("Sent location update for order {}: ({}, {})", orderId, message.getLatitude(), message.getLongitude());
    }

    // Send notification to specific user
    public void sendNotificationToUser(Long userId, String role, NotificationMessage message) {
        String destination = "/queue/notifications";
        message.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSendToUser(userId + "_" + role, destination, message);
        log.info("Sent notification to user {}_{}: {}", userId, role, message.getTitle());
    }

    // Broadcast new order to all nearby delivery executives
    public void broadcastNewOrderToExecutives(NewOrderMessage message) {
        String destination = "/topic/executives/new-orders";
        message.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSend(destination, message);
        log.info("Broadcast new order {} to executives", message.getOrderId());
    }

    // Send new order notification to specific executive
    public void sendNewOrderToExecutive(Long executiveId, NewOrderMessage message) {
        String destination = "/queue/new-orders";
        message.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSendToUser(executiveId + "_DELIVERYEXECUTIVE", destination, message);
        log.info("Sent new order {} to executive {}", message.getOrderId(), executiveId);
    }

    // Notify homemaker about order updates
    public void notifyHomemaker(Long homemakerId, OrderStatusMessage message) {
        String destination = "/queue/orders/updates";
        message.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSendToUser(homemakerId + "_HOMEMAKER", destination, message);
        log.info("Notified homemaker {} about order {} status: {}", homemakerId, message.getOrderId(), message.getStatus());
    }

    // Notify customer about order updates
    public void notifyCustomer(Long customerId, OrderStatusMessage message) {
        String destination = "/queue/orders/updates";
        message.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSendToUser(customerId + "_USER", destination, message);
        log.info("Notified customer {} about order {} status: {}", customerId, message.getOrderId(), message.getStatus());
    }
}
