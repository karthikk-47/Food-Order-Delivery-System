package com.foodapp.deliveryexecutive.admin.repository;

import com.foodapp.deliveryexecutive.admin.entity.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    
    Page<ActivityLog> findAllByOrderByTimestampDesc(Pageable pageable);
    
    Page<ActivityLog> findByLevelOrderByTimestampDesc(ActivityLog.LogLevel level, Pageable pageable);
    
    Page<ActivityLog> findByCategoryOrderByTimestampDesc(ActivityLog.LogCategory category, Pageable pageable);
    
    Page<ActivityLog> findByLevelAndCategoryOrderByTimestampDesc(
            ActivityLog.LogLevel level, ActivityLog.LogCategory category, Pageable pageable);
    
    List<ActivityLog> findByTimestampBetweenOrderByTimestampDesc(LocalDateTime start, LocalDateTime end);
    
    List<ActivityLog> findByUserIdOrderByTimestampDesc(Long userId);
    
    @Query("SELECT l FROM ActivityLog l WHERE " +
           "(:level IS NULL OR l.level = :level) AND " +
           "(:category IS NULL OR l.category = :category) AND " +
           "(:search IS NULL OR LOWER(l.message) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(l.action) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "ORDER BY l.timestamp DESC")
    Page<ActivityLog> findByFilters(
            @Param("level") ActivityLog.LogLevel level,
            @Param("category") ActivityLog.LogCategory category,
            @Param("search") String search,
            Pageable pageable);
    
    @Query("SELECT COUNT(l) FROM ActivityLog l WHERE l.level = :level AND l.timestamp >= :since")
    long countByLevelSince(@Param("level") ActivityLog.LogLevel level, @Param("since") LocalDateTime since);
    
    void deleteByTimestampBefore(LocalDateTime dateTime);
}
