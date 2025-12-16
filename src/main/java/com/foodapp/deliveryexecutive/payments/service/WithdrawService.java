package com.foodapp.deliveryexecutive.payments.service;

import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.payments.dto.PayoutRequest;
import com.foodapp.deliveryexecutive.payments.dto.PayoutResponse;
import com.foodapp.deliveryexecutive.payments.dto.PayoutStatusResponse;
import com.foodapp.deliveryexecutive.payments.dto.WithdrawHistoryResponse;
import com.foodapp.deliveryexecutive.payments.dto.WithdrawRequest;
import com.foodapp.deliveryexecutive.payments.dto.WithdrawResponse;
import com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction;
import com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction.WithdrawStatus;
import com.foodapp.deliveryexecutive.payments.repository.WithdrawRepository;
import com.foodapp.deliveryexecutive.wallet.entity.Wallet;
import com.foodapp.deliveryexecutive.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WithdrawService {

    private final WithdrawRepository withdrawRepository;
    private final WalletRepository walletRepository;
    private final PaymentsApi paymentsApi;

    @Transactional
    public WithdrawResponse processWithdraw(WithdrawRequest request) {
        WithdrawResponse response = new WithdrawResponse();
        try {
            Wallet wallet = walletRepository.findByCustomerId(request.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for customer"));

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
            transaction.setStatus(WithdrawStatus.PENDING);
            transaction = withdrawRepository.save(transaction);

            PayoutRequest payoutRequest = new PayoutRequest();
            payoutRequest.setAccount_number("2323230074952190");
            payoutRequest.setFund_account_id(request.getFundAccountId());
            payoutRequest.setAmount((int) (request.getAmount() * 100.0));
            payoutRequest.setCurrency("INR");
            payoutRequest.setMode("IMPS");
            payoutRequest.setPurpose(transaction.getPurpose());
            payoutRequest.setNarration(transaction.getNarration());
            payoutRequest.setReference_id(transaction.getReferenceId());

            PayoutResponse payoutResponse = paymentsApi.makePayout(payoutRequest);

            if (payoutResponse != null && payoutResponse.getId() != null) {
                transaction.setPayoutId(payoutResponse.getId());
                transaction.setStatus(WithdrawStatus.PROCESSING);
                transaction.setUtr(payoutResponse.getUtr());
                withdrawRepository.save(transaction);

                wallet.setBalance(wallet.getBalance() - request.getAmount());
                walletRepository.save(wallet);

                response.setSuccess(true);
                response.setMessage("Withdrawal initiated successfully");
                response.setPayoutId(payoutResponse.getId());
                response.setStatus(payoutResponse.getStatus());
                response.setAmount(request.getAmount());
                response.setRemainingBalance(wallet.getBalance());
                response.setUtr(payoutResponse.getUtr());
                response.setCreatedAt(Long.valueOf(payoutResponse.getCreated_at()));

                log.info("Withdrawal processed successfully for customer: {}, amount: {}", 
                        request.getCustomerId(), request.getAmount());
            } else {
                transaction.setStatus(WithdrawStatus.FAILED);
                transaction.setFailureReason("Payout API call failed");
                withdrawRepository.save(transaction);

                response.setSuccess(false);
                response.setMessage("Withdrawal failed. Please try again later.");
                log.error("Payout API returned null response for customer: {}", request.getCustomerId());
            }
        } catch (ResourceNotFoundException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            log.error("Resource not found: {}", e.getMessage());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("An error occurred while processing withdrawal");
            log.error("Error processing withdrawal for customer: {}", request.getCustomerId(), e);
        }
        return response;
    }

    public List<WithdrawHistoryResponse> getWithdrawHistory(Long customerId) {
        List<WithdrawTransaction> transactions = withdrawRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);
        return transactions.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());
    }

    public List<WithdrawHistoryResponse> getWithdrawHistoryByStatus(Long customerId, WithdrawStatus status) {
        List<WithdrawTransaction> transactions = withdrawRepository.findByCustomerIdAndStatusOrderByCreatedAtDesc(customerId, status);
        return transactions.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());
    }

    public List<WithdrawHistoryResponse> getWithdrawHistoryByDateRange(Long customerId, LocalDateTime startDate, LocalDateTime endDate) {
        List<WithdrawTransaction> transactions = withdrawRepository.findByCustomerIdAndDateRange(customerId, startDate, endDate);
        return transactions.stream().map(this::mapToHistoryResponse).collect(Collectors.toList());
    }

    @Transactional
    public WithdrawResponse updateWithdrawStatus(String payoutId) {
        WithdrawResponse response = new WithdrawResponse();
        try {
            WithdrawTransaction transaction = withdrawRepository.findByPayoutId(payoutId)
                    .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

            PayoutStatusResponse statusResponse = paymentsApi.getPayoutStatus(payoutId);

            if (statusResponse != null) {
                WithdrawStatus newStatus = mapRazorpayStatus(statusResponse.getStatus());
                transaction.setStatus(newStatus);
                transaction.setUtr(statusResponse.getUtr());

                if (newStatus == WithdrawStatus.FAILED || newStatus == WithdrawStatus.REVERSED) {
                    Wallet wallet = walletRepository.findByCustomerId(transaction.getCustomerId())
                            .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
                    wallet.setBalance(wallet.getBalance() + transaction.getAmount());
                    walletRepository.save(wallet);

                    if (statusResponse.getStatusDetails() != null) {
                        transaction.setFailureReason(statusResponse.getStatusDetails().getDescription());
                    }
                }

                withdrawRepository.save(transaction);

                response.setSuccess(true);
                response.setMessage("Status updated successfully");
                response.setPayoutId(payoutId);
                response.setStatus(statusResponse.getStatus());
                response.setAmount(transaction.getAmount());
                response.setUtr(statusResponse.getUtr());

                log.info("Updated withdrawal status for payout: {}, new status: {}", payoutId, newStatus);
            } else {
                response.setSuccess(false);
                response.setMessage("Failed to fetch payout status");
            }
        } catch (ResourceNotFoundException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            log.error("Resource not found: {}", e.getMessage());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error updating withdrawal status");
            log.error("Error updating withdrawal status for payout: {}", payoutId, e);
        }
        return response;
    }

    @Transactional
    public WithdrawResponse cancelWithdraw(String payoutId) {
        WithdrawResponse response = new WithdrawResponse();
        try {
            WithdrawTransaction transaction = withdrawRepository.findByPayoutId(payoutId)
                    .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

            if (transaction.getStatus() != WithdrawStatus.PENDING && 
                transaction.getStatus() != WithdrawStatus.PROCESSING) {
                response.setSuccess(false);
                response.setMessage("Cannot cancel transaction in current status: " + transaction.getStatus());
                return response;
            }

            String cancelResponse = paymentsApi.cancelPayout(payoutId);

            if (cancelResponse != null) {
                transaction.setStatus(WithdrawStatus.CANCELLED);
                withdrawRepository.save(transaction);

                Wallet wallet = walletRepository.findByCustomerId(transaction.getCustomerId())
                        .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
                wallet.setBalance(wallet.getBalance() + transaction.getAmount());
                walletRepository.save(wallet);

                response.setSuccess(true);
                response.setMessage("Withdrawal cancelled successfully");
                response.setPayoutId(payoutId);
                response.setStatus("cancelled");
                response.setAmount(transaction.getAmount());
                response.setRemainingBalance(wallet.getBalance());

                log.info("Cancelled withdrawal for payout: {}", payoutId);
            } else {
                response.setSuccess(false);
                response.setMessage("Failed to cancel withdrawal");
            }
        } catch (ResourceNotFoundException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            log.error("Resource not found: {}", e.getMessage());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error cancelling withdrawal");
            log.error("Error cancelling withdrawal for payout: {}", payoutId, e);
        }
        return response;
    }

    public Double getTotalWithdrawn(Long customerId) {
        Double total = withdrawRepository.getTotalWithdrawnAmount(customerId, WithdrawStatus.PROCESSED);
        return total != null ? total : 0.0;
    }

    public Long getWithdrawCount(Long customerId, WithdrawStatus status) {
        return withdrawRepository.countByCustomerIdAndStatus(customerId, status);
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

    private WithdrawStatus mapRazorpayStatus(String razorpayStatus) {
        return switch (razorpayStatus.toLowerCase()) {
            case "pending" -> WithdrawStatus.PENDING;
            case "processing", "queued" -> WithdrawStatus.PROCESSING;
            case "processed" -> WithdrawStatus.PROCESSED;
            case "reversed" -> WithdrawStatus.REVERSED;
            case "failed", "rejected" -> WithdrawStatus.FAILED;
            case "cancelled" -> WithdrawStatus.CANCELLED;
            default -> WithdrawStatus.PENDING;
        };
    }
}
