package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.order.dto.OrderDetailsDTO;
import com.foodapp.deliveryexecutive.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class HomemakerOrderController {
    private static final Logger log = LoggerFactory.getLogger(HomemakerOrderController.class);

    @Autowired
    private OrderService orderService;

    // GET /api/orders/homemaker/{homemakerId} - Get all orders for a homemaker
    @GetMapping("/homemaker/{homemakerId}")
    public ResponseEntity<?> getHomemakerOrders(@PathVariable Long homemakerId) {
        log.info("Fetching orders for homemaker: {}", homemakerId);
        try {
            List<OrderDetailsDTO> orders = orderService.getOrdersByHomemakerId(homemakerId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            log.error("Error fetching homemaker orders", e);
            return ResponseEntity.ok(List.of());
        }
    }

    // GET /api/orders/homemaker/recent - Get recent orders (for dashboard)
    @GetMapping("/homemaker/recent")
    public ResponseEntity<?> getRecentOrders(@RequestParam(required = false) Long homemakerId) {
        log.info("Fetching recent orders for homemaker: {}", homemakerId);
        try {
            List<OrderDetailsDTO> orders;
            if (homemakerId != null) {
                orders = orderService.getOrdersByHomemakerId(homemakerId);
            } else {
                orders = orderService.getRecentOrders(10);
            }
            // Limit to 10 most recent
            if (orders.size() > 10) {
                orders = orders.subList(0, 10);
            }
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            log.error("Error fetching recent orders", e);
            return ResponseEntity.ok(List.of());
        }
    }

    // PUT /api/orders/{orderId}/accept - Homemaker accepts order
    @PutMapping("/{orderId}/accept")
    public ResponseEntity<?> acceptOrder(@PathVariable Long orderId) {
        log.info("Homemaker accepting order: {}", orderId);
        try {
            orderService.homemakerAcceptOrder(orderId);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Order accepted",
                "order", orderService.getOrderDetails(orderId)
            ));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            log.error("Error accepting order", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to accept order"));
        }
    }

    // PUT /api/orders/{orderId}/ready - Mark order as ready for pickup
    @PutMapping("/{orderId}/ready")
    public ResponseEntity<?> markOrderReady(@PathVariable Long orderId) {
        log.info("Marking order as ready: {}", orderId);
        try {
            orderService.markOrderReady(orderId);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Order marked as ready",
                "order", orderService.getOrderDetails(orderId)
            ));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            log.error("Error marking order ready", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to mark order ready"));
        }
    }
}
