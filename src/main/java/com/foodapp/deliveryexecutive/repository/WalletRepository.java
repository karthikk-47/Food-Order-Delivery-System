package com.foodapp.deliveryexecutive.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.deliveryexecutive.entity.Wallet;
import com.foodapp.deliveryexecutive.entity.Wallet.CustomerRole;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

	Optional<Wallet> findByIdAndCustomerRole(Long id, CustomerRole customerRole);
}
