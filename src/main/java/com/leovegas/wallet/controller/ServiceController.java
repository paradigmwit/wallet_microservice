package com.leovegas.wallet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping("/")
    public ResponseEntity<String> getAvailability() {
        return ResponseEntity.ok("Odin be praised! Electricity courses through the networks, as it should!");
    }
}
