package com.foodapp.deliveryexecutive.user.repository;

import com.foodapp.deliveryexecutive.user.entity.UserProfile;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository
extends JpaRepository<UserProfile, Long> {
    public Optional<UserProfile> findByUserId(Long var1);

    public Optional<UserProfile> findByEmail(String var1);

    public Optional<UserProfile> findByPhoneNumber(String var1);

    public List<UserProfile> findByAccountStatus(UserProfile.AccountStatus var1);
}
