package com.foodapp.deliveryexecutive.homemaker.repository;

import com.foodapp.deliveryexecutive.homemaker.entity.Menu;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository
extends JpaRepository<Menu, Long> {
    public List<Menu> findByHomemakerId(Long var1);

    public List<Menu> findByHomemakerIdAndStatus(Long var1, Menu.MenuStatus var2);

    public List<Menu> findByStatus(Menu.MenuStatus var1);

    public List<Menu> findByMenuType(Menu.MenuType var1);

    @Query(value="SELECT m FROM Menu m WHERE m.homemakerId = :homemakerId AND m.status = 'ACTIVE' AND m.validFrom <= :now AND (m.validUntil IS NULL OR m.validUntil >= :now)")
    public List<Menu> findActiveMenusForHomemaker(@Param(value="homemakerId") Long var1, @Param(value="now") LocalDateTime var2);

    public Optional<Menu> findByIdAndHomemakerId(Long var1, Long var2);
}
