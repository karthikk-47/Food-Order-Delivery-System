package com.foodapp.deliveryexecutive.common.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api"})
public class HomeController {
    @GetMapping(value={"/home"})
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok(Map.of("message", "Food-app API is running"));
    }
}
