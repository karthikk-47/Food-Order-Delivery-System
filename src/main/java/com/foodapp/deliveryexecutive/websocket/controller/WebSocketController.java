package com.foodapp.deliveryexecutive.websocket.controller;

import com.foodapp.deliveryexecutive.websocket.dto.LocationUpdateMessage;
import com.foodapp.deliveryexecutive.websocket.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WebSocketController {

    private final WebSocketService webSocketService;

    // Handle location updates from delivery executives
    @MessageMapping("/location/{orderId}")
    public void handleLocationUpdate(
            @DestinationVariable Long orderId,
            @Payload LocationUpdateMessage message) {
        log.debug("Received location update for order {}: ({}, {})", 
                orderId, message.getLatitude(), message.getLongitude());
        message.setOrderId(orderId);
        webSocketService.sendLocationUpdate(orderId, message);
    }

    // Handle subscription to order tracking
    @MessageMapping("/subscribe/order/{orderId}")
    public void subscribeToOrder(@DestinationVariable Long orderId) {
        log.info("Client subscribed to order {} tracking", orderId);
    }

    // Handle unsubscription from order tracking
    @MessageMapping("/unsubscribe/order/{orderId}")
    public void unsubscribeFromOrder(@DestinationVariable Long orderId) {
        log.info("Client unsubscribed from order {} tracking", orderId);
    }
}
