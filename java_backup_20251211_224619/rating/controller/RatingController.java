/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.rating.controller;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.rating.dto.RatingDTO;
import com.foodapp.deliveryexecutive.rating.entity.Rating;
import com.foodapp.deliveryexecutive.rating.service.RatingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/ratings"})
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping(value={"/{customerId}"})
    public ResponseEntity<List<RatingDTO>> getRatings(@PathVariable Long customerId, @RequestParam Actor.Role role) {
        List<RatingDTO> ratings = this.ratingService.getRatingsByIdAndRole(customerId, role);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping(value={"/{customerId}/average"})
    public ResponseEntity<Double> getAverageRating(@PathVariable Long customerId, @RequestParam Actor.Role role) {
        Double average = this.ratingService.getAverageRating(customerId, role);
        return ResponseEntity.ok((Object)average);
    }

    @GetMapping(value={"/{customerId}/count"})
    public ResponseEntity<Long> getRatingCount(@PathVariable Long customerId, @RequestParam Actor.Role role) {
        Long count = this.ratingService.getRatingCount(customerId, role);
        return ResponseEntity.ok((Object)count);
    }

    @GetMapping(value={"/{customerId}/stars/{stars}"})
    public ResponseEntity<List<RatingDTO>> getRatingsByStars(@PathVariable Long customerId, @RequestParam Actor.Role role, @PathVariable int stars) {
        List<RatingDTO> ratings = this.ratingService.getRatingsByStars(customerId, role, stars);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping(value={"/{customerId}/top"})
    public ResponseEntity<List<RatingDTO>> getTopRatings(@PathVariable Long customerId, @RequestParam Actor.Role role, @RequestParam(defaultValue="10") int limit) {
        List<RatingDTO> ratings = this.ratingService.getTopRatings(customerId, role, limit);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping(value={"/{customerId}/recent"})
    public ResponseEntity<List<RatingDTO>> getRecentRatings(@PathVariable Long customerId, @RequestParam Actor.Role role, @RequestParam(defaultValue="10") int limit) {
        List<RatingDTO> ratings = this.ratingService.getRecentRatings(customerId, role, limit);
        return ResponseEntity.ok(ratings);
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestParam Long customerId, @RequestParam Actor.Role role, @RequestParam int stars, @RequestParam(required=false) String comment) {
        try {
            Rating rating = this.ratingService.createRating(customerId, role, stars, comment);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body((Object)rating);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value={"/{ratingId}"})
    public ResponseEntity<Rating> updateRating(@PathVariable Long ratingId, @RequestParam int stars, @RequestParam(required=false) String comment) {
        try {
            Rating rating = this.ratingService.updateRating(ratingId, stars, comment);
            return ResponseEntity.ok((Object)rating);
        }
        catch (Exception e) {
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value={"/{ratingId}"})
    public ResponseEntity<Void> deleteRating(@PathVariable Long ratingId) {
        try {
            this.ratingService.deleteRating(ratingId);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).build();
        }
    }
}
