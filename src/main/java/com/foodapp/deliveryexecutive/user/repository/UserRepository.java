/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 *  org.springframework.stereotype.Repository
 */
package com.foodapp.deliveryexecutive.user.repository;

import com.foodapp.deliveryexecutive.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
extends JpaRepository<User, Long> {
    public Optional<User> findByMobile(String var1);

    public Optional<User> findByName(String var1);
}
