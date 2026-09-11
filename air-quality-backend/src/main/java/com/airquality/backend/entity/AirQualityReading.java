package com.airquality.backend.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "air_quality_readings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AirQualityReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

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
