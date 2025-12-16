package com.foodapp.deliveryexecutive.user.repository;

import com.foodapp.deliveryexecutive.user.entity.FavouriteHomemaker;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteHomemakerRepository
extends JpaRepository<FavouriteHomemaker, Long> {
    public List<FavouriteHomemaker> findByUserId(Long var1);

    public List<FavouriteHomemaker> findByUserIdAndStatus(Long var1, FavouriteHomemaker.FavouriteStatus var2);

    public Optional<FavouriteHomemaker> findByUserIdAndHomemakerId(Long var1, Long var2);
}
