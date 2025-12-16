package com.foodapp.deliveryexecutive.profile.service;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.order.service.OrderService;
import com.foodapp.deliveryexecutive.payments.dto.CreateContactRequest;
import com.foodapp.deliveryexecutive.payments.dto.CreateContactResponse;
import com.foodapp.deliveryexecutive.payments.dto.CreateFundAccountRequest;
import com.foodapp.deliveryexecutive.payments.dto.CreateFundAccountResponse;
import com.foodapp.deliveryexecutive.payments.entity.BankAccount;
import com.foodapp.deliveryexecutive.payments.service.ContactService;
import com.foodapp.deliveryexecutive.payments.service.FundAccountService;
import com.foodapp.deliveryexecutive.profile.dto.AddBankAccountRequest;
import com.foodapp.deliveryexecutive.profile.dto.BankAccountDTO;
import com.foodapp.deliveryexecutive.profile.dto.ExecutiveProfileDTO;
import com.foodapp.deliveryexecutive.profile.dto.UpdateProfileRequest;
import com.foodapp.deliveryexecutive.profile.entity.ExecutiveBankAccount;
import com.foodapp.deliveryexecutive.profile.repository.ExecutiveBankAccountRepository;
import com.foodapp.deliveryexecutive.rating.service.RatingService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExecutiveProfileService {
    private static final Logger logger = LoggerFactory.getLogger(ExecutiveProfileService.class);
    @Autowired
    private DeliveryExecutiveRepository executiveRepository;
    @Autowired
    private ExecutiveBankAccountRepository bankAccountRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private FundAccountService fundAccountService;

    public ExecutiveProfileDTO getProfile(Long executiveId) {
        DeliveryExecutive executive = (DeliveryExecutive)this.executiveRepository.findById(executiveId).orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
        ExecutiveProfileDTO profile = new ExecutiveProfileDTO();
        profile.setId(executive.getId());
        profile.setName(executive.getName());
        profile.setMobile(executive.getMobile());
        profile.setAadharNo(executive.getAadharNo());
        profile.setLicenseNo(executive.getLicenseNo());
        profile.setOnline(executive.isOnline());
        profile.setAverageRating(this.ratingService.getAverageRating(executiveId, Actor.Role.DELIVERYEXECUTIVE));
        profile.setTotalDeliveries(this.orderService.getDeliveredOrderCount(executiveId));
        profile.setTotalEarnings(this.orderService.getTotalEarnings(executiveId));
        logger.info("Retrieved profile for executive: {}", executiveId);
        return profile;
    }

    @Transactional
    public ExecutiveProfileDTO updateProfile(Long executiveId, UpdateProfileRequest request) {
        DeliveryExecutive executive = (DeliveryExecutive)this.executiveRepository.findById(executiveId).orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
        if (request.getName() != null) {
            executive.setName(request.getName());
        }
        this.executiveRepository.save(executive);
        logger.info("Updated profile for executive: {}", executiveId);
        return this.getProfile(executiveId);
    }

    @Transactional
    public BankAccountDTO addBankAccount(Long executiveId, AddBankAccountRequest request) {
        if (this.bankAccountRepository.existsByExecutiveIdAndAccountNumber(executiveId, request.getAccountNumber())) {
            throw new RuntimeException("Bank account already exists");
        }
        if (request.isPrimary()) {
            this.bankAccountRepository.clearPrimaryForExecutive(executiveId);
        }
        ExecutiveBankAccount bankAccount = new ExecutiveBankAccount();
        bankAccount.setExecutiveId(executiveId);
        bankAccount.setAccountHolderName(request.getAccountHolderName());
        bankAccount.setAccountNumber(request.getAccountNumber());
        bankAccount.setIfscCode(request.getIfscCode());
        bankAccount.setBankName(request.getBankName());
        bankAccount.setBranchName(request.getBranchName());
        bankAccount.setAccountType(request.getAccountType());
        bankAccount.setPrimary(request.isPrimary());
        bankAccount.setVerified(false);
        bankAccount = (ExecutiveBankAccount)this.bankAccountRepository.save(bankAccount);
        try {
            DeliveryExecutive executive = (DeliveryExecutive)this.executiveRepository.findById(executiveId).orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
            CreateContactRequest contactRequest = new CreateContactRequest();
            contactRequest.setName(executive.getName());
            contactRequest.setContact(executive.getMobile());
            contactRequest.setType("customer");
            contactRequest.setReference_id("exec_" + executiveId);
            CreateContactResponse contactResponse = this.contactService.createAndSaveContact(contactRequest);
            if (contactResponse != null) {
                bankAccount.setRazorpayContactId(contactResponse.getId());
                CreateFundAccountRequest fundRequest = new CreateFundAccountRequest();
                fundRequest.setContact_id(contactResponse.getId());
                fundRequest.setAccount_type("bank_account");
                BankAccount razorpayBankAccount = new BankAccount();
                razorpayBankAccount.setName(request.getAccountHolderName());
                razorpayBankAccount.setIfsc(request.getIfscCode());
                razorpayBankAccount.setAccount_number(request.getAccountNumber());
                fundRequest.setBank_account(razorpayBankAccount);
                CreateFundAccountResponse fundResponse = this.fundAccountService.createAndSaveFundAccount(fundRequest);
                if (fundResponse != null) {
                    bankAccount.setRazorpayFundAccountId(fundResponse.getId());
                    bankAccount.setVerified(true);
                }
                this.bankAccountRepository.save(bankAccount);
            }
        }
        catch (Exception e) {
            logger.error("Failed to create Razorpay account for executive: {}", executiveId, e);
        }
        logger.info("Added bank account for executive: {}", executiveId);
        return this.mapToBankAccountDTO(bankAccount);
    }

    public List<BankAccountDTO> getBankAccounts(Long executiveId) {
        return this.bankAccountRepository.findByExecutiveId(executiveId).stream().map(this::mapToBankAccountDTO).collect(Collectors.toList());
    }

    public BankAccountDTO getPrimaryBankAccount(Long executiveId) {
        return this.bankAccountRepository.findByExecutiveIdAndIsPrimary(executiveId, true).map(this::mapToBankAccountDTO).orElse(null);
    }

    @Transactional
    public BankAccountDTO setPrimaryBankAccount(Long executiveId, Long bankAccountId) {
        ExecutiveBankAccount bankAccount = this.bankAccountRepository.findByExecutiveIdAndId(executiveId, bankAccountId).orElseThrow(() -> new ResourceNotFoundException("Bank account not found"));
        this.bankAccountRepository.clearPrimaryForExecutive(executiveId);
        bankAccount.setPrimary(true);
        this.bankAccountRepository.save(bankAccount);
        logger.info("Set primary bank account {} for executive: {}", bankAccountId, executiveId);
        return this.mapToBankAccountDTO(bankAccount);
    }

    @Transactional
    public void deleteBankAccount(Long executiveId, Long bankAccountId) {
        ExecutiveBankAccount bankAccount = this.bankAccountRepository.findByExecutiveIdAndId(executiveId, bankAccountId).orElseThrow(() -> new ResourceNotFoundException("Bank account not found"));
        if (bankAccount.isPrimary()) {
            throw new RuntimeException("Cannot delete primary bank account. Set another account as primary first.");
        }
        this.bankAccountRepository.delete(bankAccount);
        logger.info("Deleted bank account {} for executive: {}", bankAccountId, executiveId);
    }

    @Transactional
    public void changePassword(Long executiveId, String oldPassword, String newPassword) {
        DeliveryExecutive executive = (DeliveryExecutive)this.executiveRepository.findById(executiveId).orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
        if (!executive.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Invalid old password");
        }
        executive.setPassword(newPassword);
        this.executiveRepository.save(executive);
        logger.info("Password changed for executive: {}", executiveId);
    }

    private BankAccountDTO mapToBankAccountDTO(ExecutiveBankAccount account) {
        BankAccountDTO dto = new BankAccountDTO();
        dto.setId(account.getId());
        dto.setExecutiveId(account.getExecutiveId());
        dto.setAccountHolderName(account.getAccountHolderName());
        dto.setAccountNumber(this.maskAccountNumber(account.getAccountNumber()));
        dto.setIfscCode(account.getIfscCode());
        dto.setBankName(account.getBankName());
        dto.setBranchName(account.getBranchName());
        dto.setAccountType(account.getAccountType());
        dto.setPrimary(account.isPrimary());
        dto.setVerified(account.isVerified());
        dto.setRazorpayContactId(account.getRazorpayContactId());
        dto.setRazorpayFundAccountId(account.getRazorpayFundAccountId());
        return dto;
    }

    private String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) {
            return accountNumber;
        }
        int visibleDigits = 4;
        String masked = "*".repeat(accountNumber.length() - visibleDigits);
        return masked + accountNumber.substring(accountNumber.length() - visibleDigits);
    }
}
