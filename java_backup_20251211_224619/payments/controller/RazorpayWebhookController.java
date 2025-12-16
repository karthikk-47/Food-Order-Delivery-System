/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.transaction.annotation.Transactional
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestHeader
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.payments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.deliveryexecutive.payments.dto.RazorpayWebhookEvent;
import com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction;
import com.foodapp.deliveryexecutive.payments.repository.WithdrawRepository;
import com.foodapp.deliveryexecutive.wallet.entity.Wallet;
import com.foodapp.deliveryexecutive.wallet.repository.WalletRepository;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/webhook/razorpay"})
public class RazorpayWebhookController {
    private static final Logger logger = LoggerFactory.getLogger(RazorpayWebhookController.class);
    @Value(value="${razorpay.webhook.secret}")
    private String webhookSecret;
    @Autowired
    private WithdrawRepository withdrawRepository;
    @Autowired
    private WalletRepository walletRepository;

    @PostMapping(value={"/payout"})
    @Transactional
    public ResponseEntity<String> handlePayoutWebhook(@RequestBody String payload, @RequestHeader(value="X-Razorpay-Signature") String signature) {
        try {
            if (!this.verifySignature(payload, signature)) {
                logger.warn("Invalid webhook signature");
                return ResponseEntity.status((HttpStatusCode)HttpStatus.UNAUTHORIZED).body((Object)"Invalid signature");
            }
            ObjectMapper mapper = new ObjectMapper();
            RazorpayWebhookEvent event = (RazorpayWebhookEvent)mapper.readValue(payload, RazorpayWebhookEvent.class);
            String eventType = event.getEvent();
            RazorpayWebhookEvent.PayoutPayload.PayoutEntity payout = event.getPayload().getPayout();
            logger.info("Received webhook event: {} for payout: {}", (Object)eventType, (Object)payout.getId());
            WithdrawTransaction transaction = this.withdrawRepository.findByPayoutId(payout.getId()).orElse(null);
            if (transaction == null) {
                logger.warn("Transaction not found for payout ID: {}", (Object)payout.getId());
                return ResponseEntity.ok((Object)"Transaction not found");
            }
            switch (eventType) {
                case "payout.processed": {
                    transaction.setStatus(WithdrawTransaction.WithdrawStatus.PROCESSED);
                    transaction.setUtr(payout.getUtr());
                    logger.info("Payout processed: {}", (Object)payout.getId());
                    break;
                }
                case "payout.failed": 
                case "payout.rejected": {
                    transaction.setStatus(WithdrawTransaction.WithdrawStatus.FAILED);
                    if (payout.getStatusDetails() != null) {
                        transaction.setFailureReason(payout.getStatusDetails().getDescription());
                    }
                    this.refundToWallet(transaction);
                    logger.info("Payout failed: {}, refunded to wallet", (Object)payout.getId());
                    break;
                }
                case "payout.reversed": {
                    transaction.setStatus(WithdrawTransaction.WithdrawStatus.REVERSED);
                    transaction.setUtr(payout.getUtr());
                    this.refundToWallet(transaction);
                    logger.info("Payout reversed: {}, refunded to wallet", (Object)payout.getId());
                    break;
                }
                case "payout.cancelled": {
                    transaction.setStatus(WithdrawTransaction.WithdrawStatus.CANCELLED);
                    this.refundToWallet(transaction);
                    logger.info("Payout cancelled: {}, refunded to wallet", (Object)payout.getId());
                    break;
                }
                case "payout.queued": 
                case "payout.processing": {
                    transaction.setStatus(WithdrawTransaction.WithdrawStatus.PROCESSING);
                    logger.info("Payout processing: {}", (Object)payout.getId());
                    break;
                }
                default: {
                    logger.info("Unhandled event type: {}", (Object)eventType);
                }
            }
            this.withdrawRepository.save(transaction);
            return ResponseEntity.ok((Object)"Webhook processed successfully");
        }
        catch (Exception e) {
            logger.error("Error processing webhook", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body((Object)"Error processing webhook");
        }
    }

    private boolean verifySignature(String payload, String signature) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(this.webhookSecret.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(payload.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString().equals(signature);
        }
        catch (Exception e) {
            logger.error("Error verifying signature", (Throwable)e);
            return false;
        }
    }

    private void refundToWallet(WithdrawTransaction transaction) {
        try {
            Wallet wallet = this.walletRepository.findByCustomerId(transaction.getCustomerId()).orElse(null);
            if (wallet != null) {
                wallet.setBalance(wallet.getBalance() + transaction.getAmount());
                this.walletRepository.save(wallet);
                logger.info("Refunded {} to wallet for customer: {}", (Object)transaction.getAmount(), (Object)transaction.getCustomerId());
            }
        }
        catch (Exception e) {
            logger.error("Error refunding to wallet for transaction: {}", (Object)transaction.getId(), (Object)e);
        }
    }
}
