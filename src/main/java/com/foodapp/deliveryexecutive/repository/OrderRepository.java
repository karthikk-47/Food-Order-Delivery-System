package com.foodapp.deliveryexecutive.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.deliveryexecutive.entity.Order;
import com.foodapp.deliveryexecutive.entity.Order.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByExecutiveId(long id);
	List<Order> findByExecutiveIdAndOrderStatus(long id, OrderStatus status);
}
