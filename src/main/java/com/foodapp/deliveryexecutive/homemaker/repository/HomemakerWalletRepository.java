package com.foodapp.deliveryexecutive.homemaker.repository;

import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWallet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomemakerWalletRepository
extends JpaRepository<HomemakerWallet, Long> {
    public Optional<HomemakerWallet> findByHomemakerId(Long var1);

    public boolean existsByHomemakerId(Long var1);
}
