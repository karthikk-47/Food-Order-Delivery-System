/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.databind.JsonNode
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.payments.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.order.repository.OrderRepository;
import com.foodapp.deliveryexecutive.payments.dto.CreateOrderPaymentRequest;
import com.foodapp.deliveryexecutive.payments.dto.OrderPaymentResponse;
import com.foodapp.deliveryexecutive.payments.dto.VerifyPaymentRequest;
import com.foodapp.deliveryexecutive.payments.entity.OrderPayment;
import com.foodapp.deliveryexecutive.payments.repository.OrderPaymentRepository;
import com.foodapp.deliveryexecutive.payments.service.PaymentsApi;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderPaymentService {
    private static final Logger logger = LoggerFactory.getLogger(OrderPaymentService.class);
    @Autowired
    private OrderPaymentRepository orderPaymentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentsApi paymentsApi;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public OrderPaymentResponse createPayment(CreateOrderPaymentRequest request) {
        logger.info("Creating payment for order: {}", request.getOrderId());
        Optional orderOpt = this.orderRepository.findById(request.getOrderId());
        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Order not found with ID: " + request.getOrderId());
        }
        Order order = (Order)orderOpt.get();
        Optional<OrderPayment> existingPayment = this.orderPaymentRepository.findByOrderId(request.getOrderId());
        if (existingPayment.isPresent() && (existingPayment.get().getStatus() == OrderPayment.PaymentStatus.SUCCESS || existingPayment.get().getStatus() == OrderPayment.PaymentStatus.PENDING)) {
            throw new RuntimeException("Payment already exists for this order");
        }
        try {
            String receipt = "order_" + request.getOrderId() + "_" + System.currentTimeMillis();
            String notes = request.getNotes() != null ? request.getNotes() : "Food order payment";
            String razorpayResponse = this.paymentsApi.createRazorpayOrder(request.getAmount(), "INR", receipt, notes);
            JsonNode responseJson = this.objectMapper.readTree(razorpayResponse);
            String razorpayOrderId = responseJson.get("id").asText();
            OrderPayment payment = new OrderPayment();
            payment.setOrderId(request.getOrderId());
            payment.setUserId(request.getUserId());
            payment.setAmount(request.getAmount());
            payment.setCurrency("INR");
            payment.setRazorpayOrderId(razorpayOrderId);
            payment.setStatus(OrderPayment.PaymentStatus.CREATED);
            payment.setCustomerEmail(request.getCustomerEmail());
            payment.setCustomerPhone(request.getCustomerPhone());
            payment.setPaymentDescription("Payment for Order #" + request.getOrderId());
            OrderPayment savedPayment = (OrderPayment)this.orderPaymentRepository.save(payment);
            logger.info("Payment created successfully with ID: {}", savedPayment.getId());
            return this.mapToResponse(savedPayment);
        }
        catch (Exception e) {
            logger.error("Error creating payment: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create payment: " + e.getMessage());
        }
    }

    @Transactional
    public OrderPaymentResponse verifyPayment(VerifyPaymentRequest request) {
        logger.info("Verifying payment for Razorpay order: {}", request.getRazorpayOrderId());
        Optional<OrderPayment> paymentOpt = this.orderPaymentRepository.findByRazorpayOrderId(request.getRazorpayOrderId());
        if (paymentOpt.isEmpty()) {
            throw new RuntimeException("Payment not found for Razorpay order: " + request.getRazorpayOrderId());
        }
        OrderPayment payment = paymentOpt.get();
        try {
            boolean isValid = this.verifySignature(request.getRazorpayOrderId(), request.getRazorpayPaymentId(), request.getRazorpaySignature());
            if (!isValid) {
                payment.setStatus(OrderPayment.PaymentStatus.FAILED);
                payment.setFailureReason("Invalid payment signature");
                payment.setFailedAt(LocalDateTime.now());
                this.orderPaymentRepository.save(payment);
                throw new RuntimeException("Payment signature verification failed");
            }
            String paymentDetails = this.paymentsApi.fetchPaymentDetails(request.getRazorpayPaymentId());
            JsonNode paymentJson = this.objectMapper.readTree(paymentDetails);
            payment.setRazorpayPaymentId(request.getRazorpayPaymentId());
            payment.setRazorpaySignature(request.getRazorpaySignature());
            payment.setStatus(OrderPayment.PaymentStatus.SUCCESS);
            payment.setPaidAt(LocalDateTime.now());
            if (paymentJson.has("method")) {
                payment.setMethod(OrderPayment.PaymentMethod.valueOf(paymentJson.get("method").asText().toUpperCase()));
            }
            OrderPayment savedPayment = (OrderPayment)this.orderPaymentRepository.save(payment);
            this.updateOrderAfterPayment(payment.getOrderId(), true);
            logger.info("Payment verified successfully for order: {}", payment.getOrderId());
            return this.mapToResponse(savedPayment);
        }
        catch (Exception e) {
            logger.error("Error verifying payment: {}", e.getMessage(), e);
            payment.setStatus(OrderPayment.PaymentStatus.FAILED);
            payment.setFailureReason(e.getMessage());
            payment.setFailedAt(LocalDateTime.now());
            this.orderPaymentRepository.save(payment);
            throw new RuntimeException("Payment verification failed: " + e.getMessage());
        }
    }

    private boolean verifySignature(String orderId, String paymentId, String signature) {
        try {
            String payload = orderId + "|" + paymentId;
            String secret = this.paymentsApi.getRazorpayKey();
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(payload.getBytes());
            String generatedSignature = HexFormat.of().formatHex(hash);
            return generatedSignature.equals(signature);
        }
        catch (Exception e) {
            logger.error("Error verifying signature: {}", e.getMessage(), e);
            return false;
        }
    }

    @Transactional
    public void handlePaymentFailure(String razorpayOrderId, String reason, String errorCode) {
        logger.info("Handling payment failure for Razorpay order: {}", razorpayOrderId);
        Optional<OrderPayment> paymentOpt = this.orderPaymentRepository.findByRazorpayOrderId(razorpayOrderId);
        if (paymentOpt.isEmpty()) {
            logger.warn("Payment not found for failed Razorpay order: {}", razorpayOrderId);
            return;
        }
        OrderPayment payment = paymentOpt.get();
        payment.setStatus(OrderPayment.PaymentStatus.FAILED);
        payment.setFailureReason(reason);
        payment.setErrorCode(errorCode);
        payment.setFailedAt(LocalDateTime.now());
        this.orderPaymentRepository.save(payment);
        this.updateOrderAfterPayment(payment.getOrderId(), false);
        logger.info("Payment failure recorded for order: {}", payment.getOrderId());
    }

    private void updateOrderAfterPayment(Long orderId, boolean success) {
        Optional orderOpt = this.orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = (Order)orderOpt.get();
            if (success) {
                order.setPaymentMethod("ONLINE");
                order.setOrderStatus(Order.OrderStatus.PREPARING);
            } else {
                order.setOrderStatus(Order.OrderStatus.CANCELLED);
            }
            this.orderRepository.save(order);
        }
    }

    public OrderPaymentResponse getPaymentByOrderId(Long orderId) {
        logger.info("Fetching payment for order: {}", orderId);
        Optional<OrderPayment> paymentOpt = this.orderPaymentRepository.findByOrderId(orderId);
        if (paymentOpt.isEmpty()) {
            throw new RuntimeException("Payment not found for order: " + orderId);
        }
        return this.mapToResponse(paymentOpt.get());
    }

    public List<OrderPaymentResponse> getUserPaymentHistory(Long userId) {
        logger.info("Fetching payment history for user: {}", userId);
        List<OrderPayment> payments = this.orderPaymentRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return payments.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<OrderPaymentResponse> getSuccessfulPaymentsByUser(Long userId) {
        logger.info("Fetching successful payments for user: {}", userId);
        List<OrderPayment> payments = this.orderPaymentRepository.findSuccessfulPaymentsByUserId(userId);
        return payments.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Transactional
    public OrderPaymentResponse initiateRefund(Long paymentId, String reason) {
        logger.info("Initiating refund for payment: {}", paymentId);
        Optional paymentOpt = this.orderPaymentRepository.findById(paymentId);
        if (paymentOpt.isEmpty()) {
            throw new RuntimeException("Payment not found with ID: " + paymentId);
        }
        OrderPayment payment = (OrderPayment)paymentOpt.get();
        if (payment.getStatus() != OrderPayment.PaymentStatus.SUCCESS) {
            throw new RuntimeException("Only successful payments can be refunded");
        }
        if (payment.getIsRefunded().booleanValue()) {
            throw new RuntimeException("Payment already refunded");
        }
        try {
            String refundResponse = this.paymentsApi.initiateRefund(payment.getRazorpayPaymentId(), payment.getAmount(), reason);
            JsonNode refundJson = this.objectMapper.readTree(refundResponse);
            String refundId = refundJson.get("id").asText();
            payment.setIsRefunded(true);
            payment.setRefundId(refundId);
            payment.setRefundAmount(payment.getAmount());
            payment.setRefundedAt(LocalDateTime.now());
            payment.setStatus(OrderPayment.PaymentStatus.REFUNDED);
            OrderPayment savedPayment = (OrderPayment)this.orderPaymentRepository.save(payment);
            logger.info("Refund initiated successfully for payment: {}", paymentId);
            return this.mapToResponse(savedPayment);
        }
        catch (Exception e) {
            logger.error("Error initiating refund: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initiate refund: " + e.getMessage());
        }
    }

    public Double getTotalAmountPaidByUser(Long userId) {
        Double total = this.orderPaymentRepository.getTotalAmountPaidByUser(userId);
        return total != null ? total : 0.0;
    }

    public Long getSuccessfulPaymentCount(Long userId) {
        Long count = this.orderPaymentRepository.getSuccessfulPaymentCountByUser(userId);
        return count != null ? count : 0L;
    }

    @Transactional
    public void processExpiredPayments() {
        logger.info("Processing expired pending payments");
        LocalDateTime expiryTime = LocalDateTime.now().minusMinutes(30L);
        List<OrderPayment> expiredPayments = this.orderPaymentRepository.findExpiredPendingPayments(expiryTime);
        for (OrderPayment payment : expiredPayments) {
            payment.setStatus(OrderPayment.PaymentStatus.CANCELLED);
            payment.setFailureReason("Payment expired");
            this.orderPaymentRepository.save(payment);
            this.updateOrderAfterPayment(payment.getOrderId(), false);
        }
        logger.info("Processed {} expired payments", expiredPayments.size());
    }

    private OrderPaymentResponse mapToResponse(OrderPayment payment) {
        OrderPaymentResponse response = new OrderPaymentResponse();
        response.setId(payment.getId());
        response.setOrderId(payment.getOrderId());
        response.setUserId(payment.getUserId());
        response.setAmount(payment.getAmount());
        response.setCurrency(payment.getCurrency());
        response.setRazorpayOrderId(payment.getRazorpayOrderId());
        response.setRazorpayPaymentId(payment.getRazorpayPaymentId());
        response.setStatus(payment.getStatus());
        response.setMethod(payment.getMethod() != null ? payment.getMethod().toString() : null);
        response.setCreatedAt(payment.getCreatedAt());
        response.setPaidAt(payment.getPaidAt());
        response.setFailureReason(payment.getFailureReason());
        response.setRazorpayKey(this.paymentsApi.getRazorpayKey());
        response.setCustomerEmail(payment.getCustomerEmail());
        response.setCustomerPhone(payment.getCustomerPhone());
        return response;
    }
}
