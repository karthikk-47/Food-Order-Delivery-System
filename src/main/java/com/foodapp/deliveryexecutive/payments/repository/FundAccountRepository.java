package com.foodapp.deliveryexecutive.payments.repository;

import com.foodapp.deliveryexecutive.payments.entity.FundAccountEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FundAccountRepository
extends JpaRepository<FundAccountEntity, String> {
    public List<FundAccountEntity> findByStatus(String var1);

    public List<FundAccountEntity> findByActive(boolean var1);

    public Optional<FundAccountEntity> findByBatchId(String var1);

    @Query(value="SELECT f FROM FundAccountEntity f WHERE f.status = :status AND f.active = true")
    public List<FundAccountEntity> findActiveByStatus(@Param(value="status") String var1);

    @Query(value="SELECT f FROM FundAccountEntity f WHERE f.active = true")
    public List<FundAccountEntity> findAllActive();

    @Query(value="SELECT f FROM FundAccountEntity f WHERE f.utr = :utr")
    public Optional<FundAccountEntity> findByUtr(@Param(value="utr") String var1);

    @Query(value="SELECT COUNT(f) FROM FundAccountEntity f WHERE f.status = :status")
    public Long countByStatus(@Param(value="status") String var1);

    @Query(value="SELECT COUNT(f) FROM FundAccountEntity f WHERE f.active = true")
    public Long countActive();
}
