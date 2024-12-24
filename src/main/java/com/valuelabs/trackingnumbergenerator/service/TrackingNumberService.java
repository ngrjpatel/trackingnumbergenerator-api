package com.valuelabs.trackingnumbergenerator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.valuelabs.trackingnumbergenerator.controller.TrackingNumberGeneratorController;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TrackingNumberService {
	private static final Logger logger = LoggerFactory.getLogger(TrackingNumberService.class);

    private final AtomicLong counter = new AtomicLong();
    private final Set<String> generatedTrackingNumbers = new HashSet<>();

   
	public String generateTrackingNumber(String originCountryId, String destinationCountryId, Double weight,
			String createdAt, String customerId, String customerName, String customerSlug) {
		logger.info("TrackingNumberService::generateTrackingNumber initiated");
		String base = originCountryId + destinationCountryId + weight.toString() + customerId;
		String trackingNumber = base.toUpperCase() + generateUniquePart();
		while (generatedTrackingNumbers.contains(trackingNumber)) {
			trackingNumber = base.toUpperCase() + "-" + generateUniquePart();
		}

		generatedTrackingNumbers.add(trackingNumber);
		logger.info("TrackingNumberService::generateTrackingNumber completed");
		return trackingNumber.substring(0, 16).toUpperCase();
	}

	private String generateUniquePart() {
		long count = counter.incrementAndGet();
		String uniquePart = UUID.randomUUID().toString().substring(0, 8) + count;
		return uniquePart.substring(0, 8);
	}
}

