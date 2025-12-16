package com.foodapp.deliveryexecutive.executive.repository;

import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryExecutiveRepository
extends JpaRepository<DeliveryExecutive, Long> {
    public Optional<DeliveryExecutive> findByMobile(String var1);

    public List<DeliveryExecutive> findByApprovalStatus(DeliveryExecutive.ApprovalStatus var1);

    public long countByApprovalStatus(DeliveryExecutive.ApprovalStatus var1);
}
