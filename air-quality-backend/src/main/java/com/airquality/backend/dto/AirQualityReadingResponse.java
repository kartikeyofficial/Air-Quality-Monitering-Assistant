package com.airquality.backend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder

public class AirQualityReadingResponse {
    private Long id;

    private Long locationId;

    private String city;

    private Double pm25;
    private Double pm10;
    private Double co;
    private Double no2;
    private Double so2;
    private Double o3;

    private Double temperature;
    private Double humidity;
    private Double windSpeed;
    private Double pressure;

    private Double aqi;
    private String aqiCategory;

    private LocalDateTime recordedAt;
}
