package com.leovegas.wallet.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Service Availability")
public class ServiceController {

    @ApiOperation(value = "View a cheeky service up message", response = String.class)
    @GetMapping("/")
    public ResponseEntity<String> getAvailability() {
        return ResponseEntity.ok("Odin be praised! Electricity courses through the networks, as it should! " +
                "<br><br>" +
                "Api-Doc here - <a href='/swagger-ui.html#/'> Api-Doc</a>");
    }
}
