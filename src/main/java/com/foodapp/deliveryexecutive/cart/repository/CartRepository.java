package com.foodapp.deliveryexecutive.cart.repository;

import com.foodapp.deliveryexecutive.cart.entity.Cart;
import com.foodapp.deliveryexecutive.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository
extends JpaRepository<Cart, Long> {
    public Optional<Cart> findByUser(User var1);

    public Optional<Cart> findByUserId(Long var1);
}
