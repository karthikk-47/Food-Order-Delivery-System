/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.admin.controller;

import com.foodapp.deliveryexecutive.admin.dto.AdminDTO;
import com.foodapp.deliveryexecutive.admin.entity.Admin;
import com.foodapp.deliveryexecutive.admin.service.AdminService;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/admin"})
public class AdminController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    @PostMapping(value={"/create"})
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        log.info("Creating new admin: {}", adminDTO.getUsername());
        try {
            AdminDTO createdAdmin = this.adminService.createAdmin(adminDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
        }
        catch (Exception e) {
            log.error("Error creating admin", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable Long id) {
        log.info("Fetching admin with ID: {}", id);
        try {
            AdminDTO admin = this.adminService.getAdminById(id);
            return ResponseEntity.ok(admin);
        }
        catch (Exception e) {
            log.error("Error fetching admin", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value={"/username/{username}"})
    public ResponseEntity<AdminDTO> getAdminByUsername(@PathVariable String username) {
        log.info("Fetching admin with username: {}", username);
        try {
            AdminDTO admin = this.adminService.getAdminByUsername(username);
            return ResponseEntity.ok(admin);
        }
        catch (Exception e) {
            log.error("Error fetching admin", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        log.info("Updating admin with ID: {}", id);
        try {
            AdminDTO updatedAdmin = this.adminService.updateAdmin(id, adminDTO);
            return ResponseEntity.ok(updatedAdmin);
        }
        catch (Exception e) {
            log.error("Error updating admin", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        log.info("Deleting admin with ID: {}", id);
        try {
            this.adminService.deleteAdmin(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            log.error("Error deleting admin", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/suspend"})
    public ResponseEntity<AdminDTO> suspendAdmin(@PathVariable Long id) {
        log.info("Suspending admin with ID: {}", id);
        try {
            AdminDTO suspendedAdmin = this.adminService.suspendAdmin(id);
            return ResponseEntity.ok(suspendedAdmin);
        }
        catch (Exception e) {
            log.error("Error suspending admin", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/activate"})
    public ResponseEntity<AdminDTO> activateAdmin(@PathVariable Long id) {
        log.info("Activating admin with ID: {}", id);
        try {
            AdminDTO activatedAdmin = this.adminService.activateAdmin(id);
            return ResponseEntity.ok(activatedAdmin);
        }
        catch (Exception e) {
            log.error("Error activating admin", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/role/{role}"})
    public ResponseEntity<List<AdminDTO>> getAdminsByRole(@PathVariable Admin.AdminRole role) {
        log.info("Fetching admins by role: {}", role);
        try {
            List<AdminDTO> admins = this.adminService.getAdminsByRole(role);
            return ResponseEntity.ok(admins);
        }
        catch (Exception e) {
            log.error("Error fetching admins by role", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/active/all"})
    public ResponseEntity<List<AdminDTO>> getAllActiveAdmins() {
        log.info("Fetching all active admins");
        try {
            List<AdminDTO> admins = this.adminService.getAllActiveAdmins();
            return ResponseEntity.ok(admins);
        }
        catch (Exception e) {
            log.error("Error fetching active admins", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
