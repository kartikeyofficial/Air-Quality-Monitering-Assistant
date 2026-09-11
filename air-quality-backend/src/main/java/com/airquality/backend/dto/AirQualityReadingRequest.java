package com.airquality.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class AirQualityReadingRequest {
    @PositiveOrZero(message = "PM2.5 cannot be negative")
    private Double pm25;

    @PositiveOrZero(message = "PM10 cannot be negative")
    private Double pm10;

    @PositiveOrZero(message = "CO cannot be negative")
    private Double co;

    @PositiveOrZero(message = "NO2 cannot be negative")
    private Double no2;

    @PositiveOrZero(message = "SO2 cannot be negative")
    private Double so2;

    @PositiveOrZero(message = "O3 cannot be negative")
    private Double o3;

    private Double temperature;

    @PositiveOrZero(message = "Humidity cannot be negative")
    private Double humidity;

    @PositiveOrZero(message = "Wind speed cannot be negative")
    private Double windSpeed;

    private Double pressure;

    @PositiveOrZero(message = "AQI cannot be negative")
    private Double aqi;

    private String aqiCategory;

    @NotNull(message = "Recorded time is required")
    private LocalDateTime recordedAt;
}
