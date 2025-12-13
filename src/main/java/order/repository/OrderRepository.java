/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.data.jpa.repository.Query
 *  org.springframework.data.repository.query.Param
 *  org.springframework.stereotype.Repository
 */
package com.foodapp.deliveryexecutive.order.repository;

import com.foodapp.deliveryexecutive.order.entity.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
extends JpaRepository<Order, Long> {
    public List<Order> findByExecutiveId(Long var1);

    public List<Order> findByExecutiveIdAndOrderStatus(Long var1, Order.OrderStatus var2);

    public List<Order> findByOrderStatus(Order.OrderStatus var1);

    public List<Order> findByOrderStatusOrOrderStatus(Order.OrderStatus var1, Order.OrderStatus var2);

    public Optional<Order> findByIdAndExecutiveId(Long var1, Long var2);

    @Query(value="SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.id DESC")
    public List<Order> findByUserId(@Param(value="userId") Long var1);

    @Query(value="SELECT o FROM Order o WHERE o.homeMaker.id = :homeMakerId ORDER BY o.id DESC")
    public List<Order> findByHomeMakerId(@Param(value="homeMakerId") Long var1);

    @Query(value="SELECT o FROM Order o WHERE o.orderStatus IN :statuses ORDER BY o.id DESC")
    public List<Order> findByOrderStatusIn(@Param(value="statuses") List<Order.OrderStatus> var1);

    @Query(value="SELECT o FROM Order o WHERE o.executive.id = :executiveId ORDER BY o.id DESC")
    public List<Order> findByExecutiveIdOrderByIdDesc(@Param(value="executiveId") Long var1);

    @Query(value="SELECT COUNT(o) FROM Order o WHERE o.executive.id = :executiveId AND o.orderStatus = :status")
    public Long countByExecutiveIdAndStatus(@Param(value="executiveId") Long var1, @Param(value="status") Order.OrderStatus var2);

    @Query(value="SELECT SUM(o.amount) FROM Order o WHERE o.executive.id = :executiveId AND o.orderStatus = 'DELIVERED'")
    public Double getTotalEarningsByExecutiveId(@Param(value="executiveId") Long var1);

    @Query(value="SELECT o FROM Order o WHERE o.orderStatus IN ('PREPARING', 'PREPARED') AND o.executive IS NULL")
    public List<Order> findAvailableOrders();

    @Query(value="SELECT o FROM Order o WHERE o.executive.id = :executiveId AND o.orderStatus NOT IN ('DELIVERED', 'CANCELLED')")
    public List<Order> findActiveOrdersByExecutiveId(@Param(value="executiveId") Long var1);

    public boolean existsByIdAndExecutiveId(Long var1, Long var2);
}
