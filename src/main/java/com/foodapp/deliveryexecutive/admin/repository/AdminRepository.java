package com.foodapp.deliveryexecutive.admin.repository;

import com.foodapp.deliveryexecutive.admin.entity.Admin;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository
extends JpaRepository<Admin, Long> {
    public Optional<Admin> findByUsername(String var1);

    public Optional<Admin> findByEmail(String var1);

    public Optional<Admin> findByPhoneNumber(String var1);

    public List<Admin> findByAdminRole(Admin.AdminRole var1);

    public List<Admin> findByStatus(Admin.AdminStatus var1);

    public List<Admin> findByAdminRoleAndStatus(Admin.AdminRole var1, Admin.AdminStatus var2);
}
