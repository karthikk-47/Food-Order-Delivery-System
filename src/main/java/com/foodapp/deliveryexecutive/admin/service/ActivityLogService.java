package com.foodapp.deliveryexecutive.admin.service;

import com.foodapp.deliveryexecutive.admin.entity.ActivityLog;
import com.foodapp.deliveryexecutive.admin.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityLogService {

    private final ActivityLogRepository logRepository;

    @Async
    @Transactional
    public void log(ActivityLog.LogLevel level, ActivityLog.LogCategory category, 
                    String action, String message) {
        log(level, category, action, message, null, null, null, null, null, null, null);
    }

    @Async
    @Transactional
    public void log(ActivityLog.LogLevel level, ActivityLog.LogCategory category,
                    String action, String message, Long userId, String userRole, String userMobile) {
        log(level, category, action, message, userId, userRole, userMobile, null, null, null, null);
    }

    @Async
    @Transactional
    public void log(ActivityLog.LogLevel level, ActivityLog.LogCategory category,
                    String action, String message, Long userId, String userRole, String userMobile,
                    String ipAddress, String entityType, Long entityId, String details) {
        try {
            ActivityLog activityLog = ActivityLog.builder()
                    .level(level)
                    .category(category)
                    .action(action)
                    .message(message)
                    .userId(userId)
                    .userRole(userRole)
                    .userMobile(userMobile)
                    .ipAddress(ipAddress)
                    .entityType(entityType)
                    .entityId(entityId)
                    .details(details)
                    .timestamp(LocalDateTime.now())
                    .build();
            
            logRepository.save(activityLog);
        } catch (Exception e) {
            log.error("Failed to save activity log: {}", e.getMessage());
        }
    }

    // Convenience methods for common log types
    public void logAuth(String action, String message, Long userId, String userRole, String mobile) {
        log(ActivityLog.LogLevel.INFO, ActivityLog.LogCategory.AUTH, action, message, userId, userRole, mobile);
    }

    public void logUserAction(String action, String message, Long userId, String userRole) {
        log(ActivityLog.LogLevel.INFO, ActivityLog.LogCategory.USER, action, message, userId, userRole, null);
    }

    public void logAdminAction(String action, String message, Long adminId) {
        log(ActivityLog.LogLevel.INFO, ActivityLog.LogCategory.ADMIN, action, message, adminId, "ADMIN", null);
    }

    public void logApproval(String action, String message, Long adminId, String entityType, Long entityId) {
        log(ActivityLog.LogLevel.INFO, ActivityLog.LogCategory.APPROVAL, action, message, 
            adminId, "ADMIN", null, null, entityType, entityId, null);
    }

    public void logError(ActivityLog.LogCategory category, String action, String message, String details) {
        log(ActivityLog.LogLevel.ERROR, category, action, message, null, null, null, null, null, null, details);
    }

    public void logWarning(ActivityLog.LogCategory category, String action, String message) {
        log(ActivityLog.LogLevel.WARNING, category, action, message);
    }

    // Query methods
    @Transactional(readOnly = true)
    public Page<ActivityLog> getLogs(int page, int size) {
        return logRepository.findAllByOrderByTimestampDesc(PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public Page<ActivityLog> getLogsByLevel(ActivityLog.LogLevel level, int page, int size) {
        return logRepository.findByLevelOrderByTimestampDesc(level, PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public Page<ActivityLog> getLogsByCategory(ActivityLog.LogCategory category, int page, int size) {
        return logRepository.findByCategoryOrderByTimestampDesc(category, PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public Page<ActivityLog> getLogsFiltered(ActivityLog.LogLevel level, ActivityLog.LogCategory category,
                                              String search, int page, int size) {
        return logRepository.findByFilters(level, category, search, PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getLogStatistics() {
        LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);
        LocalDateTime lastWeek = LocalDateTime.now().minusDays(7);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalLogs", logRepository.count());
        stats.put("errorsLast24h", logRepository.countByLevelSince(ActivityLog.LogLevel.ERROR, last24Hours));
        stats.put("warningsLast24h", logRepository.countByLevelSince(ActivityLog.LogLevel.WARNING, last24Hours));
        stats.put("errorsLastWeek", logRepository.countByLevelSince(ActivityLog.LogLevel.ERROR, lastWeek));
        
        return stats;
    }

    @Transactional
    public void cleanupOldLogs(int daysToKeep) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(daysToKeep);
        logRepository.deleteByTimestampBefore(cutoff);
        log.info("Cleaned up logs older than {} days", daysToKeep);
    }
}
