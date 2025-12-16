/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.payments.service;

import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.payments.dto.PayoutRequest;
import com.foodapp.deliveryexecutive.payments.dto.PayoutResponse;
import com.foodapp.deliveryexecutive.payments.dto.PayoutStatusResponse;
import com.foodapp.deliveryexecutive.payments.dto.WithdrawHistoryResponse;
import com.foodapp.deliveryexecutive.payments.dto.WithdrawRequest;
import com.foodapp.deliveryexecutive.payments.dto.WithdrawResponse;
import com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction;
import com.foodapp.deliveryexecutive.payments.repository.WithdrawRepository;
import com.foodapp.deliveryexecutive.payments.service.PaymentsApi;
import com.foodapp.deliveryexecutive.wallet.entity.Wallet;
import com.foodapp.deliveryexecutive.wallet.repository.WalletRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WithdrawService {
    private static final Logger logger = LoggerFactory.getLogger(WithdrawService.class);
    @Autowired
    private WithdrawRepository withdrawRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private PaymentsApi paymentsApi;

    @Transactional
    public WithdrawResponse processWithdraw(WithdrawRequest request) {
        WithdrawResponse response = new WithdrawResponse();
        try {
            Wallet wallet = this.walletRepository.findByCustomerId(request.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Wallet not found for customer"));
            if (wallet.getBalance() < request.getAmount()) {
                response.setSuccess(false);
                response.setMessage("Insufficient balance");
                return response;
            }
            WithdrawTransaction transaction = new WithdrawTransaction();
            transaction.setCustomerId(request.getCustomerId());
            transaction.setAmount(request.getAmount());
            transaction.setFundAccountId(request.getFundAccountId());
            transaction.setPurpose(request.getPurpose() != null ? request.getPurpose() : "payout");
            transaction.setNarration(request.getNarration() != null ? request.getNarration() : "Wallet withdrawal");
            transaction.setReferenceId(request.getReferenceId() != null ? request.getReferenceId() : UUID.randomUUID().toString());
            transaction.setStatus(WithdrawTransaction.WithdrawStatus.PENDING);
            transaction = (WithdrawTransaction)this.withdrawRepository.save(transaction);
            PayoutRequest payoutRequest = new PayoutRequest();
            payoutRequest.setAccount_number("2323230074952190");
            payoutRequest.setFund_account_id(request.getFundAccountId());
            payoutRequest.setAmount((int)(request.getAmount() * 100.0));
            payoutRequest.setCurrency("INR");
            payoutRequest.setMode("IMPS");
            payoutRequest.setPurpose(transaction.getPurpose());
            payoutRequest.setNarration(transaction.getNarration());
            payoutRequest.setReference_id(transaction.getReferenceId());
            PayoutResponse payoutResponse = this.paymentsApi.makePayout(payoutRequest);
            if (payoutResponse != null && payoutResponse.getId() != null) {
                transaction.setPayoutId(payoutResponse.getId());
                transaction.setStatus(WithdrawTransaction.WithdrawStatus.PROCESSING);
                transaction.setUtr(payoutResponse.getUtr());
                this.withdrawRepository.save(transaction);
                wallet.setBalance(wallet.getBalance() - request.getAmount());
                this.walletRepository.save(wallet);
                response.setSuccess(true);
                response.setMessage("Withdrawal initiated successfully");
                response.setPayoutId(payoutResponse.getId());
                response.setStatus(payoutResponse.getStatus());
                response.setAmount(request.getAmount());
                response.setRemainingBalance(wallet.getBalance());
                response.setUtr(payoutResponse.getUtr());
                response.setCreatedAt(Long.valueOf(payoutResponse.getCreated_at()));
                logger.info("Withdrawal processed successfully for customer: {}, amount: {}", (Object)request.getCustomerId(), (Object)request.getAmount());
            } else {
                transaction.setStatus(WithdrawTransaction.WithdrawStatus.FAILED);
                transaction.setFailureReason("Payout API call failed");
                this.withdrawRepository.save(transaction);
                response.setSuccess(false);
                response.setMessage("Withdrawal failed. Please try again later.");
                logger.error("Payout API returned null response for customer: {}", (Object)request.getCustomerId());
            }
        }
        catch (ResourceNotFoundException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            logger.error("Resource not found: {}", (Object)e.getMessage());
        }
        catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("An error occurred while processing withdrawal");
            logger.error("Error processing withdrawal for customer: {}", (Object)request.getCustomerId(), (Object)e);
        }
        return response;
    }

    public List<WithdrawHistoryResponse> getWithdrawHistory(Long customerId) {
        List<WithdrawTransaction> transactions = this.withdrawRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);
        return transactions.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());
    }

    public List<WithdrawHistoryResponse> getWithdrawHistoryByStatus(Long customerId, WithdrawTransaction.WithdrawStatus status) {
        List<WithdrawTransaction> transactions = this.withdrawRepository.findByCustomerIdAndStatusOrderByCreatedAtDesc(customerId, status);
        return transactions.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());
    }

    public List<WithdrawHistoryResponse> getWithdrawHistoryByDateRange(Long customerId, LocalDateTime startDate, LocalDateTime endDate) {
        List<WithdrawTransaction> transactions = this.withdrawRepository.findByCustomerIdAndDateRange(customerId, startDate, endDate);
        return transactions.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());
    }

    @Transactional
    public WithdrawResponse updateWithdrawStatus(String payoutId) {
        WithdrawResponse response = new WithdrawResponse();
        try {
            WithdrawTransaction transaction = this.withdrawRepository.findByPayoutId(payoutId).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
            PayoutStatusResponse statusResponse = this.paymentsApi.getPayoutStatus(payoutId);
            if (statusResponse != null) {
                WithdrawTransaction.WithdrawStatus newStatus = this.mapRazorpayStatus(statusResponse.getStatus());
                transaction.setStatus(newStatus);
                transaction.setUtr(statusResponse.getUtr());
                if (newStatus == WithdrawTransaction.WithdrawStatus.FAILED || newStatus == WithdrawTransaction.WithdrawStatus.REVERSED) {
                    Wallet wallet = this.walletRepository.findByCustomerId(transaction.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
                    wallet.setBalance(wallet.getBalance() + transaction.getAmount());
                    this.walletRepository.save(wallet);
                    if (statusResponse.getStatusDetails() != null) {
                        transaction.setFailureReason(statusResponse.getStatusDetails().getDescription());
                    }
                }
                this.withdrawRepository.save(transaction);
                response.setSuccess(true);
                response.setMessage("Status updated successfully");
                response.setPayoutId(payoutId);
                response.setStatus(statusResponse.getStatus());
                response.setAmount(transaction.getAmount());
                response.setUtr(statusResponse.getUtr());
                logger.info("Updated withdrawal status for payout: {}, new status: {}", (Object)payoutId, (Object)newStatus);
            } else {
                response.setSuccess(false);
                response.setMessage("Failed to fetch payout status");
            }
        }
        catch (ResourceNotFoundException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            logger.error("Resource not found: {}", (Object)e.getMessage());
        }
        catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error updating withdrawal status");
            logger.error("Error updating withdrawal status for payout: {}", (Object)payoutId, (Object)e);
        }
        return response;
    }

    @Transactional
    public WithdrawResponse cancelWithdraw(String payoutId) {
        WithdrawResponse response = new WithdrawResponse();
        try {
            WithdrawTransaction transaction = this.withdrawRepository.findByPayoutId(payoutId).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
            if (transaction.getStatus() != WithdrawTransaction.WithdrawStatus.PENDING && transaction.getStatus() != WithdrawTransaction.WithdrawStatus.PROCESSING) {
                response.setSuccess(false);
                response.setMessage("Cannot cancel transaction in current status: " + String.valueOf((Object)transaction.getStatus()));
                return response;
            }
            String cancelResponse = this.paymentsApi.cancelPayout(payoutId);
            if (cancelResponse != null) {
                transaction.setStatus(WithdrawTransaction.WithdrawStatus.CANCELLED);
                this.withdrawRepository.save(transaction);
                Wallet wallet = this.walletRepository.findByCustomerId(transaction.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
                wallet.setBalance(wallet.getBalance() + transaction.getAmount());
                this.walletRepository.save(wallet);
                response.setSuccess(true);
                response.setMessage("Withdrawal cancelled successfully");
                response.setPayoutId(payoutId);
                response.setStatus("cancelled");
                response.setAmount(transaction.getAmount());
                response.setRemainingBalance(wallet.getBalance());
                logger.info("Cancelled withdrawal for payout: {}", (Object)payoutId);
            } else {
                response.setSuccess(false);
                response.setMessage("Failed to cancel withdrawal");
            }
        }
        catch (ResourceNotFoundException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            logger.error("Resource not found: {}", (Object)e.getMessage());
        }
        catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error cancelling withdrawal");
            logger.error("Error cancelling withdrawal for payout: {}", (Object)payoutId, (Object)e);
        }
        return response;
    }

    public Double getTotalWithdrawn(Long customerId) {
        Double total = this.withdrawRepository.getTotalWithdrawnAmount(customerId, WithdrawTransaction.WithdrawStatus.PROCESSED);
        return total != null ? total : 0.0;
    }

    public Long getWithdrawCount(Long customerId, WithdrawTransaction.WithdrawStatus status) {
        return this.withdrawRepository.countByCustomerIdAndStatus(customerId, status);
    }

    private WithdrawHistoryResponse mapToHistoryResponse(WithdrawTransaction transaction) {
        WithdrawHistoryResponse response = new WithdrawHistoryResponse();
        response.setId(transaction.getId());
        response.setCustomerId(transaction.getCustomerId());
        response.setAmount(transaction.getAmount());
        response.setStatus(transaction.getStatus().name());
        response.setPayoutId(transaction.getPayoutId());
        response.setFundAccountId(transaction.getFundAccountId());
        response.setUtr(transaction.getUtr());
        response.setPurpose(transaction.getPurpose());
        response.setNarration(transaction.getNarration());
        response.setCreatedAt(transaction.getCreatedAt());
        response.setUpdatedAt(transaction.getUpdatedAt());
        return response;
    }

    private WithdrawTransaction.WithdrawStatus mapRazorpayStatus(String razorpayStatus) {
        switch (razorpayStatus.toLowerCase()) {
            case "pending": {
                return WithdrawTransaction.WithdrawStatus.PENDING;
            }
            case "processing": 
            case "queued": {
                return WithdrawTransaction.WithdrawStatus.PROCESSING;
            }
            case "processed": {
                return WithdrawTransaction.WithdrawStatus.PROCESSED;
            }
            case "reversed": {
                return WithdrawTransaction.WithdrawStatus.REVERSED;
            }
            case "failed": 
            case "rejected": {
                return WithdrawTransaction.WithdrawStatus.FAILED;
            }
            case "cancelled": {
                return WithdrawTransaction.WithdrawStatus.CANCELLED;
            }
        }
        return WithdrawTransaction.WithdrawStatus.PENDING;
    }
}
