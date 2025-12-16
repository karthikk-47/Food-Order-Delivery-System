package com.foodapp.deliveryexecutive.homemaker.repository;

import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeMakerRepository
extends JpaRepository<HomeMaker, Long> {
    public Optional<HomeMaker> findByName(String var1);

    public Optional<HomeMaker> findByMobile(String var1);

    public List<HomeMaker> findByApprovalStatus(HomeMaker.ApprovalStatus var1);

    public long countByApprovalStatus(HomeMaker.ApprovalStatus var1);
}
