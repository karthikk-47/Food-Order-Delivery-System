/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.CrossOrigin
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.payments.controller;

import com.foodapp.deliveryexecutive.payments.dto.CreateOrderPaymentRequest;
import com.foodapp.deliveryexecutive.payments.dto.OrderPaymentResponse;
import com.foodapp.deliveryexecutive.payments.dto.VerifyPaymentRequest;
import com.foodapp.deliveryexecutive.payments.service.OrderPaymentService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/order-payments"})
@CrossOrigin(origins={"*"})
public class OrderPaymentController {
    private static final Logger logger = LoggerFactory.getLogger(OrderPaymentController.class);
    @Autowired
    private OrderPaymentService orderPaymentService;

    @PostMapping(value={"/create"})
    public ResponseEntity<?> createPayment(@Valid @RequestBody CreateOrderPaymentRequest request) {
        try {
            logger.info("Creating payment for order: {}", request.getOrderId());
            OrderPaymentResponse response = this.orderPaymentService.createPayment(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            logger.error("Error creating payment: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping(value={"/verify"})
    public ResponseEntity<?> verifyPayment(@Valid @RequestBody VerifyPaymentRequest request) {
        try {
            logger.info("Verifying payment for Razorpay order: {}", request.getRazorpayOrderId());
            OrderPaymentResponse response = this.orderPaymentService.verifyPayment(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            logger.error("Error verifying payment: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping(value={"/order/{orderId}"})
    public ResponseEntity<?> getPaymentByOrderId(@PathVariable Long orderId) {
        try {
            logger.info("Fetching payment for order: {}", orderId);
            OrderPaymentResponse response = this.orderPaymentService.getPaymentByOrderId(orderId);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            logger.error("Error fetching payment: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping(value={"/user/{userId}"})
    public ResponseEntity<?> getUserPaymentHistory(@PathVariable Long userId) {
        try {
            logger.info("Fetching payment history for user: {}", userId);
            List<OrderPaymentResponse> payments = this.orderPaymentService.getUserPaymentHistory(userId);
            return ResponseEntity.ok(payments);
        }
        catch (Exception e) {
            logger.error("Error fetching payment history: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping(value={"/user/{userId}/successful"})
    public ResponseEntity<?> getSuccessfulPayments(@PathVariable Long userId) {
        try {
            logger.info("Fetching successful payments for user: {}", userId);
            List<OrderPaymentResponse> payments = this.orderPaymentService.getSuccessfulPaymentsByUser(userId);
            return ResponseEntity.ok(payments);
        }
        catch (Exception e) {
            logger.error("Error fetching successful payments: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping(value={"/{paymentId}/refund"})
    public ResponseEntity<?> initiateRefund(@PathVariable Long paymentId, @RequestParam(required=false, defaultValue="Customer requested refund") String reason) {
        try {
            logger.info("Initiating refund for payment: {}", paymentId);
            OrderPaymentResponse response = this.orderPaymentService.initiateRefund(paymentId, reason);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            logger.error("Error initiating refund: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping(value={"/failure"})
    public ResponseEntity<?> handlePaymentFailure(@RequestParam String razorpayOrderId, @RequestParam String reason, @RequestParam(required=false) String errorCode) {
        try {
            logger.info("Handling payment failure for Razorpay order: {}", razorpayOrderId);
            this.orderPaymentService.handlePaymentFailure(razorpayOrderId, reason, errorCode);
            HashMap<String, String> response = new HashMap<String, String>();
            response.put("message", "Payment failure recorded successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            logger.error("Error handling payment failure: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping(value={"/user/{userId}/stats"})
    public ResponseEntity<?> getUserPaymentStats(@PathVariable Long userId) {
        try {
            logger.info("Fetching payment stats for user: {}", userId);
            HashMap<String, Number> stats = new HashMap<String, Number>();
            stats.put("totalAmountPaid", this.orderPaymentService.getTotalAmountPaidByUser(userId));
            stats.put("successfulPaymentCount", this.orderPaymentService.getSuccessfulPaymentCount(userId));
            return ResponseEntity.ok(stats);
        }
        catch (Exception e) {
            logger.error("Error fetching payment stats: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping(value={"/process-expired"})
    public ResponseEntity<?> processExpiredPayments() {
        try {
            logger.info("Processing expired payments");
            this.orderPaymentService.processExpiredPayments();
            HashMap<String, String> response = new HashMap<String, String>();
            response.put("message", "Expired payments processed successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            logger.error("Error processing expired payments: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
