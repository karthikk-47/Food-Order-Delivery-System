/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
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
