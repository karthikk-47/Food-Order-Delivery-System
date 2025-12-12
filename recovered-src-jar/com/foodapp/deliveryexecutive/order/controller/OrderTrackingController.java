/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.http.ResponseEntity
 *  org.springframework.messaging.handler.annotation.DestinationVariable
 *  org.springframework.messaging.handler.annotation.MessageMapping
 *  org.springframework.messaging.handler.annotation.SendTo
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.order.controller;

import com.foodapp.deliveryexecutive.order.dto.LocationUpdateDTO;
import com.foodapp.deliveryexecutive.order.dto.OrderTrackingDTO;
import com.foodapp.deliveryexecutive.order.service.OrderTrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/orders"})
public class OrderTrackingController {
    private final OrderTrackingService trackingService;

    public OrderTrackingController(OrderTrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping(value={"/{orderId}/track"})
    public ResponseEntity<OrderTrackingDTO> trackOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok((Object)this.trackingService.getOrderTracking(orderId));
    }

    @MessageMapping(value={"/order/{orderId}/location"})
    @SendTo(value={"/topic/order/{orderId}/location"})
    public LocationUpdateDTO updateLocation(@DestinationVariable Long orderId, LocationUpdateDTO location) {
        location.setOrderId(orderId);
        this.trackingService.updateExecutiveLocation(location);
        return location;
    }
}
