package com.foodapp.deliveryexecutive.homemaker.repository;

import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerAnalytics;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomemakerAnalyticsRepository
extends JpaRepository<HomemakerAnalytics, Long> {
    public Optional<HomemakerAnalytics> findByHomemakerId(Long var1);
}
