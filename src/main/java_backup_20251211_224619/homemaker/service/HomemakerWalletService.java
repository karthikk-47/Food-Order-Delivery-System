/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.homemaker.service;

import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerWalletDTO;
import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerWithdrawalDTO;
import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWallet;
import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWithdrawal;
import com.foodapp.deliveryexecutive.homemaker.repository.HomemakerWalletRepository;
import com.foodapp.deliveryexecutive.homemaker.repository.HomemakerWithdrawalRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HomemakerWalletService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(HomemakerWalletService.class);
    private final HomemakerWalletRepository homemakerWalletRepository;
    private final HomemakerWithdrawalRepository homemakerWithdrawalRepository;
    private static final Double MINIMUM_WITHDRAWAL_AMOUNT = 500.0;
    private static final Double MAXIMUM_WITHDRAWAL_AMOUNT = 100000.0;

    public HomemakerWalletDTO getWallet(Long homemakerId) {
        log.info("Fetching wallet for homemaker: {}", (Object)homemakerId);
        HomemakerWallet wallet = this.homemakerWalletRepository.findByHomemakerId(homemakerId).orElseGet(() -> this.createWallet(homemakerId));
        return this.convertToDTO(wallet);
    }

    public HomemakerWallet createWallet(Long homemakerId) {
        log.info("Creating wallet for homemaker: {}", (Object)homemakerId);
        if (this.homemakerWalletRepository.existsByHomemakerId(homemakerId)) {
            throw new RuntimeException("Wallet already exists for homemaker: " + homemakerId);
        }
        HomemakerWallet wallet = new HomemakerWallet();
        wallet.setHomemakerId(homemakerId);
        wallet.setBalance(0.0);
        wallet.setTotalEarnings(0.0);
        wallet.setTotalWithdrawn(0.0);
        HomemakerWallet savedWallet = (HomemakerWallet)this.homemakerWalletRepository.save(wallet);
        log.info("Wallet created for homemaker: {}", (Object)homemakerId);
        return savedWallet;
    }

    public HomemakerWalletDTO addBalance(Long homemakerId, Double amount) {
        log.info("Adding \u20b9{} to wallet for homemaker: {}", (Object)amount, (Object)homemakerId);
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        HomemakerWallet wallet = this.homemakerWalletRepository.findByHomemakerId(homemakerId).orElseGet(() -> this.createWallet(homemakerId));
        wallet.setBalance(wallet.getBalance() + amount);
        wallet.setTotalEarnings(wallet.getTotalEarnings() + amount);
        HomemakerWallet updatedWallet = (HomemakerWallet)this.homemakerWalletRepository.save(wallet);
        log.info("Balance updated. New balance: \u20b9{}", (Object)updatedWallet.getBalance());
        return this.convertToDTO(updatedWallet);
    }

    public HomemakerWithdrawalDTO requestWithdrawal(Long homemakerId, HomemakerWithdrawalDTO withdrawalDTO) {
        log.info("Processing withdrawal request for homemaker: {}, amount: \u20b9{}", (Object)homemakerId, (Object)withdrawalDTO.getAmount());
        if (withdrawalDTO.getAmount() < MINIMUM_WITHDRAWAL_AMOUNT) {
            throw new IllegalArgumentException("Minimum withdrawal amount is \u20b9" + MINIMUM_WITHDRAWAL_AMOUNT);
        }
        if (withdrawalDTO.getAmount() > MAXIMUM_WITHDRAWAL_AMOUNT) {
            throw new IllegalArgumentException("Maximum withdrawal amount is \u20b9" + MAXIMUM_WITHDRAWAL_AMOUNT);
        }
        HomemakerWallet wallet = this.homemakerWalletRepository.findByHomemakerId(homemakerId).orElseThrow(() -> new RuntimeException("Wallet not found for homemaker: " + homemakerId));
        if (wallet.getBalance() < withdrawalDTO.getAmount()) {
            throw new RuntimeException("Insufficient balance. Available: \u20b9" + wallet.getBalance());
        }
        HomemakerWithdrawal withdrawal = new HomemakerWithdrawal();
        withdrawal.setHomemakerId(homemakerId);
        withdrawal.setAmount(withdrawalDTO.getAmount());
        withdrawal.setMethod(withdrawalDTO.getMethod());
        withdrawal.setStatus(HomemakerWithdrawal.WithdrawalStatus.PENDING);
        if (withdrawalDTO.getMethod() == HomemakerWithdrawal.WithdrawalMethod.BANK_TRANSFER) {
            withdrawal.setBankAccountNumber(withdrawalDTO.getBankAccountNumber());
            withdrawal.setBankIFSC(withdrawalDTO.getBankIFSC());
        } else if (withdrawalDTO.getMethod() == HomemakerWithdrawal.WithdrawalMethod.UPI) {
            withdrawal.setUpiId(withdrawalDTO.getUpiId());
        } else if (withdrawalDTO.getMethod() == HomemakerWithdrawal.WithdrawalMethod.CHEQUE) {
            withdrawal.setChequeNumber(withdrawalDTO.getChequeNumber());
        }
        HomemakerWithdrawal savedWithdrawal = (HomemakerWithdrawal)this.homemakerWithdrawalRepository.save(withdrawal);
        log.info("Withdrawal request created: {}", (Object)savedWithdrawal.getId());
        return this.convertToDTO(savedWithdrawal);
    }

    public HomemakerWithdrawalDTO approveWithdrawal(Long withdrawalId, String transactionId) {
        log.info("Approving withdrawal: {}", (Object)withdrawalId);
        HomemakerWithdrawal withdrawal = (HomemakerWithdrawal)this.homemakerWithdrawalRepository.findById(withdrawalId).orElseThrow(() -> new RuntimeException("Withdrawal not found"));
        if (withdrawal.getStatus() != HomemakerWithdrawal.WithdrawalStatus.PENDING) {
            throw new RuntimeException("Only pending withdrawals can be approved");
        }
        withdrawal.setStatus(HomemakerWithdrawal.WithdrawalStatus.APPROVED);
        withdrawal.setProcessedDate(LocalDateTime.now());
        withdrawal.setTransactionId(transactionId);
        HomemakerWithdrawal updatedWithdrawal = (HomemakerWithdrawal)this.homemakerWithdrawalRepository.save(withdrawal);
        log.info("Withdrawal approved: {}", (Object)withdrawalId);
        return this.convertToDTO(updatedWithdrawal);
    }

    public HomemakerWithdrawalDTO completeWithdrawal(Long withdrawalId) {
        log.info("Completing withdrawal: {}", (Object)withdrawalId);
        HomemakerWithdrawal withdrawal = (HomemakerWithdrawal)this.homemakerWithdrawalRepository.findById(withdrawalId).orElseThrow(() -> new RuntimeException("Withdrawal not found"));
        if (withdrawal.getStatus() != HomemakerWithdrawal.WithdrawalStatus.APPROVED) {
            throw new RuntimeException("Only approved withdrawals can be completed");
        }
        HomemakerWallet wallet = this.homemakerWalletRepository.findByHomemakerId(withdrawal.getHomemakerId()).orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.setBalance(wallet.getBalance() - withdrawal.getAmount());
        wallet.setTotalWithdrawn(wallet.getTotalWithdrawn() + withdrawal.getAmount());
        wallet.setLastWithdrawalDate(LocalDateTime.now());
        this.homemakerWalletRepository.save(wallet);
        withdrawal.setStatus(HomemakerWithdrawal.WithdrawalStatus.COMPLETED);
        withdrawal.setCompletedDate(LocalDateTime.now());
        HomemakerWithdrawal updatedWithdrawal = (HomemakerWithdrawal)this.homemakerWithdrawalRepository.save(withdrawal);
        log.info("Withdrawal completed: {}", (Object)withdrawalId);
        return this.convertToDTO(updatedWithdrawal);
    }

    public HomemakerWithdrawalDTO rejectWithdrawal(Long withdrawalId, String reason) {
        log.info("Rejecting withdrawal: {}", (Object)withdrawalId);
        HomemakerWithdrawal withdrawal = (HomemakerWithdrawal)this.homemakerWithdrawalRepository.findById(withdrawalId).orElseThrow(() -> new RuntimeException("Withdrawal not found"));
        if (withdrawal.getStatus() != HomemakerWithdrawal.WithdrawalStatus.PENDING) {
            throw new RuntimeException("Only pending withdrawals can be rejected");
        }
        withdrawal.setStatus(HomemakerWithdrawal.WithdrawalStatus.REJECTED);
        withdrawal.setRejectionReason(reason);
        withdrawal.setProcessedDate(LocalDateTime.now());
        HomemakerWithdrawal updatedWithdrawal = (HomemakerWithdrawal)this.homemakerWithdrawalRepository.save(withdrawal);
        log.info("Withdrawal rejected: {}", (Object)withdrawalId);
        return this.convertToDTO(updatedWithdrawal);
    }

    public List<HomemakerWithdrawalDTO> getWithdrawalHistory(Long homemakerId) {
        log.debug("Fetching withdrawal history for homemaker: {}", (Object)homemakerId);
        return this.homemakerWithdrawalRepository.findRecentWithdrawals(homemakerId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public HomemakerWithdrawalDTO getWithdrawal(Long withdrawalId) {
        log.debug("Fetching withdrawal: {}", (Object)withdrawalId);
        HomemakerWithdrawal withdrawal = (HomemakerWithdrawal)this.homemakerWithdrawalRepository.findById(withdrawalId).orElseThrow(() -> new RuntimeException("Withdrawal not found"));
        return this.convertToDTO(withdrawal);
    }

    public List<HomemakerWithdrawalDTO> getPendingWithdrawals() {
        log.debug("Fetching all pending withdrawals");
        return this.homemakerWithdrawalRepository.findByStatus(HomemakerWithdrawal.WithdrawalStatus.PENDING).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private HomemakerWalletDTO convertToDTO(HomemakerWallet wallet) {
        return HomemakerWalletDTO.builder().id(wallet.getId()).homemakerId(wallet.getHomemakerId()).balance(wallet.getBalance()).totalEarnings(wallet.getTotalEarnings()).totalWithdrawn(wallet.getTotalWithdrawn()).lastWithdrawalDate(wallet.getLastWithdrawalDate()).createdAt(wallet.getCreatedAt()).updatedAt(wallet.getUpdatedAt()).build();
    }

    private HomemakerWithdrawalDTO convertToDTO(HomemakerWithdrawal withdrawal) {
        return HomemakerWithdrawalDTO.builder().id(withdrawal.getId()).homemakerId(withdrawal.getHomemakerId()).amount(withdrawal.getAmount()).status(withdrawal.getStatus()).method(withdrawal.getMethod()).bankAccountNumber(withdrawal.getBankAccountNumber()).bankIFSC(withdrawal.getBankIFSC()).upiId(withdrawal.getUpiId()).chequeNumber(withdrawal.getChequeNumber()).transactionId(withdrawal.getTransactionId()).rejectionReason(withdrawal.getRejectionReason()).requestDate(withdrawal.getRequestDate()).processedDate(withdrawal.getProcessedDate()).completedDate(withdrawal.getCompletedDate()).createdAt(withdrawal.getCreatedAt()).updatedAt(withdrawal.getUpdatedAt()).build();
    }

    @Generated
    public HomemakerWalletService(HomemakerWalletRepository homemakerWalletRepository, HomemakerWithdrawalRepository homemakerWithdrawalRepository) {
        this.homemakerWalletRepository = homemakerWalletRepository;
        this.homemakerWithdrawalRepository = homemakerWithdrawalRepository;
    }
}
