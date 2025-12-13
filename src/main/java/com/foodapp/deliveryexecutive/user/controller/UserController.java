/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.user.controller;

import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import com.foodapp.deliveryexecutive.map.service.MapService;
import com.foodapp.deliveryexecutive.map.dto.ReverseGeocodeResponseDTO;
import com.foodapp.deliveryexecutive.map.dto.Result;
import com.foodapp.deliveryexecutive.map.dto.Location;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/users"})
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapService mapService;

    @GetMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable Long id) {
        log.info("Fetching user profile for ID: {}", (Object)id);
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            HashMap<String, Object> profile = new HashMap<String, Object>();
            profile.put("id", user.getId());
            profile.put("name", user.getName());
            profile.put("mobile", user.getMobile());
            profile.put("email", user.getEmail());
            profile.put("address", user.getAddress());
            profile.put("role", (Object)user.getRole());
            return ResponseEntity.ok(profile);
        }
        catch (Exception e) {
            log.error("Error fetching user profile", (Throwable)e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<Map<String, Object>> updateUserProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        log.info("Updating user profile for ID: {}", (Object)id);
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            if (updates.containsKey("name")) {
                user.setName((String)updates.get("name"));
            }
            if (updates.containsKey("email")) {
                user.setEmail((String)updates.get("email"));
            }
            if (updates.containsKey("address")) {
                user.setAddress((String)updates.get("address"));
            }
            this.userRepository.save(user);
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("success", true);
            response.put("message", "Profile updated successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error updating user profile", (Throwable)e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value={"/{id}/address-with-geocoding"})
    public ResponseEntity<Map<String, Object>> updateUserAddressWithGeocoding(@PathVariable Long id, @RequestBody Map<String, Object> addressData) {
        log.info("Updating user address with geocoding for ID: {}", (Object)id);
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            if (addressData.containsKey("address")) {
                String address = (String)addressData.get("address");
                user.setAddress(address);

                // Geocode the address to get GPS coordinates
                try {
                    ReverseGeocodeResponseDTO response = this.mapService.getAddress(address);
                    if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                        Result result = response.getResults().get(0);
                        if (result != null && result.getGeometry() != null && result.getGeometry().getLocation() != null) {
                            Location location = result.getGeometry().getLocation();
                            Point point = new Point(location.getLat(), location.getLng());
                            user.setLocation(point);
                            log.info("Geocoded address {} to location: {}", (Object)address, (Object)point);
                        }
                    }
                } catch (Exception e) {
                    log.warn("Failed to geocode address: {}", (Object)address, (Object)e);
                    // Continue without location if geocoding fails
                }
            }

            this.userRepository.save(user);

            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("success", true);
            response.put("message", "Address updated successfully with geocoding");
            if (user.getLocation() != null) {
                response.put("latitude", user.getLocation().getX());
                response.put("longitude", user.getLocation().getY());
            }
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error updating user address with geocoding", (Throwable)e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value={"/{id}/location"})
    public ResponseEntity<Map<String, Object>> getUserLocation(@PathVariable Long id) {
        log.info("Fetching location for user ID: {}", (Object)id);
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            HashMap<String, Object> locationData = new HashMap<String, Object>();
            if (user.getLocation() != null) {
                locationData.put("latitude", user.getLocation().getX());
                locationData.put("longitude", user.getLocation().getY());
                locationData.put("address", user.getAddress());
            } else {
                locationData.put("latitude", null);
                locationData.put("longitude", null);
                locationData.put("address", user.getAddress());
            }
            return ResponseEntity.ok(locationData);
        }
        catch (Exception e) {
            log.error("Error fetching user location", (Throwable)e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value={"/{id}/location"})
    public ResponseEntity<Map<String, Object>> updateUserLocation(@PathVariable Long id, @RequestBody Map<String, Object> locationData) {
        log.info("Updating location for user ID: {}", (Object)id);
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            if (locationData.containsKey("latitude") && locationData.containsKey("longitude")) {
                double latitude = Double.parseDouble(locationData.get("latitude").toString());
                double longitude = Double.parseDouble(locationData.get("longitude").toString());
                user.setLocation(new Point(latitude, longitude));
            }

            if (locationData.containsKey("address")) {
                user.setAddress((String)locationData.get("address"));
            }

            this.userRepository.save(user);

            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("success", true);
            response.put("message", "Location updated successfully");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            log.error("Error updating user location", (Throwable)e);
            return ResponseEntity.badRequest().build();
        }
    }
}
