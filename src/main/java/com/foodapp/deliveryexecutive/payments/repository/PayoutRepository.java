package com.foodapp.deliveryexecutive.payments.repository;

import com.foodapp.deliveryexecutive.payments.entity.Payout;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayoutRepository
extends JpaRepository<Payout, String> {
    public Optional<Payout> findByReferenceId(String var1);

    public Optional<Payout> findByUtr(String var1);

    public List<Payout> findByFundAccountId(String var1);

    public List<Payout> findByStatus(String var1);

    public List<Payout> findByMode(String var1);

    public List<Payout> findByPurpose(String var1);

    public Optional<Payout> findByBatchId(String var1);

    @Query(value="SELECT p FROM Payout p WHERE p.fundAccountId = :fundAccountId AND p.status = :status")
    public List<Payout> findByFundAccountIdAndStatus(@Param(value="fundAccountId") String var1, @Param(value="status") String var2);

    @Query(value="SELECT p FROM Payout p WHERE p.status = :status AND p.createdAt >= :startTime AND p.createdAt <= :endTime")
    public List<Payout> findByStatusAndDateRange(@Param(value="status") String var1, @Param(value="startTime") int var2, @Param(value="endTime") int var3);

    @Query(value="SELECT SUM(p.amount) FROM Payout p WHERE p.fundAccountId = :fundAccountId AND p.status = 'processed'")
    public Long getTotalProcessedAmount(@Param(value="fundAccountId") String var1);

    @Query(value="SELECT COUNT(p) FROM Payout p WHERE p.status = :status")
    public Long countByStatus(@Param(value="status") String var1);

    @Query(value="SELECT p FROM Payout p WHERE p.status IN ('queued', 'pending', 'processing') ORDER BY p.createdAt DESC")
    public List<Payout> findPendingPayouts();

    @Query(value="SELECT p FROM Payout p WHERE p.status = 'failed' ORDER BY p.createdAt DESC")
    public List<Payout> findFailedPayouts();

    @Query(value="SELECT p FROM Payout p WHERE p.fundAccountId = :fundAccountId ORDER BY p.createdAt DESC")
    public List<Payout> findByFundAccountIdOrderByCreatedAtDesc(@Param(value="fundAccountId") String var1);

    @Query(value="SELECT p FROM Payout p WHERE p.createdAt >= :startTime AND p.createdAt <= :endTime ORDER BY p.createdAt DESC")
    public List<Payout> findByDateRange(@Param(value="startTime") int var1, @Param(value="endTime") int var2);

    public boolean existsByReferenceId(String var1);

    public boolean existsByUtr(String var1);
}
