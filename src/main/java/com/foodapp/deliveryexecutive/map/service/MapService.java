/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.data.geo.Point
 *  org.springframework.stereotype.Service
 */
package com.foodapp.deliveryexecutive.map.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.deliveryexecutive.map.dto.DirectionResponseDTO;
import com.foodapp.deliveryexecutive.map.dto.DistanceMatrixResponseDTO;
import com.foodapp.deliveryexecutive.map.dto.ReverseGeocodeResponseDTO;
import com.foodapp.deliveryexecutive.order.entity.Order;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class MapService {
    private static final Logger logger = LoggerFactory.getLogger(MapService.class);
    private static final int MAX_DISTANCE_METERS = 5000;
    private static final int REQUEST_TIMEOUT_SECONDS = 10;
    @Value(value="${olamap.api.key}")
    private String apiKey;
    private final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10L)).build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ReverseGeocodeResponseDTO getAddress(Point location) {
        String latlng = location.getX() + "," + location.getY();
        return this.getAddress(latlng);
    }

    public ReverseGeocodeResponseDTO getAddress(String latlng) {
        try {
            if (this.apiKey == null || this.apiKey.trim().isEmpty()) {
                logger.error("OLAMAP API key not configured (olamap.api.key is empty)");
                throw new RuntimeException("OLAMAP API key not configured");
            }
            String url = String.format("https://api.olamaps.io/places/v1/reverse-geocode?api_key=%s&latlng=%s", this.apiKey, latlng);
            String maskedKey = this.apiKey.length() > 6 ? this.apiKey.substring(0, 4) + "..." + this.apiKey.substring(this.apiKey.length() - 2) : "***";
            logger.info("Calling reverse-geocode: url={} (api_key={})", url, maskedKey);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().timeout(Duration.ofSeconds(10L)).build();
            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int status = response.statusCode();
            String body = response.body();
            logger.debug("Reverse geocode response status={} body={}", status, body);
            if (status != 200) {
                logger.error("Reverse geocode API failed with status: {} for location: {}. Response body: {}", new Object[]{status, latlng, body});
                throw new RuntimeException("Failed to get address for location: " + latlng + "; status=" + status + "; body=" + body);
            }
            try {
                return (ReverseGeocodeResponseDTO)this.objectMapper.readValue(body, ReverseGeocodeResponseDTO.class);
            }
            catch (IOException e) {
                logger.error("Failed to parse reverse geocode response for location: {}. responseBody={}", new Object[]{latlng, body, e});
                throw new RuntimeException("Failed to parse reverse geocode response", e);
            }
        }
        catch (IOException | InterruptedException e) {
            logger.error("Error calling reverse geocode API for location: {}", latlng, e);
            throw new RuntimeException("Failed to get address", e);
        }
    }

    public DirectionResponseDTO getRoute(Point origin, Point destination) {
        String originStr = origin.getX() + "," + origin.getY();
        String destinationStr = destination.getX() + "," + destination.getY();
        return this.getRoute(originStr, destinationStr);
    }

    public DirectionResponseDTO getRoute(String origin, String destination) {
        try {
            String url = String.format("https://api.olamaps.io/routing/v1/directions?api_key=%s&origin=%s&destination=%s", this.apiKey, origin, destination);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.noBody()).timeout(Duration.ofSeconds(10L)).build();
            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                logger.error("Routing API failed with status: {} for route: {} -> {}", new Object[]{response.statusCode(), origin, destination});
                throw new RuntimeException("Failed to get route");
            }
            return (DirectionResponseDTO)this.objectMapper.readValue(response.body(), DirectionResponseDTO.class);
        }
        catch (IOException | InterruptedException e) {
            logger.error("Error calling routing API for route: {} -> {}", new Object[]{origin, destination, e});
            throw new RuntimeException("Failed to get route", e);
        }
    }

    public Map<Long, Integer> calculateDistancesToOrders(Point executiveLocation, List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new HashMap<Long, Integer>();
        }
        try {
            String origin = executiveLocation.getX() + "," + executiveLocation.getY();
            String destinations = orders.stream().filter(o -> o.getPickupLocation() != null).map(o -> o.getPickupLocation().getX() + "," + o.getPickupLocation().getY()).collect(Collectors.joining("|"));
            if (destinations.isEmpty()) {
                logger.warn("No valid pickup locations found in orders");
                return new HashMap<Long, Integer>();
            }
            DistanceMatrixResponseDTO distanceMatrix = this.getDistanceMatrix(origin, destinations);
            HashMap<Long, Integer> result = new HashMap<Long, Integer>();
            List distances = distanceMatrix.getRows().stream().flatMap(row -> row.getElements().stream().map(el -> el.getDistance())).collect(Collectors.toList());
            int i = 0;
            while (i < Math.min(orders.size(), distances.size())) {
                int distance = (Integer)distances.get(i);
                if (distance <= 5000) {
                    result.put(orders.get(i).getId(), distance);
                }
                ++i;
            }
            logger.info("Found {} nearby orders within {} meters", result.size(), (Object)5000);
            return result;
        }
        catch (Exception e) {
            logger.error("Error calculating distances to orders", e);
            return new HashMap<Long, Integer>();
        }
    }

    public DistanceMatrixResponseDTO getDistanceMatrix(String origin, String destinations) {
        try {
            String url = String.format("https://api.olamaps.io/routing/v1/distanceMatrix?api_key=%s&origin=%s&destinations=%s", this.apiKey, origin, destinations);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().timeout(Duration.ofSeconds(10L)).build();
            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                logger.error("Distance matrix API failed with status: {}", response.statusCode());
                throw new RuntimeException("Failed to get distance matrix");
            }
            return (DistanceMatrixResponseDTO)this.objectMapper.readValue(response.body(), DistanceMatrixResponseDTO.class);
        }
        catch (IOException | InterruptedException e) {
            logger.error("Error calling distance matrix API", e);
            throw new RuntimeException("Failed to get distance matrix", e);
        }
    }

    public int calculateDistance(Point point1, Point point2) {
        try {
            String origin = point1.getX() + "," + point1.getY();
            String destination = point2.getX() + "," + point2.getY();
            DistanceMatrixResponseDTO matrix = this.getDistanceMatrix(origin, destination);
            return matrix.getRows().stream().flatMap(row -> row.getElements().stream()).findFirst().map(el -> el.getDistance()).orElse(0);
        }
        catch (Exception e) {
            logger.error("Error calculating distance between points", e);
            return 0;
        }
    }

    public boolean isWithinRange(Point point1, Point point2, int maxDistanceMeters) {
        int distance = this.calculateDistance(point1, point2);
        return distance <= maxDistanceMeters;
    }
}
