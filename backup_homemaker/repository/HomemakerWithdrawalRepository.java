/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.Query
 *  org.springframework.data.repository.query.Param
 *  org.springframework.stereotype.Repository
 */
package com.foodapp.deliveryexecutive.homemaker.repository;

import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWithdrawal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HomemakerWithdrawalRepository
extends JpaRepository<HomemakerWithdrawal, Long> {
    public List<HomemakerWithdrawal> findByHomemakerId(Long var1);

    public List<HomemakerWithdrawal> findByHomemakerIdAndStatus(Long var1, HomemakerWithdrawal.WithdrawalStatus var2);

    public List<HomemakerWithdrawal> findByStatus(HomemakerWithdrawal.WithdrawalStatus var1);

    @Query(value="SELECT w FROM HomemakerWithdrawal w WHERE w.homemakerId = :homemakerId ORDER BY w.requestDate DESC")
    public List<HomemakerWithdrawal> findRecentWithdrawals(@Param(value="homemakerId") Long var1);

    @Query(value="SELECT SUM(w.amount) FROM HomemakerWithdrawal w WHERE w.homemakerId = :homemakerId AND w.status = 'COMPLETED'")
    public Double getTotalCompletedWithdrawals(@Param(value="homemakerId") Long var1);

    @Query(value="SELECT SUM(w.amount) FROM HomemakerWithdrawal w WHERE w.homemakerId = :homemakerId AND w.status = 'PENDING'")
    public Double getTotalPendingWithdrawals(@Param(value="homemakerId") Long var1);

    public Optional<HomemakerWithdrawal> findByTransactionId(String var1);

    public List<HomemakerWithdrawal> findByRequestDateBetween(LocalDateTime var1, LocalDateTime var2);
}
