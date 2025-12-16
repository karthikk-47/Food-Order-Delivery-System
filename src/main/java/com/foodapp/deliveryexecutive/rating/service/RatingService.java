/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.rating.service;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.rating.dto.RatingDTO;
import com.foodapp.deliveryexecutive.rating.entity.Rating;
import com.foodapp.deliveryexecutive.rating.repository.RatingRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingService {
    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);
    @Autowired
    private RatingRepository ratingRepository;

    public List<RatingDTO> getRatingsByIdAndRole(Long id, Actor.Role role) {
        return this.ratingRepository.findByIdAndRole(id, role).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<RatingDTO> getRatingsByCustomerId(Long customerId) {
        return this.ratingRepository.findByCustomerId(customerId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Rating getRatingById(Long ratingId) {
        return (Rating)this.ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with ID: " + ratingId));
    }

    @Transactional
    public Rating createRating(Long customerId, Actor.Role role, int stars, String comment) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Stars must be between 1 and 5");
        }
        Rating rating = new Rating();
        rating.setCustomerId(customerId);
        rating.setRole(role);
        rating.setStars(stars);
        rating.setComment(comment);
        this.ratingRepository.save(rating);
        logger.info("Created rating for customer: {} with {} stars", customerId, stars);
        return rating;
    }

    @Transactional
    public Rating updateRating(Long ratingId, int stars, String comment) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Stars must be between 1 and 5");
        }
        Rating rating = this.getRatingById(ratingId);
        rating.setStars(stars);
        rating.setComment(comment);
        this.ratingRepository.save(rating);
        logger.info("Updated rating: {} with {} stars", ratingId, stars);
        return rating;
    }

    @Transactional
    public void deleteRating(Long ratingId) {
        if (!this.ratingRepository.existsById(ratingId)) {
            throw new ResourceNotFoundException("Rating not found with ID: " + ratingId);
        }
        this.ratingRepository.deleteById(ratingId);
        logger.info("Deleted rating: {}", ratingId);
    }

    public Double getAverageRating(Long customerId, Actor.Role role) {
        Double average = this.ratingRepository.getAverageRatingByCustomerIdAndRole(customerId, role);
        return average != null ? average : 0.0;
    }

    public Long getRatingCount(Long customerId, Actor.Role role) {
        return this.ratingRepository.countByCustomerIdAndRole(customerId, role);
    }

    public List<RatingDTO> getRatingsByStars(Long customerId, Actor.Role role, int stars) {
        return this.ratingRepository.findByCustomerIdAndRoleAndStars(customerId, role, stars).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<RatingDTO> getTopRatings(Long customerId, Actor.Role role, int limit) {
        return this.ratingRepository.findTopRatingsByCustomerIdAndRole(customerId, role).stream().limit(limit).map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<RatingDTO> getRecentRatings(Long customerId, Actor.Role role, int limit) {
        return this.ratingRepository.findRecentRatingsByCustomerIdAndRole(customerId, role).stream().limit(limit).map(this::mapToDTO).collect(Collectors.toList());
    }

    private RatingDTO mapToDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setStars(rating.getStars());
        dto.setComment(rating.getComment());
        return dto;
    }
}
