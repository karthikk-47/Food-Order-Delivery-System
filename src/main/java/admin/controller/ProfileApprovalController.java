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
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.admin.controller;

import com.foodapp.deliveryexecutive.admin.dto.ProfileApprovalDTO;
import com.foodapp.deliveryexecutive.admin.service.ProfileApprovalService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/admin/approvals"})
public class ProfileApprovalController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(ProfileApprovalController.class);
    @Autowired
    private ProfileApprovalService profileApprovalService;

    @GetMapping(value={"/homemakers/pending"})
    public ResponseEntity<List<ProfileApprovalDTO>> getPendingHomemakerApprovals() {
        log.info("Fetching pending homemaker approvals");
        try {
            List<ProfileApprovalDTO> pending = this.profileApprovalService.getPendingHomemakerApprovals();
            return ResponseEntity.ok(pending);
        }
        catch (Exception e) {
            log.error("Error fetching pending homemaker approvals", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @GetMapping(value={"/homemakers/approved"})
    public ResponseEntity<List<ProfileApprovalDTO>> getApprovedHomemakers() {
        log.info("Fetching approved homemakers");
        try {
            List<ProfileApprovalDTO> approved = this.profileApprovalService.getApprovedHomemakers();
            return ResponseEntity.ok(approved);
        }
        catch (Exception e) {
            log.error("Error fetching approved homemakers", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @GetMapping(value={"/homemakers/rejected"})
    public ResponseEntity<List<ProfileApprovalDTO>> getRejectedHomemakers() {
        log.info("Fetching rejected homemakers");
        try {
            List<ProfileApprovalDTO> rejected = this.profileApprovalService.getRejectedHomemakers();
            return ResponseEntity.ok(rejected);
        }
        catch (Exception e) {
            log.error("Error fetching rejected homemakers", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @PutMapping(value={"/homemakers/{id}/approve"})
    public ResponseEntity<Map<String, Object>> approveHomemaker(@PathVariable Long id, @RequestParam Long adminId) {
        log.info("Approving homemaker {} by admin {}", (Object)id, (Object)adminId);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            ProfileApprovalDTO approved = this.profileApprovalService.approveHomemaker(id, adminId);
            response.put("success", true);
            response.put("message", "Homemaker approved successfully");
            response.put("data", approved);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            log.error("Homemaker not found: {}", (Object)id);
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(response);
        }
        catch (IllegalStateException e) {
            log.error("Invalid state for approval: {}", (Object)e.getMessage());
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(response);
        }
        catch (Exception e) {
            log.error("Error approving homemaker", (Throwable)e);
            response.put("success", false);
            response.put("message", "Error approving homemaker");
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(value={"/homemakers/{id}/reject"})
    public ResponseEntity<Map<String, Object>> rejectHomemaker(@PathVariable Long id, @RequestParam Long adminId, @RequestBody Map<String, String> body) {
        log.info("Rejecting homemaker {} by admin {}", (Object)id, (Object)adminId);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            String reason = body.get("reason");
            if (reason == null || reason.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Rejection reason is required");
                return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(response);
            }
            ProfileApprovalDTO rejected = this.profileApprovalService.rejectHomemaker(id, adminId, reason);
            response.put("success", true);
            response.put("message", "Homemaker rejected successfully");
            response.put("data", rejected);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            log.error("Homemaker not found: {}", (Object)id);
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e) {
            log.error("Error rejecting homemaker", (Throwable)e);
            response.put("success", false);
            response.put("message", "Error rejecting homemaker");
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(value={"/homemakers/{id}/revert"})
    public ResponseEntity<Map<String, Object>> revertHomemakerToPending(@PathVariable Long id, @RequestParam Long adminId) {
        log.info("Reverting homemaker {} to pending by admin {}", (Object)id, (Object)adminId);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            ProfileApprovalDTO reverted = this.profileApprovalService.revertHomemakerToPending(id, adminId);
            response.put("success", true);
            response.put("message", "Homemaker reverted to pending status");
            response.put("data", reverted);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error reverting homemaker to pending", (Throwable)e);
            response.put("success", false);
            response.put("message", "Error reverting homemaker to pending");
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(value={"/homemakers/bulk-approve"})
    public ResponseEntity<Map<String, Object>> bulkApproveHomemakers(@RequestBody List<Long> homemakerIds, @RequestParam Long adminId) {
        log.info("Bulk approving {} homemakers by admin {}", (Object)homemakerIds.size(), (Object)adminId);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            List<ProfileApprovalDTO> approved = this.profileApprovalService.bulkApproveHomemakers(homemakerIds, adminId);
            response.put("success", true);
            response.put("message", String.format("Successfully approved %d homemakers", approved.size()));
            response.put("data", approved);
            response.put("approvedCount", approved.size());
            response.put("requestedCount", homemakerIds.size());
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error bulk approving homemakers", (Throwable)e);
            response.put("success", false);
            response.put("message", "Error bulk approving homemakers");
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value={"/executives/pending"})
    public ResponseEntity<List<ProfileApprovalDTO>> getPendingExecutiveApprovals() {
        log.info("Fetching pending delivery executive approvals");
        try {
            List<ProfileApprovalDTO> pending = this.profileApprovalService.getPendingExecutiveApprovals();
            return ResponseEntity.ok(pending);
        }
        catch (Exception e) {
            log.error("Error fetching pending executive approvals", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @GetMapping(value={"/executives/approved"})
    public ResponseEntity<List<ProfileApprovalDTO>> getApprovedExecutives() {
        log.info("Fetching approved delivery executives");
        try {
            List<ProfileApprovalDTO> approved = this.profileApprovalService.getApprovedExecutives();
            return ResponseEntity.ok(approved);
        }
        catch (Exception e) {
            log.error("Error fetching approved executives", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @GetMapping(value={"/executives/rejected"})
    public ResponseEntity<List<ProfileApprovalDTO>> getRejectedExecutives() {
        log.info("Fetching rejected delivery executives");
        try {
            List<ProfileApprovalDTO> rejected = this.profileApprovalService.getRejectedExecutives();
            return ResponseEntity.ok(rejected);
        }
        catch (Exception e) {
            log.error("Error fetching rejected executives", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @PutMapping(value={"/executives/{id}/approve"})
    public ResponseEntity<Map<String, Object>> approveExecutive(@PathVariable Long id, @RequestParam Long adminId) {
        log.info("Approving delivery executive {} by admin {}", (Object)id, (Object)adminId);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            ProfileApprovalDTO approved = this.profileApprovalService.approveExecutive(id, adminId);
            response.put("success", true);
            response.put("message", "Delivery Executive approved successfully");
            response.put("data", approved);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            log.error("Delivery Executive not found: {}", (Object)id);
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(response);
        }
        catch (IllegalStateException e) {
            log.error("Invalid state for approval: {}", (Object)e.getMessage());
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(response);
        }
        catch (Exception e) {
            log.error("Error approving delivery executive", (Throwable)e);
            response.put("success", false);
            response.put("message", "Error approving delivery executive");
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(value={"/executives/{id}/reject"})
    public ResponseEntity<Map<String, Object>> rejectExecutive(@PathVariable Long id, @RequestParam Long adminId, @RequestBody Map<String, String> body) {
        log.info("Rejecting delivery executive {} by admin {}", (Object)id, (Object)adminId);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            String reason = body.get("reason");
            if (reason == null || reason.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Rejection reason is required");
                return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body(response);
            }
            ProfileApprovalDTO rejected = this.profileApprovalService.rejectExecutive(id, adminId, reason);
            response.put("success", true);
            response.put("message", "Delivery Executive rejected successfully");
            response.put("data", rejected);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            log.error("Delivery Executive not found: {}", (Object)id);
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e) {
            log.error("Error rejecting delivery executive", (Throwable)e);
            response.put("success", false);
            response.put("message", "Error rejecting delivery executive");
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(value={"/executives/{id}/revert"})
    public ResponseEntity<Map<String, Object>> revertExecutiveToPending(@PathVariable Long id, @RequestParam Long adminId) {
        log.info("Reverting delivery executive {} to pending by admin {}", (Object)id, (Object)adminId);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            ProfileApprovalDTO reverted = this.profileApprovalService.revertExecutiveToPending(id, adminId);
            response.put("success", true);
            response.put("message", "Delivery Executive reverted to pending status");
            response.put("data", reverted);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error reverting delivery executive to pending", (Throwable)e);
            response.put("success", false);
            response.put("message", "Error reverting delivery executive to pending");
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(value={"/executives/bulk-approve"})
    public ResponseEntity<Map<String, Object>> bulkApproveExecutives(@RequestBody List<Long> executiveIds, @RequestParam Long adminId) {
        log.info("Bulk approving {} delivery executives by admin {}", (Object)executiveIds.size(), (Object)adminId);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            List<ProfileApprovalDTO> approved = this.profileApprovalService.bulkApproveExecutives(executiveIds, adminId);
            response.put("success", true);
            response.put("message", String.format("Successfully approved %d delivery executives", approved.size()));
            response.put("data", approved);
            response.put("approvedCount", approved.size());
            response.put("requestedCount", executiveIds.size());
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error bulk approving delivery executives", (Throwable)e);
            response.put("success", false);
            response.put("message", "Error bulk approving delivery executives");
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value={"/pending/all"})
    public ResponseEntity<List<ProfileApprovalDTO>> getAllPendingApprovals() {
        log.info("Fetching all pending approvals");
        try {
            List<ProfileApprovalDTO> pending = this.profileApprovalService.getAllPendingApprovals();
            return ResponseEntity.ok(pending);
        }
        catch (Exception e) {
            log.error("Error fetching all pending approvals", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @GetMapping(value={"/statistics"})
    public ResponseEntity<Map<String, Object>> getApprovalStatistics() {
        log.info("Fetching approval statistics");
        try {
            Map<String, Object> stats = this.profileApprovalService.getApprovalStatistics();
            return ResponseEntity.ok(stats);
        }
        catch (Exception e) {
            log.error("Error fetching approval statistics", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap());
        }
    }

    @GetMapping(value={"/homemakers/{id}/status"})
    public ResponseEntity<Map<String, Object>> getHomemakerApprovalStatus(@PathVariable Long id) {
        log.info("Checking homemaker approval status: {}", (Object)id);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            String status = this.profileApprovalService.getHomemakerApprovalStatus(id);
            boolean isApproved = this.profileApprovalService.isHomemakerApproved(id);
            response.put("id", id);
            response.put("userType", "HOMEMAKER");
            response.put("status", status);
            response.put("isApproved", isApproved);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping(value={"/executives/{id}/status"})
    public ResponseEntity<Map<String, Object>> getExecutiveApprovalStatus(@PathVariable Long id) {
        log.info("Checking delivery executive approval status: {}", (Object)id);
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            String status = this.profileApprovalService.getExecutiveApprovalStatus(id);
            boolean isApproved = this.profileApprovalService.isExecutiveApproved(id);
            response.put("id", id);
            response.put("userType", "DELIVERY_EXECUTIVE");
            response.put("status", status);
            response.put("isApproved", isApproved);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body(response);
        }
    }
}
