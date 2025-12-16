package com.foodapp.deliveryexecutive.admin.repository;

import com.foodapp.deliveryexecutive.admin.entity.Dispute;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisputeRepository
extends JpaRepository<Dispute, Long> {
    public List<Dispute> findByOrderId(Long var1);

    public List<Dispute> findByComplainantId(Long var1);

    public List<Dispute> findByRespondentId(Long var1);

    public List<Dispute> findByStatus(Dispute.DisputeStatus var1);

    public List<Dispute> findByAssignedAdminId(Long var1);

    public List<Dispute> findByStatusAndAssignedAdminId(Dispute.DisputeStatus var1, Long var2);

    public List<Dispute> findByCategory(Dispute.DisputeCategory var1);
}
