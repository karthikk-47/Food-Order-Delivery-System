package com.foodapp.deliveryexecutive.integration.external.service;

import com.foodapp.deliveryexecutive.integration.external.dto.RestaurantSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExternalRestaurantService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalRestaurantService.class);

    @Value("${external.api.key}")
    private String apiKey;

    @Value("${external.api.base-url:https://developers.zomato.com/api/v2.1}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public ExternalRestaurantService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Search for restaurants
     * 
     * @param query  Search keyword (e.g. "pizza")
     * @param lat    Latitude
     * @param lon    Longitude
     * @param radius Radius in meters (max 100000)
     * @return RestaurantSearchResponse
     */
    public RestaurantSearchResponse searchRestaurants(String query, Double lat, Double lon, Double radius) {
        String url = baseUrl + "/search";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("q", query);

        if (lat != null && lon != null) {
            builder.queryParam("lat", lat)
                    .queryParam("lon", lon);
            if (radius != null) {
                builder.queryParam("radius", radius);
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key", apiKey);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            logger.info("Calling External Search API: {}", builder.toUriString());
            ResponseEntity<RestaurantSearchResponse> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    RestaurantSearchResponse.class);
            return response.getBody();
        } catch (Exception e) {
            logger.error("Error calling External API", e);
            throw new RuntimeException("Failed to fetch data from external provider: " + e.getMessage());
        }
    }

    /**
     * Get Restaurant Details including Menu URL
     * 
     * @param restaurantId External Restaurant ID
     * @return Raw JSON Object (or mapped DTO if created)
     */
    public Object getRestaurantDetails(String restaurantId) {
        String url = baseUrl + "/restaurant";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("res_id", restaurantId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("user-key", apiKey);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    Object.class);
            return response.getBody();
        } catch (Exception e) {
            logger.error("Error calling External API for details", e);
            throw new RuntimeException("Failed to fetch restaurant details: " + e.getMessage());
        }
    }
}
