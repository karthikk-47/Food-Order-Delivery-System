package com.foodapp.deliveryexecutive.homemaker.repository;

import com.foodapp.deliveryexecutive.homemaker.entity.MenuItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository
extends JpaRepository<MenuItem, Long> {
    public List<MenuItem> findByMenuId(Long var1);

    public List<MenuItem> findByMenuIdAndIsAvailable(Long var1, Boolean var2);
}
