/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.user.controller;

import com.foodapp.deliveryexecutive.user.dto.FavouriteHomemakerDTO;
import com.foodapp.deliveryexecutive.user.service.FavouriteHomemakerService;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/favourite-homemaker"})
public class FavouriteHomemakerController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(FavouriteHomemakerController.class);
    @Autowired
    private FavouriteHomemakerService favouriteHomemakerService;

    @PostMapping(value={"/add"})
    public ResponseEntity<FavouriteHomemakerDTO> addToFavourites(@RequestParam Long userId, @RequestParam Long homemakerId) {
        log.info("Adding homemaker {} to favourites for user: {}", homemakerId, userId);
        try {
            FavouriteHomemakerDTO favourite = this.favouriteHomemakerService.addToFavourites(userId, homemakerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(favourite);
        }
        catch (Exception e) {
            log.error("Error adding to favourites", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/remove"})
    public ResponseEntity<Void> removeFromFavourites(@RequestParam Long userId, @RequestParam Long homemakerId) {
        log.info("Removing homemaker {} from favourites for user: {}", homemakerId, userId);
        try {
            this.favouriteHomemakerService.removeFromFavourites(userId, homemakerId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            log.error("Error removing from favourites", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/user/{userId}"})
    public ResponseEntity<List<FavouriteHomemakerDTO>> getFavouriteHomemakers(@PathVariable Long userId) {
        log.info("Fetching favourite homemakers for user: {}", userId);
        try {
            List<FavouriteHomemakerDTO> favourites = this.favouriteHomemakerService.getFavouriteHomemakers(userId);
            return ResponseEntity.ok(favourites);
        }
        catch (Exception e) {
            log.error("Error fetching favourite homemakers", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/check"})
    public ResponseEntity<Boolean> isFavourited(@RequestParam Long userId, @RequestParam Long homemakerId) {
        log.info("Checking if homemaker {} is favourited by user: {}", homemakerId, userId);
        try {
            boolean isFavourited = this.favouriteHomemakerService.isFavourited(userId, homemakerId);
            return ResponseEntity.ok(isFavourited);
        }
        catch (Exception e) {
            log.error("Error checking favourite status", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
