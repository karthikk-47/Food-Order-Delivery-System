package com.foodapp.deliveryexecutive.logging.repository;

import com.foodapp.deliveryexecutive.logging.model.AuditLogEntry;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for audit log entries.
 */
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLogEntry, Long> {

    Page<AuditLogEntry> findByUserId(String userId, Pageable pageable);

    Page<AuditLogEntry> findByAction(String action, Pageable pageable);

    Page<AuditLogEntry> findByEntityTypeAndEntityId(String entityType, String entityId, Pageable pageable);

    @Query("SELECT a FROM AuditLogEntry a WHERE a.timestamp BETWEEN :startDate AND :endDate ORDER BY a.timestamp DESC")
    Page<AuditLogEntry> findByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);

    @Query("SELECT a FROM AuditLogEntry a WHERE a.userId = :userId AND a.timestamp BETWEEN :startDate AND :endDate ORDER BY a.timestamp DESC")
    List<AuditLogEntry> findByUserIdAndDateRange(
            @Param("userId") String userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT a.action, COUNT(a) FROM AuditLogEntry a WHERE a.timestamp >= :since GROUP BY a.action ORDER BY COUNT(a) DESC")
    List<Object[]> getActionStats(@Param("since") LocalDateTime since);

    @Query("SELECT a FROM AuditLogEntry a WHERE a.success = false AND a.timestamp >= :since ORDER BY a.timestamp DESC")
    List<AuditLogEntry> findRecentFailures(@Param("since") LocalDateTime since);
}
