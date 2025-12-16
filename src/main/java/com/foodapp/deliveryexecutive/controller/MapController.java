/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.databind.JsonNode
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.deliveryexecutive.map.dto.ReverseGeocodeResponseDTO;
import com.foodapp.deliveryexecutive.map.service.MapService;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {
    private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    @Autowired
    private MapService mapService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value={"/getAddress"})
    public ResponseEntity<?> getAddress(@RequestBody String body) {
        Object location;
        block6: {
            logger.info("Received /getAddress request body: {}", body);
            location = null;
            try {
                if (body != null) {
                    JsonNode node;
                    String trimmed = body.trim();
                    location = trimmed.startsWith("{") ? ((node = this.objectMapper.readTree(body)).has("location") ? node.get("location").asText() : (node.has("lat") && node.has("lng") ? node.get("lat").asText() + "," + node.get("lng").asText() : node.toString())) : body.replace("\"", "").trim();
                }
            }
            catch (Exception e) {
                logger.warn("Failed to parse getAddress request body, falling back to raw value", e);
                if (body == null) break block6;
                location = body.replace("\"", "").trim();
            }
        }
        logger.info("Parsed location for /getAddress: {}", location);
        if (location == null || ((String)location).isEmpty()) {
            HashMap<String, String> err = new HashMap<String, String>();
            err.put("error", "Location must be provided in request body");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        try {
            ReverseGeocodeResponseDTO dto = this.mapService.getAddress((String)location);
            return ResponseEntity.ok(dto);
        }
        catch (Exception ex) {
            logger.error("Error while reverse geocoding location {}: {}", new Object[]{location, ex.getMessage(), ex});
            HashMap<String, String> err = new HashMap<String, String>();
            err.put("error", "Failed to get address");
            err.put("message", ex.getMessage());
            err.put("exception", ex.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }
}
