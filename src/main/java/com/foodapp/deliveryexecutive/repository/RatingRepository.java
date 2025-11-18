package com.foodapp.deliveryexecutive.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.foodapp.deliveryexecutive.entity.Rating;
import com.foodapp.deliveryexecutive.entity.Wallet.CustomerRole;

public interface RatingRepository extends JpaRepository<Rating, Long>{

	List<Rating> findByIdAndCustomerRole(Long id, CustomerRole customerType);
}
