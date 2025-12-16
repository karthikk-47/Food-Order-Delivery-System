package com.foodapp.deliveryexecutive.user.repository;

import com.foodapp.deliveryexecutive.user.entity.UserPreference;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceRepository
extends JpaRepository<UserPreference, Long> {
    public Optional<UserPreference> findByUserId(Long var1);
}
