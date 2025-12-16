/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.order.controller;

import com.foodapp.deliveryexecutive.executive.dto.DeliveryProofDTO;
import com.foodapp.deliveryexecutive.order.dto.OrderDetailsDTO;
import com.foodapp.deliveryexecutive.order.dto.OrderSummaryDTO;
import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.order.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/orders"})
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value={"/stats"})
    public ResponseEntity<?> getOrderStats(@RequestParam(required = false) Long userId) {
        java.util.Map<String, Object> stats = new java.util.HashMap<>();
        if (userId != null) {
            stats.put("totalOrders", orderService.getOrderCountByUserId(userId));
            stats.put("totalSpent", orderService.getTotalSpentByUserId(userId));
            stats.put("activeOrders", orderService.getActiveOrderCountByUserId(userId));
        } else {
            stats.put("totalOrders", 0L);
            stats.put("totalSpent", 0.0);
            stats.put("activeOrders", 0L);
        }
        return ResponseEntity.ok(stats);
    }

    @GetMapping(value={"/active"})
    public ResponseEntity<List<OrderDetailsDTO>> getActiveOrdersForUser(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            List<OrderDetailsDTO> orders = orderService.getActiveOrdersByUserId(userId);
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }

    @GetMapping(value={"/{orderId}"})
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(@PathVariable Long orderId) {
        OrderDetailsDTO order = this.orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping(value={"/available"})
    public ResponseEntity<List<OrderSummaryDTO>> getAvailableOrders() {
        List<OrderSummaryDTO> orders = this.orderService.getAvailableOrdersSummary();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value={"/user/{userId}"})
    public ResponseEntity<List<OrderDetailsDTO>> getUserOrders(@PathVariable Long userId) {
        List<OrderDetailsDTO> orders = this.orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value={"/executive/{executiveId}"})
    public ResponseEntity<List<OrderDetailsDTO>> getExecutiveOrders(@PathVariable Long executiveId) {
        List<OrderDetailsDTO> orders = this.orderService.getOrdersByExecutiveId(executiveId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value={"/executive/{executiveId}/active"})
    public ResponseEntity<List<OrderDetailsDTO>> getActiveOrders(@PathVariable Long executiveId) {
        List<OrderDetailsDTO> orders = this.orderService.getActiveOrders(executiveId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value={"/executive/{executiveId}/delivered"})
    public ResponseEntity<List<OrderDetailsDTO>> getDeliveredOrders(@PathVariable Long executiveId) {
        List<OrderDetailsDTO> orders = this.orderService.getDeliveredOrders(executiveId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value={"/status/{status}"})
    public ResponseEntity<List<OrderDetailsDTO>> getOrdersByStatus(@PathVariable Order.OrderStatus status) {
        List<OrderDetailsDTO> orders = this.orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @PostMapping(value={"/{orderId}/accept"})
    public ResponseEntity<OrderDetailsDTO> acceptOrder(@PathVariable Long orderId, @RequestParam Long executiveId) {
        try {
            Order order = this.orderService.acceptOrder(executiveId, orderId);
            return ResponseEntity.ok(this.orderService.getOrderDetails(order.getId()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{orderId}/pickup"})
    public ResponseEntity<OrderDetailsDTO> confirmPickup(@PathVariable Long orderId, @RequestParam Long executiveId) {
        try {
            Order order = this.orderService.confirmPickup(executiveId, orderId);
            return ResponseEntity.ok(this.orderService.getOrderDetails(order.getId()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{orderId}/deliver"})
    public ResponseEntity<OrderDetailsDTO> confirmDelivery(@PathVariable Long orderId, @RequestParam Long executiveId, @RequestBody DeliveryProofDTO proof) {
        try {
            Order order = this.orderService.confirmDelivery(executiveId, orderId, proof.getCustomerOtp());
            return ResponseEntity.ok(this.orderService.getOrderDetails(order.getId()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value={"/{orderId}/status"})
    public ResponseEntity<OrderDetailsDTO> updateOrderStatus(@PathVariable Long orderId, @RequestParam Order.OrderStatus status) {
        Order order = this.orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(this.orderService.getOrderDetails(order.getId()));
    }

    @GetMapping(value={"/executive/{executiveId}/stats/delivered-count"})
    public ResponseEntity<Long> getDeliveredCount(@PathVariable Long executiveId) {
        Long count = this.orderService.getDeliveredOrderCount(executiveId);
        return ResponseEntity.ok(count);
    }

    @GetMapping(value={"/executive/{executiveId}/stats/total-earnings"})
    public ResponseEntity<Double> getTotalEarnings(@PathVariable Long executiveId) {
        Double earnings = this.orderService.getTotalEarnings(executiveId);
        return ResponseEntity.ok(earnings);
    }
}
