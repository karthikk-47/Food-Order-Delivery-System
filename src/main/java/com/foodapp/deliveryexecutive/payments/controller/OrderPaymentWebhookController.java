package com.foodapp.deliveryexecutive.payments.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.deliveryexecutive.payments.service.OrderPaymentService;
import java.util.HashMap;
import java.util.HexFormat;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/webhooks/order-payment"})
public class OrderPaymentWebhookController {
    private static final Logger logger = LoggerFactory.getLogger(OrderPaymentWebhookController.class);
    @Autowired
    private OrderPaymentService orderPaymentService;
    @Value(value="${razorpay.webhook.secret}")
    private String webhookSecret;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<?> handleWebhook(@RequestBody String payload, @RequestHeader(value="X-Razorpay-Signature") String signature) {
        try {
            logger.info("Received order payment webhook");
            if (!this.verifyWebhookSignature(payload, signature)) {
                logger.error("Invalid webhook signature");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
            }
            JsonNode webhookData = this.objectMapper.readTree(payload);
            String event = webhookData.get("event").asText();
            JsonNode paymentData = webhookData.get("payload").get("payment").get("entity");
            logger.info("Processing webhook event: {}", event);
            switch (event) {
                case "payment.authorized": {
                    this.handlePaymentAuthorized(paymentData);
                    break;
                }
                case "payment.captured": {
                    this.handlePaymentCaptured(paymentData);
                    break;
                }
                case "payment.failed": {
                    this.handlePaymentFailed(paymentData);
                    break;
                }
                default: {
                    logger.info("Unhandled webhook event: {}", event);
                }
            }
            HashMap<String, String> response = new HashMap<String, String>();
            response.put("status", "success");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            logger.error("Error processing webhook: {}", e.getMessage(), e);
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    private boolean verifyWebhookSignature(String payload, String signature) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(this.webhookSecret.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(payload.getBytes());
            String generatedSignature = HexFormat.of().formatHex(hash);
            return generatedSignature.equals(signature);
        }
        catch (Exception e) {
            logger.error("Error verifying webhook signature: {}", e.getMessage(), e);
            return false;
        }
    }

    private void handlePaymentAuthorized(JsonNode paymentData) {
        logger.info("Payment authorized: {}", paymentData.get("id").asText());
    }

    private void handlePaymentCaptured(JsonNode paymentData) {
        logger.info("Payment captured: {}", paymentData.get("id").asText());
    }

    private void handlePaymentFailed(JsonNode paymentData) {
        String orderId = paymentData.get("order_id").asText();
        String errorCode = paymentData.has("error_code") ? paymentData.get("error_code").asText() : "UNKNOWN";
        String errorDescription = paymentData.has("error_description") ? paymentData.get("error_description").asText() : "Payment failed";
        logger.info("Payment failed for order: {}, reason: {}", orderId, errorDescription);
        this.orderPaymentService.handlePaymentFailure(orderId, errorDescription, errorCode);
    }
}
