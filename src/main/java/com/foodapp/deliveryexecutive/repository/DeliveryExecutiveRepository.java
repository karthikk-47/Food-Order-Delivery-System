package com.foodapp.deliveryexecutive.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodapp.deliveryexecutive.entity.DeliveryExecutive;

public interface DeliveryExecutiveRepository extends JpaRepository<DeliveryExecutive, Long> {

	Optional<DeliveryExecutive> findByMobile(String mobileNo);
}
