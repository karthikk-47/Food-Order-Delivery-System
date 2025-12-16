package com.foodapp.deliveryexecutive.payments.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.deliveryexecutive.payments.dto.CreateContactRequest;
import com.foodapp.deliveryexecutive.payments.dto.CreateContactResponse;
import com.foodapp.deliveryexecutive.payments.dto.CreateFundAccountRequest;
import com.foodapp.deliveryexecutive.payments.dto.CreateFundAccountResponse;
import com.foodapp.deliveryexecutive.payments.dto.PayoutRequest;
import com.foodapp.deliveryexecutive.payments.dto.PayoutResponse;
import com.foodapp.deliveryexecutive.payments.dto.PayoutStatusResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentsApi {
    @Value(value="${razorpay.key}")
    private String razorpayKey;
    @Value(value="${razorpay.secret}")
    private String razorpaySecret;
    private static final Logger logger = LoggerFactory.getLogger(PaymentsApi.class);
    ObjectMapper mapper = new ObjectMapper();

    private String getAuthHeader() {
        String auth = this.razorpayKey + ":" + this.razorpaySecret;
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
    }

    public CreateContactResponse createContact(CreateContactRequest req) throws Exception {
        String url = "https://api.razorpay.com/v1/contacts";
        String contactReq = this.mapper.writeValueAsString(req);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").header("Authorization", this.getAuthHeader()).POST(HttpRequest.BodyPublishers.ofString(contactReq)).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            logger.error("Contact creation failed with status code: {} and response: {}", response.statusCode(), response.body());
            return null;
        }
        return (CreateContactResponse)this.mapper.readValue(response.body(), CreateContactResponse.class);
    }

    public CreateFundAccountResponse createFundAccount(CreateFundAccountRequest req) throws Exception {
        String url = "https://api.razorpay.com/v1/fund_accounts";
        String fundAccReq = this.mapper.writeValueAsString(req);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").header("Authorization", this.getAuthHeader()).POST(HttpRequest.BodyPublishers.ofString(fundAccReq)).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            logger.error("Fund account creation failed with status code: {} and response: {}", response.statusCode(), response.body());
            return null;
        }
        try {
            return (CreateFundAccountResponse)this.mapper.readValue(response.body(), CreateFundAccountResponse.class);
        }
        catch (Exception e) {
            logger.error("Json parse exception while parsing fund account response: {}", e.getMessage(), e);
            return null;
        }
    }

    public PayoutResponse makePayout(PayoutRequest req) throws Exception {
        String url = "https://api.razorpay.com/v1/payouts";
        String payoutReq = this.mapper.writeValueAsString(req);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").header("Authorization", this.getAuthHeader()).POST(HttpRequest.BodyPublishers.ofString(payoutReq)).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            logger.error("Payout failed with status code: {} and response: {}", response.statusCode(), response.body());
            return null;
        }
        try {
            return (PayoutResponse)this.mapper.readValue(response.body(), PayoutResponse.class);
        }
        catch (Exception e) {
            logger.error("Json parse exception while parsing payout response: {}", e.getMessage(), e);
            return null;
        }
    }

    public String createRazorpayOrder(Double amount, String currency, String receipt, String notes) throws Exception {
        String url = "https://api.razorpay.com/v1/orders";
        String requestBody = String.format("{\"amount\":%d,\"currency\":\"%s\",\"receipt\":\"%s\",\"notes\":{\"description\":\"%s\"}}", (int)(amount * 100.0), currency, receipt, notes);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").header("Authorization", this.getAuthHeader()).POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            logger.error("Razorpay order creation failed with status code: {} and response: {}", response.statusCode(), response.body());
            throw new RuntimeException("Failed to create Razorpay order: " + response.body());
        }
        return response.body();
    }

    public PayoutStatusResponse getPayoutStatus(String payoutId) throws Exception {
        String url = "https://api.razorpay.com/v1/payouts/" + payoutId;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Authorization", this.getAuthHeader()).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            logger.error("Get payout status failed with status code: {} and response: {}", response.statusCode(), response.body());
            return null;
        }
        try {
            return (PayoutStatusResponse)this.mapper.readValue(response.body(), PayoutStatusResponse.class);
        }
        catch (Exception e) {
            logger.error("Json parse exception while parsing payout status response: {}", e.getMessage(), e);
            return null;
        }
    }

    public String cancelPayout(String payoutId) throws Exception {
        String url = "https://api.razorpay.com/v1/payouts/" + payoutId + "/cancel";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Authorization", this.getAuthHeader()).POST(HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            logger.error("Cancel payout failed with status code: {} and response: {}", response.statusCode(), response.body());
            return null;
        }
        return response.body();
    }

    public String fetchPaymentDetails(String paymentId) throws Exception {
        String url = "https://api.razorpay.com/v1/payments/" + paymentId;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Authorization", this.getAuthHeader()).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            logger.error("Fetch payment details failed with status code: {} and response: {}", response.statusCode(), response.body());
            return null;
        }
        return response.body();
    }

    public String initiateRefund(String paymentId, Double amount, String notes) throws Exception {
        String url = "https://api.razorpay.com/v1/payments/" + paymentId + "/refund";
        String requestBody = amount != null ? String.format("{\"amount\":%d,\"notes\":{\"reason\":\"%s\"}}", (int)(amount * 100.0), notes) : String.format("{\"notes\":{\"reason\":\"%s\"}}", notes);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").header("Authorization", this.getAuthHeader()).POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            logger.error("Refund initiation failed with status code: {} and response: {}", response.statusCode(), response.body());
            throw new RuntimeException("Failed to initiate refund: " + response.body());
        }
        return response.body();
    }

    public String getRazorpayKey() {
        return this.razorpayKey;
    }
}
