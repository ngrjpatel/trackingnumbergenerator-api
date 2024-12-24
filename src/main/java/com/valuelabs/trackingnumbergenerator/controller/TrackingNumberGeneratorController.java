package com.valuelabs.trackingnumbergenerator.controller;

import com.valuelabs.trackingnumbergenerator.exceptions.TrackingNumberException;
import com.valuelabs.trackingnumbergenerator.model.TrackingNumberResponse;
import com.valuelabs.trackingnumbergenerator.service.TrackingNumberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;

@RestController
public class TrackingNumberGeneratorController {
	private static final Logger logger = LoggerFactory.getLogger(TrackingNumberGeneratorController.class);

	private final TrackingNumberService trackingNumberService;

    @Autowired
    public TrackingNumberGeneratorController(TrackingNumberService trackingNumberService) {
        this.trackingNumberService = trackingNumberService;
    }

    @GetMapping("/next-tracking-number")
	public ResponseEntity<TrackingNumberResponse> generateTrackingNumber(@RequestParam String origin_country_id,
			@RequestParam String destination_country_id, @RequestParam Double weight, @RequestParam String created_at,
			@RequestParam String customer_id, @RequestParam String customer_name, @RequestParam String customer_slug) {
		logger.info("Received request to generate tracking number");

		validateParameter("origin_country_id", origin_country_id);
		validateParameter("destination_country_id", destination_country_id);
		validateParameter("weight", weight != null ? weight.toString() : null);
		validateParameter("created_at", created_at);
		validateParameter("customer_id", customer_id);
		validateParameter("customer_name", customer_name);
		validateParameter("customer_slug", customer_slug);

		logger.debug(
				"Request details - Origin: {}, Destination: {}, Weight: {}, Created At: {}, Customer ID: {}, Customer Name: {}, Customer Slug: {}",
				origin_country_id, destination_country_id, weight, created_at, customer_id, customer_name,
				customer_slug);

		String trackingNumber = trackingNumberService.generateTrackingNumber(origin_country_id, destination_country_id,
				weight, created_at, customer_id, customer_name, customer_slug);
		logger.info("Tracking number generated: {}", trackingNumber);
		return ResponseEntity.ok(new TrackingNumberResponse(trackingNumber, "Tracking number generated successfully.",
				LocalDateTime.now(), 200));
	}
    
	private void validateParameter(String parameterName, String parameterValue) {
		if (parameterValue == null || parameterValue.trim().isEmpty()) {
			throw new TrackingNumberException("Parameter '" + parameterName + "' cannot be null or empty.");
		}
	}
}
