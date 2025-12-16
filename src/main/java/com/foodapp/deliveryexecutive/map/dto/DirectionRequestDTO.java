package com.foodapp.deliveryexecutive.map.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectionRequestDTO {
    private String origin;
    private String destination;
    private String mode = "driving";
    private boolean alternatives = false;
    private boolean steps = true;
    private String overview = "full";
    private String language = "en";
    @JsonProperty(value="traffic_metadata")
    private boolean trafficMetadata = false;

    public DirectionRequestDTO(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isAlternatives() {
        return this.alternatives;
    }

    public void setAlternatives(boolean alternatives) {
        this.alternatives = alternatives;
    }

    public boolean isSteps() {
        return this.steps;
    }

    public void setSteps(boolean steps) {
        this.steps = steps;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isTrafficMetadata() {
        return this.trafficMetadata;
    }

    public void setTrafficMetadata(boolean trafficMetadata) {
        this.trafficMetadata = trafficMetadata;
    }
}
