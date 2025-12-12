/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.Modifying
 *  org.springframework.data.jpa.repository.Query
 *  org.springframework.data.repository.query.Param
 *  org.springframework.stereotype.Repository
 */
package com.foodapp.deliveryexecutive.profile.repository;

import com.foodapp.deliveryexecutive.profile.entity.ExecutiveBankAccount;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutiveBankAccountRepository
extends JpaRepository<ExecutiveBankAccount, Long> {
    public List<ExecutiveBankAccount> findByExecutiveId(Long var1);

    public Optional<ExecutiveBankAccount> findByExecutiveIdAndIsPrimary(Long var1, boolean var2);

    public Optional<ExecutiveBankAccount> findByExecutiveIdAndId(Long var1, Long var2);

    public Optional<ExecutiveBankAccount> findByAccountNumber(String var1);

    public boolean existsByExecutiveIdAndAccountNumber(Long var1, String var2);

    @Query(value="SELECT b FROM ExecutiveBankAccount b WHERE b.executiveId = :executiveId AND b.isVerified = true")
    public List<ExecutiveBankAccount> findVerifiedAccountsByExecutiveId(@Param(value="executiveId") Long var1);

    @Modifying
    @Query(value="UPDATE ExecutiveBankAccount b SET b.isPrimary = false WHERE b.executiveId = :executiveId")
    public void clearPrimaryForExecutive(@Param(value="executiveId") Long var1);

    @Query(value="SELECT COUNT(b) FROM ExecutiveBankAccount b WHERE b.executiveId = :executiveId")
    public Long countByExecutiveId(@Param(value="executiveId") Long var1);
}
