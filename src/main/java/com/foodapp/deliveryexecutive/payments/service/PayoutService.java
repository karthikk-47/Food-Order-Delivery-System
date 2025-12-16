package com.foodapp.deliveryexecutive.payments.service;

import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.payments.dto.PayoutRequest;
import com.foodapp.deliveryexecutive.payments.dto.PayoutResponse;
import com.foodapp.deliveryexecutive.payments.entity.Payout;
import com.foodapp.deliveryexecutive.payments.repository.PayoutRepository;
import com.foodapp.deliveryexecutive.payments.service.PaymentsApi;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayoutService {
    private static final Logger logger = LoggerFactory.getLogger(PayoutService.class);
    @Autowired
    private PayoutRepository payoutRepository;
    @Autowired
    private PaymentsApi paymentsApi;

    @Transactional
    public PayoutResponse createAndSavePayout(PayoutRequest request) throws Exception {
        PayoutResponse response = this.paymentsApi.makePayout(request);
        if (response != null && response.getId() != null) {
            Payout payout = new Payout();
            payout.setId(response.getId());
            payout.setEntity(response.getEntity());
            payout.setFundAccountId(response.getFund_account_id());
            payout.setAmount(response.getAmount());
            payout.setCurrency(response.getCurrency());
            payout.setNotes(response.getNotes());
            payout.setFees(response.getFees());
            payout.setTax(response.getTax());
            payout.setStatus(response.getStatus());
            payout.setUtr(response.getUtr());
            payout.setMode(response.getMode());
            payout.setPurpose(response.getPurpose());
            payout.setReferenceId(response.getReference_id());
            payout.setNarration(response.getNarration());
            payout.setCreatedAt(response.getCreated_at());
            this.payoutRepository.save(payout);
            logger.info("Payout saved successfully with ID: {}", payout.getId());
        }
        return response;
    }

    public Optional<Payout> getPayoutById(String id) {
        return this.payoutRepository.findById(id);
    }

    public Optional<Payout> getPayoutByReferenceId(String referenceId) {
        return this.payoutRepository.findByReferenceId(referenceId);
    }

    public Optional<Payout> getPayoutByUtr(String utr) {
        return this.payoutRepository.findByUtr(utr);
    }

    public List<Payout> getPayoutsByFundAccountId(String fundAccountId) {
        return this.payoutRepository.findByFundAccountIdOrderByCreatedAtDesc(fundAccountId);
    }

    public List<Payout> getPayoutsByStatus(String status) {
        return this.payoutRepository.findByStatus(status);
    }

    public List<Payout> getPendingPayouts() {
        return this.payoutRepository.findPendingPayouts();
    }

    public List<Payout> getFailedPayouts() {
        return this.payoutRepository.findFailedPayouts();
    }

    public List<Payout> getPayoutsByDateRange(int startTime, int endTime) {
        return this.payoutRepository.findByDateRange(startTime, endTime);
    }

    public Long getTotalProcessedAmount(String fundAccountId) {
        Long total = this.payoutRepository.getTotalProcessedAmount(fundAccountId);
        return total != null ? total : 0L;
    }

    public Long getPayoutCountByStatus(String status) {
        return this.payoutRepository.countByStatus(status);
    }

    @Transactional
    public void updatePayoutStatus(String payoutId, String status) {
        Payout payout = (Payout)this.payoutRepository.findById(payoutId).orElseThrow(() -> new ResourceNotFoundException("Payout not found with ID: " + payoutId));
        payout.setStatus(status);
        this.payoutRepository.save(payout);
        logger.info("Payout status updated: {} -> {}", payoutId, status);
    }

    @Transactional
    public void updatePayoutUtr(String payoutId, String utr) {
        Payout payout = (Payout)this.payoutRepository.findById(payoutId).orElseThrow(() -> new ResourceNotFoundException("Payout not found with ID: " + payoutId));
        payout.setUtr(utr);
        this.payoutRepository.save(payout);
        logger.info("Payout UTR updated: {} -> {}", payoutId, utr);
    }

    public List<Payout> getAllPayouts() {
        return this.payoutRepository.findAll();
    }
}
