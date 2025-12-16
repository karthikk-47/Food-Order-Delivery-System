package com.foodapp.deliveryexecutive.rating.repository;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.rating.entity.Rating;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository
extends JpaRepository<Rating, Long> {
    public List<Rating> findByIdAndRole(Long var1, Actor.Role var2);

    public List<Rating> findByCustomerId(Long var1);

    public List<Rating> findByCustomerIdAndRole(Long var1, Actor.Role var2);

    public List<Rating> findByCustomerIdAndRoleAndStars(Long var1, Actor.Role var2, int var3);

    @Query(value="SELECT AVG(r.stars) FROM Rating r WHERE r.customerId = :customerId AND r.role = :role")
    public Double getAverageRatingByCustomerIdAndRole(@Param(value="customerId") Long var1, @Param(value="role") Actor.Role var2);

    @Query(value="SELECT COUNT(r) FROM Rating r WHERE r.customerId = :customerId AND r.role = :role")
    public Long countByCustomerIdAndRole(@Param(value="customerId") Long var1, @Param(value="role") Actor.Role var2);

    @Query(value="SELECT r FROM Rating r WHERE r.customerId = :customerId AND r.role = :role ORDER BY r.stars DESC")
    public List<Rating> findTopRatingsByCustomerIdAndRole(@Param(value="customerId") Long var1, @Param(value="role") Actor.Role var2);

    @Query(value="SELECT r FROM Rating r WHERE r.customerId = :customerId AND r.role = :role ORDER BY r.id DESC")
    public List<Rating> findRecentRatingsByCustomerIdAndRole(@Param(value="customerId") Long var1, @Param(value="role") Actor.Role var2);

    @Query(value="SELECT r FROM Rating r WHERE r.role = :role AND r.stars >= :minStars")
    public List<Rating> findByRoleAndMinStars(@Param(value="role") Actor.Role var1, @Param(value="minStars") int var2);

    public boolean existsByCustomerIdAndRole(Long var1, Actor.Role var2);
}
