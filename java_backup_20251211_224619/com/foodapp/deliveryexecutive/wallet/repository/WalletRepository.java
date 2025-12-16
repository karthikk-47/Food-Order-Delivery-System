/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.Query
 *  org.springframework.data.repository.query.Param
 *  org.springframework.stereotype.Repository
 */
package com.foodapp.deliveryexecutive.wallet.repository;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.wallet.entity.Wallet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository
extends JpaRepository<Wallet, Long> {
    public Optional<Wallet> findByIdAndRole(Long var1, Actor.Role var2);

    public Optional<Wallet> findByCustomerId(Long var1);

    public Optional<Wallet> findByCustomerIdAndRole(Long var1, Actor.Role var2);

    public List<Wallet> findByRole(Actor.Role var1);

    public boolean existsByCustomerIdAndRole(Long var1, Actor.Role var2);

    @Query(value="SELECT w FROM Wallet w WHERE w.role = :role AND w.balance > :minBalance")
    public List<Wallet> findByRoleAndBalanceGreaterThan(@Param(value="role") Actor.Role var1, @Param(value="minBalance") Double var2);

    @Query(value="SELECT SUM(w.balance) FROM Wallet w WHERE w.role = :role")
    public Double getTotalBalanceByRole(@Param(value="role") Actor.Role var1);

    @Query(value="SELECT COUNT(w) FROM Wallet w WHERE w.role = :role")
    public Long countByRole(@Param(value="role") Actor.Role var1);

    @Query(value="SELECT AVG(w.balance) FROM Wallet w WHERE w.role = :role")
    public Double getAverageBalanceByRole(@Param(value="role") Actor.Role var1);
}
