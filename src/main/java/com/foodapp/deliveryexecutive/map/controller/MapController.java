package com.foodapp.deliveryexecutive.map.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.deliveryexecutive.map.dto.ReverseGeocodeResponseDTO;
import com.foodapp.deliveryexecutive.map.service.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/getAddress")
    public ResponseEntity<?> getAddress(@RequestBody String body) {
        log.info("Received /getAddress request body: {}", body);
        String location = null;
        
        try {
            if (body != null) {
                String trimmed = body.trim();
                if (trimmed.startsWith("{")) {
                    JsonNode node = objectMapper.readTree(body);
                    if (node.has("location")) {
                        location = node.get("location").asText();
                    } else if (node.has("lat") && node.has("lng")) {
                        location = node.get("lat").asText() + "," + node.get("lng").asText();
                    } else {
                        location = node.toString();
                    }
                } else {
                    location = body.replace("\"", "").trim();
                }
            }
        } catch (Exception e) {
            log.warn("Failed to parse getAddress request body, falling back to raw value", e);
            if (body != null) {
                location = body.replace("\"", "").trim();
            }
        }
        
        log.info("Parsed location for /getAddress: {}", location);
        
        if (location == null || location.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Location must be provided in request body"));
        }
        
        try {
            ReverseGeocodeResponseDTO dto = mapService.getAddress(location);
            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            log.error("Error while reverse geocoding location {}: {}", location, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Failed to get address",
                            "message", ex.getMessage(),
                            "exception", ex.getClass().getName()
                    ));
        }
    }
}
