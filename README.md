# Tracking Number Generator API

## Overview
This is a Spring Boot application that generates unique tracking numbers for parcels. It supports high concurrency and is scalable.

## Requirements
- Java 17 
- Maven

## Running the Application
1. Clone the repository.
2. Run the application using `mvn spring-boot:run`.
3. The application will run on `http://localhost:8081`.

## API Endpoint

### `GET /next-tracking-number`

#### Query Parameters:
- `origin_country_id`: The origin country code (e.g., "MY").
- `destination_country_id`: The destination country code (e.g., "ID").
- `weight`: The parcel weight in kilograms (e.g., "1.234").
- `created_at`: The order creation timestamp in RFC 3339 format.
- `customer_id`: The customer UUID.
- `customer_name`: The customer’s name.
- `customer_slug`: The customer’s slug.

#### Response:
```json
{
    "trackingNumber": "MYID1.234DE61985",
    "message": "Tracking number generated successfully.",
    "createdAt": "2024-12-24T17:05:45.3012153",
    "status": 200
}
