/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.foodapp.deliveryexecutive.cart.repository;

import com.foodapp.deliveryexecutive.cart.entity.Cart;
import com.foodapp.deliveryexecutive.cart.entity.CartItem;
import com.foodapp.deliveryexecutive.homemaker.entity.MenuItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository
extends JpaRepository<CartItem, Long> {
    public List<CartItem> findByCart(Cart var1);

    public Optional<CartItem> findByCartAndMenuItem(Cart var1, MenuItem var2);

    public void deleteByCart(Cart var1);
}
