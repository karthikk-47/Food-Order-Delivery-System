package com.foodapp.deliveryexecutive.homemaker.repository;

import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomemakerProfileRepository
extends JpaRepository<HomemakerProfile, Long> {
    public Optional<HomemakerProfile> findByUserId(Long var1);

    public Optional<HomemakerProfile> findByEmail(String var1);

    public Optional<HomemakerProfile> findByPhoneNumber(String var1);
}
