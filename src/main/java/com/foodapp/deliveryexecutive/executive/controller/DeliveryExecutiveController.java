package com.foodapp.deliveryexecutive.executive.controller;

import com.foodapp.deliveryexecutive.executive.dto.DeliveryLoginRequest;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryLoginResponse;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryProofDTO;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryRegisterRequest;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryRegisterResponse;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryStatusUpdateRequest;
import com.foodapp.deliveryexecutive.executive.service.DeliveryExecutiveService;
import com.foodapp.deliveryexecutive.map.dto.DirectionResponseDTO;
import com.foodapp.deliveryexecutive.map.service.MapService;
import com.foodapp.deliveryexecutive.order.dto.OrderDetailsDTO;
import com.foodapp.deliveryexecutive.order.dto.OrderSummaryDTO;
import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.order.service.OrderService;
import com.foodapp.deliveryexecutive.rating.dto.RatingDTO;
import com.foodapp.deliveryexecutive.wallet.dto.WalletDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
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
@RequestMapping(value={"/api/delivery-executive"})
public class DeliveryExecutiveController {
    @Autowired
    private DeliveryExecutiveService deliveryExecutiveService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MapService mapService;

    @PostMapping(value={"/register"})
    public ResponseEntity<DeliveryRegisterResponse> register(@RequestBody DeliveryRegisterRequest request) {
        DeliveryRegisterResponse response = this.deliveryExecutiveService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value={"/login"})
    public ResponseEntity<DeliveryLoginResponse> login(@RequestBody DeliveryLoginRequest request) {
        DeliveryLoginResponse response = this.deliveryExecutiveService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value={"/{id}/status"})
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody DeliveryStatusUpdateRequest request) {
        this.deliveryExecutiveService.updateStatus(id, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value={"/{id}/location"})
    public ResponseEntity<Void> updateLocation(@PathVariable Long id, @RequestBody Point location) {
        this.deliveryExecutiveService.updateLiveLocation(id, location);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value={"/{id}/nearby-orders"})
    public ResponseEntity<List<OrderSummaryDTO>> getNearbyOrders(@PathVariable Long id, @RequestParam double latitude, @RequestParam double longitude, @RequestParam(defaultValue="optimal") String sortBy) {
        Point location = new Point(latitude, longitude);
        List<OrderSummaryDTO> orders = this.deliveryExecutiveService.getNearbyOrders(id, location, sortBy);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value={"/{id}/orders"})
    public ResponseEntity<List<OrderDetailsDTO>> getExecutiveOrders(@PathVariable Long id) {
        List<OrderDetailsDTO> orders = this.orderService.getOrdersByExecutiveId(id);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value={"/{id}/orders/active"})
    public ResponseEntity<List<OrderDetailsDTO>> getActiveOrders(@PathVariable Long id) {
        List<OrderDetailsDTO> orders = this.orderService.getActiveOrders(id);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value={"/{id}/orders/delivered"})
    public ResponseEntity<List<OrderDetailsDTO>> getDeliveredOrders(@PathVariable Long id) {
        List<OrderDetailsDTO> orders = this.orderService.getDeliveredOrders(id);
        return ResponseEntity.ok(orders);
    }

    @PostMapping(value={"/{id}/orders/{orderId}/accept"})
    public ResponseEntity<DirectionResponseDTO> acceptOrder(@PathVariable Long id, @PathVariable Long orderId) {
        try {
            Order order = this.orderService.acceptOrder(id, orderId);
            DirectionResponseDTO direction = this.mapService.getRoute(order.getPickupLocation(), order.getDeliveryLocation());
            return ResponseEntity.ok(direction);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/orders/{orderId}/pickup"})
    public ResponseEntity<OrderDetailsDTO> confirmPickup(@PathVariable Long id, @PathVariable Long orderId) {
        try {
            Order order = this.orderService.confirmPickup(id, orderId);
            return ResponseEntity.ok(this.orderService.getOrderDetails(order.getId()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/orders/{orderId}/deliver"})
    public ResponseEntity<OrderDetailsDTO> confirmDelivery(@PathVariable Long id, @PathVariable Long orderId, @RequestBody DeliveryProofDTO proof) {
        try {
            Order order = this.orderService.confirmDelivery(id, orderId, proof.getCustomerOtp());
            this.deliveryExecutiveService.processDeliveryPayment(id, orderId);
            return ResponseEntity.ok(this.orderService.getOrderDetails(order.getId()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}/wallet"})
    public ResponseEntity<WalletDTO> getWallet(@PathVariable Long id) {
        WalletDTO wallet = this.deliveryExecutiveService.getWallet(id);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping(value={"/{id}/ratings"})
    public ResponseEntity<List<RatingDTO>> getRatings(@PathVariable Long id) {
        List<RatingDTO> ratings = this.deliveryExecutiveService.getRatings(id);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping(value={"/{id}/ratings/average"})
    public ResponseEntity<Double> getAverageRating(@PathVariable Long id) {
        Double average = this.deliveryExecutiveService.getAverageRating(id);
        return ResponseEntity.ok(average);
    }

    @GetMapping(value={"/{id}/stats/delivered-count"})
    public ResponseEntity<Long> getDeliveredCount(@PathVariable Long id) {
        Long count = this.orderService.getDeliveredOrderCount(id);
        return ResponseEntity.ok(count);
    }

    @GetMapping(value={"/{id}/stats/total-earnings"})
    public ResponseEntity<Double> getTotalEarnings(@PathVariable Long id) {
        Double earnings = this.orderService.getTotalEarnings(id);
        return ResponseEntity.ok(earnings);
    }

    @GetMapping(value={"/{id}/profile"})
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        try {
            Map<String, Object> profile = this.deliveryExecutiveService.getProfileById(id);
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value={"/{id}/profile"})
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            this.deliveryExecutiveService.updateProfile(id, updates);
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("success", true);
            response.put("message", "Profile updated successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
