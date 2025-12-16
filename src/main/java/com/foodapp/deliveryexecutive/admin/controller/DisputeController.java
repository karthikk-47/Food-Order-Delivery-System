/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.admin.controller;

import com.foodapp.deliveryexecutive.admin.dto.DisputeDTO;
import com.foodapp.deliveryexecutive.admin.entity.Dispute;
import com.foodapp.deliveryexecutive.admin.service.DisputeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/dispute"})
public class DisputeController {
    private static final Logger log = LoggerFactory.getLogger(DisputeController.class);
    @Autowired
    private DisputeService disputeService;

    @PostMapping(value={"/create"})
    public ResponseEntity<DisputeDTO> createDispute(@RequestBody DisputeDTO disputeDTO) {
        log.info("Creating dispute for order: {}", disputeDTO.getOrderId());
        try {
            DisputeDTO created = this.disputeService.createDispute(disputeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }
        catch (Exception e) {
            log.error("Error creating dispute", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<DisputeDTO> getDispute(@PathVariable Long id) {
        log.info("Fetching dispute with ID: {}", id);
        try {
            DisputeDTO dispute = this.disputeService.getDisputeById(id);
            return ResponseEntity.ok(dispute);
        }
        catch (Exception e) {
            log.error("Error fetching dispute", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value={"/order/{orderId}"})
    public ResponseEntity<List<DisputeDTO>> getDisputesByOrder(@PathVariable Long orderId) {
        log.info("Fetching disputes for order: {}", orderId);
        try {
            List<DisputeDTO> disputes = this.disputeService.getDisputesByOrderId(orderId);
            return ResponseEntity.ok(disputes);
        }
        catch (Exception e) {
            log.error("Error fetching disputes for order", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/complainant/{complainantId}"})
    public ResponseEntity<List<DisputeDTO>> getDisputesByComplainant(@PathVariable Long complainantId) {
        log.info("Fetching disputes by complainant: {}", complainantId);
        try {
            List<DisputeDTO> disputes = this.disputeService.getDisputesByComplainant(complainantId);
            return ResponseEntity.ok(disputes);
        }
        catch (Exception e) {
            log.error("Error fetching disputes by complainant", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/open/all"})
    public ResponseEntity<List<DisputeDTO>> getOpenDisputes() {
        log.info("Fetching all open disputes");
        try {
            List<DisputeDTO> disputes = this.disputeService.getOpenDisputes();
            return ResponseEntity.ok(disputes);
        }
        catch (Exception e) {
            log.error("Error fetching open disputes", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/assign"})
    public ResponseEntity<DisputeDTO> assignDispute(@PathVariable Long id, @RequestParam Long adminId) {
        log.info("Assigning dispute: {} to admin: {}", id, adminId);
        try {
            DisputeDTO assigned = this.disputeService.assignDisputeToAdmin(id, adminId);
            return ResponseEntity.ok(assigned);
        }
        catch (Exception e) {
            log.error("Error assigning dispute", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/resolve"})
    public ResponseEntity<DisputeDTO> resolveDispute(@PathVariable Long id, @RequestParam Dispute.DisputeResolution resolution, @RequestParam(required=false) Double refundAmount, @RequestParam(required=false) String notes) {
        log.info("Resolving dispute: {} with resolution: {}", id, resolution);
        try {
            DisputeDTO resolved = this.disputeService.resolveDispute(id, resolution, refundAmount, notes);
            return ResponseEntity.ok(resolved);
        }
        catch (Exception e) {
            log.error("Error resolving dispute", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/close"})
    public ResponseEntity<DisputeDTO> closeDispute(@PathVariable Long id) {
        log.info("Closing dispute: {}", id);
        try {
            DisputeDTO closed = this.disputeService.closeDispute(id);
            return ResponseEntity.ok(closed);
        }
        catch (Exception e) {
            log.error("Error closing dispute", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/escalate"})
    public ResponseEntity<DisputeDTO> escalateDispute(@PathVariable Long id, @RequestParam Long adminId) {
        log.info("Escalating dispute: {} to admin: {}", id, adminId);
        try {
            DisputeDTO escalated = this.disputeService.escalateDispute(id, adminId);
            return ResponseEntity.ok(escalated);
        }
        catch (Exception e) {
            log.error("Error escalating dispute", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/admin/{adminId}/all"})
    public ResponseEntity<List<DisputeDTO>> getDisputesByAdmin(@PathVariable Long adminId) {
        log.info("Fetching disputes assigned to admin: {}", adminId);
        try {
            List<DisputeDTO> disputes = this.disputeService.getDisputesByAssignedAdmin(adminId);
            return ResponseEntity.ok(disputes);
        }
        catch (Exception e) {
            log.error("Error fetching disputes by admin", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/admin/{adminId}/open"})
    public ResponseEntity<List<DisputeDTO>> getOpenDisputesByAdmin(@PathVariable Long adminId) {
        log.info("Fetching open disputes for admin: {}", adminId);
        try {
            List<DisputeDTO> disputes = this.disputeService.getOpenDisputesByAdmin(adminId);
            return ResponseEntity.ok(disputes);
        }
        catch (Exception e) {
            log.error("Error fetching open disputes for admin", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/category/{category}"})
    public ResponseEntity<List<DisputeDTO>> getDisputesByCategory(@PathVariable Dispute.DisputeCategory category) {
        log.info("Fetching disputes by category: {}", category);
        try {
            List<DisputeDTO> disputes = this.disputeService.getDisputesByCategory(category);
            return ResponseEntity.ok(disputes);
        }
        catch (Exception e) {
            log.error("Error fetching disputes by category", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
