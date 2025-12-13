/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.Query
 *  org.springframework.data.repository.query.Param
 *  org.springframework.stereotype.Repository
 */
package com.foodapp.deliveryexecutive.payments.repository;

import com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository
extends JpaRepository<WithdrawTransaction, Long> {
    public List<WithdrawTransaction> findByCustomerIdOrderByCreatedAtDesc(Long var1);

    public List<WithdrawTransaction> findByCustomerIdAndStatusOrderByCreatedAtDesc(Long var1, WithdrawTransaction.WithdrawStatus var2);

    public Optional<WithdrawTransaction> findByPayoutId(String var1);

    @Query(value="SELECT SUM(w.amount) FROM WithdrawTransaction w WHERE w.customerId = :customerId AND w.status = :status")
    public Double getTotalWithdrawnAmount(@Param(value="customerId") Long var1, @Param(value="status") WithdrawTransaction.WithdrawStatus var2);

    @Query(value="SELECT w FROM WithdrawTransaction w WHERE w.customerId = :customerId AND w.createdAt BETWEEN :startDate AND :endDate ORDER BY w.createdAt DESC")
    public List<WithdrawTransaction> findByCustomerIdAndDateRange(@Param(value="customerId") Long var1, @Param(value="startDate") LocalDateTime var2, @Param(value="endDate") LocalDateTime var3);

    public List<WithdrawTransaction> findByStatus(WithdrawTransaction.WithdrawStatus var1);

    @Query(value="SELECT COUNT(w) FROM WithdrawTransaction w WHERE w.customerId = :customerId AND w.status = :status")
    public Long countByCustomerIdAndStatus(@Param(value="customerId") Long var1, @Param(value="status") WithdrawTransaction.WithdrawStatus var2);
}
