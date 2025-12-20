package com.foodapp.deliveryexecutive.integration.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class RestaurantSearchResponse {
    @JsonProperty("results_found")
    private Integer resultsFound;

    @JsonProperty("results_start")
    private Integer resultsStart;

    @JsonProperty("results_shown")
    private Integer resultsShown;

    private List<RestaurantWrapper> restaurants;

    @Data
    public static class RestaurantWrapper {
        private Restaurant restaurant;
    }

    @Data
    public static class Restaurant {
        private String id;
        private String name;
        private String url;
        private Location location;

        @JsonProperty("average_cost_for_two")
        private Integer averageCostForTwo;

        @JsonProperty("price_range")
        private Integer priceRange;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("thumb")
        private String thumbUrl;

        @JsonProperty("featured_image")
        private String featuredImage;

        @JsonProperty("photos_url")
        private String photosUrl;

        @JsonProperty("menu_url")
        private String menuUrl;

        @JsonProperty("events_url")
        private String eventsUrl;

        @JsonProperty("user_rating")
        private UserRating userRating;

        @JsonProperty("has_online_delivery")
        private Integer hasOnlineDelivery;

        @JsonProperty("is_delivering_now")
        private Integer isDeliveringNow;

        @JsonProperty("cuisines")
        private String cuisines;
    }

    @Data
    public static class Location {
        private String address;
        private String locality;
        private String city;

        @JsonProperty("city_id")
        private Integer cityId;

        private String latitude;
        private String longitude;

        @JsonProperty("zipcode")
        private String zipcode;

        @JsonProperty("country_id")
        private Integer countryId;
    }

    @Data
    public static class UserRating {
        @JsonProperty("aggregate_rating")
        private String aggregateRating;

        @JsonProperty("rating_text")
        private String ratingText;

        @JsonProperty("rating_color")
        private String ratingColor;

        @JsonProperty("votes")
        private String votes;
    }
}
