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
package com.foodapp.deliveryexecutive.wallet.service;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.wallet.dto.WalletDTO;
import com.foodapp.deliveryexecutive.wallet.entity.Wallet;
import com.foodapp.deliveryexecutive.wallet.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {
    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);
    private static final double BASE_FEE_PERCENTAGE = 0.8;
    private static final double DISTANCE_FEE_PER_KM = 2.0;
    @Autowired
    private WalletRepository walletRepository;

    public Wallet getWalletByCustomerId(Long customerId) {
        return this.walletRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Wallet not found for customer: " + customerId));
    }

    public Wallet getWalletByCustomerIdAndRole(Long customerId, Actor.Role role) {
        return this.walletRepository.findByCustomerIdAndRole(customerId, role).orElseThrow(() -> new ResourceNotFoundException("Wallet not found for customer: " + customerId + " with role: " + String.valueOf((Object)role)));
    }

    public WalletDTO getWalletDTO(Long customerId, Actor.Role role) {
        Wallet wallet = this.walletRepository.findByCustomerIdAndRole(customerId, role).orElse(this.createDefaultWallet(customerId, role));
        WalletDTO dto = new WalletDTO();
        dto.setBalance(wallet.getBalance());
        return dto;
    }

    @Transactional
    public Wallet createWallet(Long customerId, Actor.Role role) {
        if (this.walletRepository.existsByCustomerIdAndRole(customerId, role)) {
            throw new RuntimeException("Wallet already exists for customer: " + customerId);
        }
        Wallet wallet = new Wallet();
        wallet.setCustomerId(customerId);
        wallet.setRole(role);
        wallet.setBalance(0.0);
        this.walletRepository.save(wallet);
        logger.info("Created wallet for customer: {} with role: {}", (Object)customerId, (Object)role);
        return wallet;
    }

    @Transactional
    public Wallet addBalance(Long customerId, Actor.Role role, Double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        Wallet wallet = this.walletRepository.findByCustomerIdAndRole(customerId, role).orElse(this.createDefaultWallet(customerId, role));
        wallet.setBalance(wallet.getBalance() + amount);
        this.walletRepository.save(wallet);
        logger.info("Added {} to wallet for customer: {}, new balance: {}", new Object[]{amount, customerId, wallet.getBalance()});
        return wallet;
    }

    @Transactional
    public Wallet deductBalance(Long customerId, Actor.Role role, Double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        Wallet wallet = this.walletRepository.findByCustomerIdAndRole(customerId, role).orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        wallet.setBalance(wallet.getBalance() - amount);
        this.walletRepository.save(wallet);
        logger.info("Deducted {} from wallet for customer: {}, new balance: {}", new Object[]{amount, customerId, wallet.getBalance()});
        return wallet;
    }

    @Transactional
    public void processDeliveryPayment(Long executiveId, Order order) {
        Wallet wallet = this.walletRepository.findByCustomerIdAndRole(executiveId, Actor.Role.DELIVERYEXECUTIVE).orElse(this.createDefaultWallet(executiveId, Actor.Role.DELIVERYEXECUTIVE));
        double deliveryFee = this.calculateDeliveryFee(order);
        wallet.setBalance(wallet.getBalance() + deliveryFee);
        this.walletRepository.save(wallet);
        logger.info("Processed delivery payment for executive {}: {} added, new balance: {}", new Object[]{executiveId, deliveryFee, wallet.getBalance()});
    }

    public double calculateDeliveryFee(Order order) {
        double baseFee = order.getAmount() * 0.8;
        double distanceFee = order.getDistance() / 1000.0 * 2.0;
        double totalFee = baseFee + distanceFee;
        logger.debug("Calculated delivery fee for order {}: base={}, distance={}, total={}", new Object[]{order.getId(), baseFee, distanceFee, totalFee});
        return totalFee;
    }

    @Transactional
    public Wallet transferBalance(Long fromCustomerId, Long toCustomerId, Actor.Role fromRole, Actor.Role toRole, Double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        Wallet fromWallet = this.walletRepository.findByCustomerIdAndRole(fromCustomerId, fromRole).orElseThrow(() -> new ResourceNotFoundException("Source wallet not found"));
        if (fromWallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in source wallet");
        }
        Wallet toWallet = this.walletRepository.findByCustomerIdAndRole(toCustomerId, toRole).orElse(this.createDefaultWallet(toCustomerId, toRole));
        fromWallet.setBalance(fromWallet.getBalance() - amount);
        toWallet.setBalance(toWallet.getBalance() + amount);
        this.walletRepository.save(fromWallet);
        this.walletRepository.save(toWallet);
        logger.info("Transferred {} from customer {} to customer {}", new Object[]{amount, fromCustomerId, toCustomerId});
        return fromWallet;
    }

    public Double getBalance(Long customerId, Actor.Role role) {
        return this.walletRepository.findByCustomerIdAndRole(customerId, role).map(Wallet::getBalance).orElse(0.0);
    }

    public boolean hasBalance(Long customerId, Actor.Role role, Double amount) {
        Double balance = this.getBalance(customerId, role);
        return balance >= amount;
    }

    private Wallet createDefaultWallet(Long customerId, Actor.Role role) {
        Wallet wallet = new Wallet();
        wallet.setCustomerId(customerId);
        wallet.setRole(role);
        wallet.setBalance(0.0);
        return wallet;
    }
}
