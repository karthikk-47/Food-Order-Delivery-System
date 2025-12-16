package com.foodapp.deliveryexecutive.user.service;

import com.foodapp.deliveryexecutive.user.dto.FavouriteHomemakerDTO;
import com.foodapp.deliveryexecutive.user.entity.FavouriteHomemaker;
import com.foodapp.deliveryexecutive.user.repository.FavouriteHomemakerRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FavouriteHomemakerService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(FavouriteHomemakerService.class);
    @Autowired
    private FavouriteHomemakerRepository favouriteHomemakerRepository;

    public FavouriteHomemakerDTO addToFavourites(Long userId, Long homemakerId) {
        log.info("Adding homemaker {} to favourites for user: {}", homemakerId, userId);
        FavouriteHomemaker existing = this.favouriteHomemakerRepository.findByUserIdAndHomemakerId(userId, homemakerId).orElse(null);
        if (existing != null) {
            if (existing.getStatus() == FavouriteHomemaker.FavouriteStatus.REMOVED) {
                existing.setStatus(FavouriteHomemaker.FavouriteStatus.ACTIVE);
                existing.setRemovedAt(null);
                FavouriteHomemaker saved = (FavouriteHomemaker)this.favouriteHomemakerRepository.save(existing);
                log.info("Homemaker re-added to favourites");
                return this.convertToDTO(saved);
            }
            log.warn("Homemaker already in favourites");
            return this.convertToDTO(existing);
        }
        FavouriteHomemaker favourite = new FavouriteHomemaker();
        favourite.setUserId(userId);
        favourite.setHomemakerId(homemakerId);
        FavouriteHomemaker savedFavourite = (FavouriteHomemaker)this.favouriteHomemakerRepository.save(favourite);
        log.info("Homemaker added to favourites successfully");
        return this.convertToDTO(savedFavourite);
    }

    public void removeFromFavourites(Long userId, Long homemakerId) {
        log.info("Removing homemaker {} from favourites for user: {}", homemakerId, userId);
        FavouriteHomemaker favourite = this.favouriteHomemakerRepository.findByUserIdAndHomemakerId(userId, homemakerId).orElseThrow(() -> new IllegalArgumentException("Favourite not found"));
        favourite.setStatus(FavouriteHomemaker.FavouriteStatus.REMOVED);
        favourite.setRemovedAt(LocalDateTime.now());
        this.favouriteHomemakerRepository.save(favourite);
        log.info("Homemaker removed from favourites");
    }

    public List<FavouriteHomemakerDTO> getFavouriteHomemakers(Long userId) {
        log.debug("Fetching favourite homemakers for user: {}", userId);
        return this.favouriteHomemakerRepository.findByUserIdAndStatus(userId, FavouriteHomemaker.FavouriteStatus.ACTIVE).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public boolean isFavourited(Long userId, Long homemakerId) {
        log.debug("Checking if homemaker {} is favourited by user: {}", homemakerId, userId);
        return this.favouriteHomemakerRepository.findByUserIdAndHomemakerId(userId, homemakerId).filter(fav -> fav.getStatus() == FavouriteHomemaker.FavouriteStatus.ACTIVE).isPresent();
    }

    private FavouriteHomemakerDTO convertToDTO(FavouriteHomemaker favourite) {
        FavouriteHomemakerDTO dto = new FavouriteHomemakerDTO();
        dto.setId(favourite.getId());
        dto.setUserId(favourite.getUserId());
        dto.setHomemakerId(favourite.getHomemakerId());
        dto.setAddedAt(favourite.getAddedAt());
        dto.setRemovedAt(favourite.getRemovedAt());
        dto.setStatus(favourite.getStatus());
        return dto;
    }
}
