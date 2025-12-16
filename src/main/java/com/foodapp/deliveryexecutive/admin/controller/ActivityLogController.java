package com.foodapp.deliveryexecutive.admin.controller;

import com.foodapp.deliveryexecutive.admin.entity.ActivityLog;
import com.foodapp.deliveryexecutive.admin.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/logs")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogService logService;

    @GetMapping
    public ResponseEntity<Page<ActivityLog>> getLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        
        log.info("Fetching logs - page: {}, size: {}, level: {}, category: {}", page, size, level, category);
        
        ActivityLog.LogLevel logLevel = null;
        ActivityLog.LogCategory logCategory = null;
        
        if (level != null && !level.isEmpty()) {
            try {
                logLevel = ActivityLog.LogLevel.valueOf(level.toUpperCase());
            } catch (IllegalArgumentException e) {
                log.warn("Invalid log level: {}", level);
            }
        }
        
        if (category != null && !category.isEmpty()) {
            try {
                logCategory = ActivityLog.LogCategory.valueOf(category.toUpperCase());
            } catch (IllegalArgumentException e) {
                log.warn("Invalid log category: {}", category);
            }
        }
        
        Page<ActivityLog> logs = logService.getLogsFiltered(logLevel, logCategory, search, page, size);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getLogStatistics() {
        log.info("Fetching log statistics");
        return ResponseEntity.ok(logService.getLogStatistics());
    }

    @GetMapping("/levels")
    public ResponseEntity<ActivityLog.LogLevel[]> getLogLevels() {
        return ResponseEntity.ok(ActivityLog.LogLevel.values());
    }

    @GetMapping("/categories")
    public ResponseEntity<ActivityLog.LogCategory[]> getLogCategories() {
        return ResponseEntity.ok(ActivityLog.LogCategory.values());
    }

    @DeleteMapping("/cleanup")
    public ResponseEntity<Map<String, String>> cleanupLogs(
            @RequestParam(defaultValue = "30") int daysToKeep) {
        log.info("Cleaning up logs older than {} days", daysToKeep);
        logService.cleanupOldLogs(daysToKeep);
        return ResponseEntity.ok(Map.of("message", "Logs older than " + daysToKeep + " days have been deleted"));
    }
}
