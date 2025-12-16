package com.foodapp.deliveryexecutive.order.controller;

import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.order.dto.OrderDetailsDTO;
import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.order.repository.OrderRepository;
import com.foodapp.deliveryexecutive.order.service.OrderService;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class UserOrderController {
    private static final Logger log = LoggerFactory.getLogger(UserOrderController.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HomeMakerRepository homeMakerRepository;

    // POST /api/orders - Create a new order
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
        log.info("Creating order for user: {}", request.getUserId());
        try {
            User user = userRepository.findById(request.getUserId()).orElse(null);
            if (user == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
            }

            HomeMaker homeMaker = homeMakerRepository.findById(request.getHomemakerId()).orElse(null);
            if (homeMaker == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Homemaker not found"));
            }

            Order order = new Order();
            order.setUser(user);
            order.setHomeMaker(homeMaker);
            order.setAmount(request.getAmount());
            double deliveryFee = request.getDeliveryFee() != null ? request.getDeliveryFee() : 30.0;
            order.setDeliveryFee(deliveryFee);
            order.setPayment(request.getAmount() + deliveryFee);
            order.setPaymentMethod(request.getPaymentMethod() != null ? request.getPaymentMethod() : "ONLINE");
            order.setOrderStatus(Order.OrderStatus.PENDING);
            order.setSpecialInstructions(request.getSpecialInstructions());
            
            // Set locations
            if (request.getPickupLat() != null && request.getPickupLng() != null) {
                order.setPickupLocation(new Point(request.getPickupLat(), request.getPickupLng()));
            }
            if (request.getDeliveryLat() != null && request.getDeliveryLng() != null) {
                order.setDeliveryLocation(new Point(request.getDeliveryLat(), request.getDeliveryLng()));
            }
            
            // Calculate distance (simplified)
            if (order.getPickupLocation() != null && order.getDeliveryLocation() != null) {
                double dist = calculateDistance(
                    order.getPickupLocation().getX(), order.getPickupLocation().getY(),
                    order.getDeliveryLocation().getX(), order.getDeliveryLocation().getY()
                );
                order.setDistance(dist);
            }

            // Generate OTP for delivery verification
            order.setCustomerOtp(String.format("%04d", new Random().nextInt(10000)));

            Order savedOrder = orderRepository.save(order);
            log.info("Order created successfully with ID: {}", savedOrder.getId());

            return ResponseEntity.ok(orderService.getOrderDetails(savedOrder.getId()));
        } catch (Exception e) {
            log.error("Error creating order", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to create order: " + e.getMessage()));
        }
    }

    // PUT /api/orders/{orderId}/cancel - Cancel an order
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        log.info("Cancelling order: {}", orderId);
        try {
            Order order = orderRepository.findById(orderId).orElse(null);
            if (order == null) {
                return ResponseEntity.notFound().build();
            }

            // Only allow cancellation for certain statuses
            Order.OrderStatus status = order.getOrderStatus();
            if (status == Order.OrderStatus.DELIVERED || status == Order.OrderStatus.OUTFORDELIVERY) {
                return ResponseEntity.badRequest().body(Map.of(
                    "error", "Cannot cancel order in " + status + " status"
                ));
            }

            order.setOrderStatus(Order.OrderStatus.CANCELLED);
            orderRepository.save(order);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Order cancelled successfully",
                "orderId", orderId
            ));
        } catch (Exception e) {
            log.error("Error cancelling order", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to cancel order"));
        }
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // DTO for create order request
    public static class CreateOrderRequest {
        private Long userId;
        private Long homemakerId;
        private Double amount;
        private Double deliveryFee;
        private String paymentMethod;
        private String specialInstructions;
        private Double pickupLat;
        private Double pickupLng;
        private Double deliveryLat;
        private Double deliveryLng;
        private List<OrderItem> items;

        // Getters and setters
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public Long getHomemakerId() { return homemakerId; }
        public void setHomemakerId(Long homemakerId) { this.homemakerId = homemakerId; }
        public Double getAmount() { return amount; }
        public void setAmount(Double amount) { this.amount = amount; }
        public Double getDeliveryFee() { return deliveryFee; }
        public void setDeliveryFee(Double deliveryFee) { this.deliveryFee = deliveryFee; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public String getSpecialInstructions() { return specialInstructions; }
        public void setSpecialInstructions(String specialInstructions) { this.specialInstructions = specialInstructions; }
        public Double getPickupLat() { return pickupLat; }
        public void setPickupLat(Double pickupLat) { this.pickupLat = pickupLat; }
        public Double getPickupLng() { return pickupLng; }
        public void setPickupLng(Double pickupLng) { this.pickupLng = pickupLng; }
        public Double getDeliveryLat() { return deliveryLat; }
        public void setDeliveryLat(Double deliveryLat) { this.deliveryLat = deliveryLat; }
        public Double getDeliveryLng() { return deliveryLng; }
        public void setDeliveryLng(Double deliveryLng) { this.deliveryLng = deliveryLng; }
        public List<OrderItem> getItems() { return items; }
        public void setItems(List<OrderItem> items) { this.items = items; }
    }

    public static class OrderItem {
        private Long menuItemId;
        private Integer quantity;
        private String specialInstructions;

        public Long getMenuItemId() { return menuItemId; }
        public void setMenuItemId(Long menuItemId) { this.menuItemId = menuItemId; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public String getSpecialInstructions() { return specialInstructions; }
        public void setSpecialInstructions(String specialInstructions) { this.specialInstructions = specialInstructions; }
    }
}
