package com.foodapp.deliveryexecutive.payments.repository;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.payments.entity.UserBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<UserBankAccount, Long> {
    List<UserBankAccount> findByUserIdAndUserRole(Long userId, Actor.Role userRole);
    
    Optional<UserBankAccount> findByUserIdAndUserRoleAndIsPrimaryTrue(Long userId, Actor.Role userRole);
    
    Optional<UserBankAccount> findByIdAndUserIdAndUserRole(Long id, Long userId, Actor.Role userRole);
    
    boolean existsByUserIdAndUserRoleAndAccountNumber(Long userId, Actor.Role userRole, String accountNumber);
    
    void deleteByIdAndUserIdAndUserRole(Long id, Long userId, Actor.Role userRole);
}
