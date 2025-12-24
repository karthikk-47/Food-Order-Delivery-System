package com.foodapp.deliveryexecutive.logging.controller;

import com.foodapp.deliveryexecutive.logging.model.AuditLogEntry;
import com.foodapp.deliveryexecutive.logging.repository.AuditLogRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for viewing and managing audit logs.
 * Only accessible by admins.
 */
@RestController
@RequestMapping("/api/admin/audit-logs")
@PreAuthorize("hasRole('ADMIN')")
public class AuditLogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    /**
     * Get paginated audit logs
     */
    @GetMapping
    public ResponseEntity<Page<AuditLogEntry>> getAuditLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) String entityType) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "timestamp"));

        Page<AuditLogEntry> result;
        if (userId != null && !userId.isEmpty()) {
            result = auditLogRepository.findByUserId(userId, pageRequest);
        } else if (action != null && !action.isEmpty()) {
            result = auditLogRepository.findByAction(action, pageRequest);
        } else {
            result = auditLogRepository.findAll(pageRequest);
        }

        return ResponseEntity.ok(result);
    }

    /**
     * Get audit logs for a specific entity
     */
    @GetMapping("/entity/{entityType}/{entityId}")
    public ResponseEntity<Page<AuditLogEntry>> getEntityAuditLogs(
            @PathVariable String entityType,
            @PathVariable String entityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "timestamp"));
        Page<AuditLogEntry> result = auditLogRepository.findByEntityTypeAndEntityId(entityType, entityId, pageRequest);
        return ResponseEntity.ok(result);
    }

    /**
     * Get audit logs by date range
     */
    @GetMapping("/date-range")
    public ResponseEntity<Page<AuditLogEntry>> getAuditLogsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "timestamp"));
        Page<AuditLogEntry> result = auditLogRepository.findByDateRange(startDate, endDate, pageRequest);
        return ResponseEntity.ok(result);
    }

    /**
     * Get recent failures
     */
    @GetMapping("/failures")
    public ResponseEntity<List<AuditLogEntry>> getRecentFailures(
            @RequestParam(defaultValue = "24") int hoursAgo) {
        LocalDateTime since = LocalDateTime.now().minusHours(hoursAgo);
        List<AuditLogEntry> failures = auditLogRepository.findRecentFailures(since);
        return ResponseEntity.ok(failures);
    }

    /**
     * Get action statistics
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getActionStats(
            @RequestParam(defaultValue = "7") int daysAgo) {
        LocalDateTime since = LocalDateTime.now().minusDays(daysAgo);
        List<Object[]> stats = auditLogRepository.getActionStats(since);

        Map<String, Object> result = new HashMap<>();
        Map<String, Long> actionCounts = new HashMap<>();

        for (Object[] row : stats) {
            actionCounts.put((String) row[0], (Long) row[1]);
        }

        result.put("actionCounts", actionCounts);
        result.put("period", daysAgo + " days");
        result.put("since", since);

        return ResponseEntity.ok(result);
    }

    /**
     * Get a specific audit log entry
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuditLogEntry> getAuditLogById(@PathVariable Long id) {
        return auditLogRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
