package com.valuelabs.trackingnumbergenerator.exceptions;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.valuelabs.trackingnumbergenerator.model.TrackingNumberResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	 private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	 
	// Handle generic exceptions
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<TrackingNumberResponse> handleGlobalException(Exception ex, WebRequest request) {
	        logger.error("Exception occurred: {}", ex.getMessage(), ex);

	        TrackingNumberResponse response = new TrackingNumberResponse(
	                null,
	                ex.getMessage(),
	                LocalDateTime.now(),
	                HttpStatus.INTERNAL_SERVER_ERROR.value()
	        );

	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    // Handle specific custom exceptions
	    @ExceptionHandler(TrackingNumberException.class)
	    public ResponseEntity<TrackingNumberResponse> handleTrackingNumberException(TrackingNumberException ex, WebRequest request) {
	        logger.error("TrackingNumberException occurred: {}", ex.getMessage());

	        TrackingNumberResponse response = new TrackingNumberResponse(
	                null,
	                ex.getMessage(),
	                LocalDateTime.now(),
	                HttpStatus.BAD_REQUEST.value()
	        );

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	    }
}
