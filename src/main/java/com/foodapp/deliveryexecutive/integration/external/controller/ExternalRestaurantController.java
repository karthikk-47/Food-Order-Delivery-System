package com.foodapp.deliveryexecutive.integration.external.controller;

import com.foodapp.deliveryexecutive.integration.external.dto.RestaurantSearchResponse;
import com.foodapp.deliveryexecutive.integration.external.service.ExternalRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external/restaurants")
public class ExternalRestaurantController {

    @Autowired
    private ExternalRestaurantService externalRestaurantService;

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> searchRestaurants(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon,
            @RequestParam(required = false) Double radius) {

        try {
            RestaurantSearchResponse response = externalRestaurantService.searchRestaurants(query, lat, lon, radius);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{restaurantId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getRestaurantDetails(@PathVariable String restaurantId) {
        try {
            Object response = externalRestaurantService.getRestaurantDetails(restaurantId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
