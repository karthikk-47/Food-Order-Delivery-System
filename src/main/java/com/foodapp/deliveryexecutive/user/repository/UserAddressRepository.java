package com.foodapp.deliveryexecutive.user.repository;

import com.foodapp.deliveryexecutive.user.entity.UserAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository
extends JpaRepository<UserAddress, Long> {
    public List<UserAddress> findByUserId(Long var1);

    public List<UserAddress> findByUserIdAndIsActive(Long var1, Boolean var2);

    public Optional<UserAddress> findByUserIdAndIsDefault(Long var1, Boolean var2);
}
