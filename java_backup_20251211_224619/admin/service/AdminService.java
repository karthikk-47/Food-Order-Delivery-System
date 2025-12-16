/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.admin.service;

import com.foodapp.deliveryexecutive.admin.dto.AdminDTO;
import com.foodapp.deliveryexecutive.admin.entity.Admin;
import com.foodapp.deliveryexecutive.admin.repository.AdminRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    private AdminRepository adminRepository;
    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final int LOCKOUT_DURATION_MINUTES = 30;

    public AdminDTO createAdmin(AdminDTO adminDTO) {
        log.info("Creating new admin: {}", (Object)adminDTO.getUsername());
        if (this.adminRepository.findByUsername(adminDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Admin with username already exists");
        }
        if (this.adminRepository.findByEmail(adminDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Admin with email already exists");
        }
        Admin admin = new Admin();
        admin.setUsername(adminDTO.getUsername());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        admin.setFullName(adminDTO.getFullName());
        admin.setPhoneNumber(adminDTO.getPhoneNumber());
        admin.setAdminRole(adminDTO.getAdminRole());
        admin.setStatus(Admin.AdminStatus.ACTIVE);
        admin.setCanManageUsers(adminDTO.getCanManageUsers());
        admin.setCanManagePayments(adminDTO.getCanManagePayments());
        admin.setCanManageDisputes(adminDTO.getCanManageDisputes());
        admin.setCanViewAnalytics(adminDTO.getCanViewAnalytics());
        admin.setCanManageAdmins(adminDTO.getCanManageAdmins());
        Admin savedAdmin = (Admin)this.adminRepository.save(admin);
        log.info("Admin created successfully with ID: {}", (Object)savedAdmin.getId());
        return this.convertToDTO(savedAdmin);
    }

    public AdminDTO getAdminById(Long id) {
        log.debug("Fetching admin by ID: {}", (Object)id);
        return this.adminRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
    }

    public AdminDTO getAdminByUsername(String username) {
        log.debug("Fetching admin by username: {}", (Object)username);
        return this.adminRepository.findByUsername(username).map(this::convertToDTO).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
    }

    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) {
        log.info("Updating admin with ID: {}", (Object)id);
        Admin admin = (Admin)this.adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        if (adminDTO.getFullName() != null) {
            admin.setFullName(adminDTO.getFullName());
        }
        if (adminDTO.getPhoneNumber() != null) {
            admin.setPhoneNumber(adminDTO.getPhoneNumber());
        }
        if (adminDTO.getCanManageUsers() != null) {
            admin.setCanManageUsers(adminDTO.getCanManageUsers());
        }
        if (adminDTO.getCanManagePayments() != null) {
            admin.setCanManagePayments(adminDTO.getCanManagePayments());
        }
        if (adminDTO.getCanManageDisputes() != null) {
            admin.setCanManageDisputes(adminDTO.getCanManageDisputes());
        }
        if (adminDTO.getCanViewAnalytics() != null) {
            admin.setCanViewAnalytics(adminDTO.getCanViewAnalytics());
        }
        Admin updatedAdmin = (Admin)this.adminRepository.save(admin);
        log.info("Admin updated successfully");
        return this.convertToDTO(updatedAdmin);
    }

    public void deleteAdmin(Long id) {
        log.info("Deleting admin with ID: {}", (Object)id);
        Admin admin = (Admin)this.adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        this.adminRepository.delete(admin);
        log.info("Admin deleted successfully");
    }

    public AdminDTO suspendAdmin(Long id) {
        log.info("Suspending admin with ID: {}", (Object)id);
        Admin admin = (Admin)this.adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        admin.setStatus(Admin.AdminStatus.SUSPENDED);
        Admin updatedAdmin = (Admin)this.adminRepository.save(admin);
        log.info("Admin suspended successfully");
        return this.convertToDTO(updatedAdmin);
    }

    public AdminDTO activateAdmin(Long id) {
        log.info("Activating admin with ID: {}", (Object)id);
        Admin admin = (Admin)this.adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        admin.setStatus(Admin.AdminStatus.ACTIVE);
        admin.setLoginAttempts(0);
        admin.setAccountLockedUntil(null);
        Admin updatedAdmin = (Admin)this.adminRepository.save(admin);
        log.info("Admin activated successfully");
        return this.convertToDTO(updatedAdmin);
    }

    public void recordLoginAttempt(String username, boolean success, String ipAddress) {
        log.debug("Recording login attempt for username: {} from IP: {}", (Object)username, (Object)ipAddress);
        Optional<Admin> optionalAdmin = this.adminRepository.findByUsername(username);
        if (optionalAdmin.isEmpty()) {
            return;
        }
        Admin admin = optionalAdmin.get();
        if (success) {
            admin.setLastLogin(LocalDateTime.now());
            admin.setLastLoginIp(ipAddress);
            admin.setLoginAttempts(0);
            admin.setAccountLockedUntil(null);
        } else {
            admin.setLoginAttempts(admin.getLoginAttempts() + 1);
            if (admin.getLoginAttempts() >= 5) {
                admin.setAccountLockedUntil(LocalDateTime.now().plusMinutes(30L));
                log.warn("Admin account locked due to multiple failed login attempts: {}", (Object)username);
            }
        }
        this.adminRepository.save(admin);
    }

    public boolean isAccountLocked(Long adminId) {
        return this.adminRepository.findById(adminId).map(admin -> admin.getAccountLockedUntil() != null && admin.getAccountLockedUntil().isAfter(LocalDateTime.now())).orElse(false);
    }

    public List<AdminDTO> getAdminsByRole(Admin.AdminRole role) {
        log.debug("Fetching admins by role: {}", (Object)role);
        return this.adminRepository.findByAdminRole(role).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<AdminDTO> getAllActiveAdmins() {
        log.debug("Fetching all active admins");
        return this.adminRepository.findByStatus(Admin.AdminStatus.ACTIVE).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AdminDTO convertToDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setUsername(admin.getUsername());
        dto.setEmail(admin.getEmail());
        dto.setFullName(admin.getFullName());
        dto.setPhoneNumber(admin.getPhoneNumber());
        dto.setAdminRole(admin.getAdminRole());
        dto.setStatus(admin.getStatus());
        dto.setCanManageUsers(admin.getCanManageUsers());
        dto.setCanManagePayments(admin.getCanManagePayments());
        dto.setCanManageDisputes(admin.getCanManageDisputes());
        dto.setCanViewAnalytics(admin.getCanViewAnalytics());
        dto.setCanManageAdmins(admin.getCanManageAdmins());
        dto.setCreatedAt(admin.getCreatedAt());
        dto.setUpdatedAt(admin.getUpdatedAt());
        dto.setLastLogin(admin.getLastLogin());
        dto.setIsAccountLocked(admin.getAccountLockedUntil() != null && admin.getAccountLockedUntil().isAfter(LocalDateTime.now()));
        return dto;
    }
}
