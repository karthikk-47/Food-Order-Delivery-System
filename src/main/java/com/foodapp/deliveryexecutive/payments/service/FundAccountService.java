package com.foodapp.deliveryexecutive.payments.service;

import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.payments.dto.CreateFundAccountRequest;
import com.foodapp.deliveryexecutive.payments.dto.CreateFundAccountResponse;
import com.foodapp.deliveryexecutive.payments.entity.FundAccountEntity;
import com.foodapp.deliveryexecutive.payments.repository.FundAccountRepository;
import com.foodapp.deliveryexecutive.payments.service.PaymentsApi;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FundAccountService {
    private static final Logger logger = LoggerFactory.getLogger(FundAccountService.class);
    @Autowired
    private FundAccountRepository fundAccountRepository;
    @Autowired
    private PaymentsApi paymentsApi;

    @Transactional
    public CreateFundAccountResponse createAndSaveFundAccount(CreateFundAccountRequest request) throws Exception {
        CreateFundAccountResponse response = this.paymentsApi.createFundAccount(request);
        if (response != null && response.getId() != null) {
            FundAccountEntity fundAccount = new FundAccountEntity();
            fundAccount.setId(response.getId());
            fundAccount.setEntity(response.getEntity());
            fundAccount.setFund_account(response.getFund_account());
            fundAccount.setActive(response.isActive());
            fundAccount.setCreated_at(response.getCreated_at());
            this.fundAccountRepository.save(fundAccount);
            logger.info("Fund account saved successfully with ID: {}", fundAccount.getId());
        }
        return response;
    }

    public Optional<FundAccountEntity> getFundAccountById(String id) {
        return this.fundAccountRepository.findById(id);
    }

    public List<FundAccountEntity> getFundAccountsByStatus(String status) {
        return this.fundAccountRepository.findByStatus(status);
    }

    public List<FundAccountEntity> getActiveFundAccounts() {
        return this.fundAccountRepository.findAllActive();
    }

    public List<FundAccountEntity> getAllFundAccounts() {
        return this.fundAccountRepository.findAll();
    }

    public Optional<FundAccountEntity> getFundAccountByUtr(String utr) {
        return this.fundAccountRepository.findByUtr(utr);
    }

    public Long getActiveFundAccountCount() {
        return this.fundAccountRepository.countActive();
    }

    public Long getFundAccountCountByStatus(String status) {
        return this.fundAccountRepository.countByStatus(status);
    }

    @Transactional
    public void deactivateFundAccount(String id) {
        FundAccountEntity fundAccount = (FundAccountEntity)this.fundAccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fund account not found with ID: " + id));
        fundAccount.setActive(false);
        this.fundAccountRepository.save(fundAccount);
        logger.info("Fund account deactivated: {}", id);
    }

    @Transactional
    public void updateFundAccountStatus(String id, String status) {
        FundAccountEntity fundAccount = (FundAccountEntity)this.fundAccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fund account not found with ID: " + id));
        fundAccount.setStatus(status);
        this.fundAccountRepository.save(fundAccount);
        logger.info("Fund account status updated: {} -> {}", id, status);
    }
}
