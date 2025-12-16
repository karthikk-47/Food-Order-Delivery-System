package com.foodapp.deliveryexecutive.admin.repository;

import com.foodapp.deliveryexecutive.admin.entity.Verification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository
extends JpaRepository<Verification, Long> {
    public List<Verification> findByUserIdAndUserType(Long var1, String var2);

    public List<Verification> findByStatus(Verification.VerificationStatus var1);

    public List<Verification> findByUserTypeAndStatus(String var1, Verification.VerificationStatus var2);

    public Optional<Verification> findByUserIdAndUserTypeAndVerificationType(Long var1, String var2, Verification.VerificationType var3);

    public List<Verification> findByVerificationTypeAndStatus(Verification.VerificationType var1, Verification.VerificationStatus var2);
}
