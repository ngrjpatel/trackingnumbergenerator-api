package com.valuelabs.trackingnumbergenerator.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingNumberResponse {

	private String trackingNumber;
    private String message;
    private LocalDateTime createdAt;
    private int status;

}

