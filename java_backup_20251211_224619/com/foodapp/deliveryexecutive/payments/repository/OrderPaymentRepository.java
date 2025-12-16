/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.Query
 *  org.springframework.stereotype.Repository
 */
package com.foodapp.deliveryexecutive.payments.repository;

import com.foodapp.deliveryexecutive.payments.entity.OrderPayment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRepository
extends JpaRepository<OrderPayment, Long> {
    public Optional<OrderPayment> findByOrderId(Long var1);

    public Optional<OrderPayment> findByRazorpayOrderId(String var1);

    public Optional<OrderPayment> findByRazorpayPaymentId(String var1);

    public List<OrderPayment> findByUserId(Long var1);

    public List<OrderPayment> findByUserIdAndStatus(Long var1, OrderPayment.PaymentStatus var2);

    public List<OrderPayment> findByStatus(OrderPayment.PaymentStatus var1);

    @Query(value="SELECT op FROM OrderPayment op WHERE op.userId = :userId ORDER BY op.createdAt DESC")
    public List<OrderPayment> findByUserIdOrderByCreatedAtDesc(Long var1);

    @Query(value="SELECT op FROM OrderPayment op WHERE op.userId = :userId AND op.status = 'SUCCESS' ORDER BY op.paidAt DESC")
    public List<OrderPayment> findSuccessfulPaymentsByUserId(Long var1);

    @Query(value="SELECT SUM(op.amount) FROM OrderPayment op WHERE op.userId = :userId AND op.status = 'SUCCESS'")
    public Double getTotalAmountPaidByUser(Long var1);

    @Query(value="SELECT COUNT(op) FROM OrderPayment op WHERE op.userId = :userId AND op.status = 'SUCCESS'")
    public Long getSuccessfulPaymentCountByUser(Long var1);

    @Query(value="SELECT op FROM OrderPayment op WHERE op.status = 'PENDING' AND op.createdAt < :expiryTime")
    public List<OrderPayment> findExpiredPendingPayments(LocalDateTime var1);
}
