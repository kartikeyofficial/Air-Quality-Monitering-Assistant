package com.airquality.backend.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder

public class PredictionResponse {
    private Long id;

    private Long locationId;

    private String city;

    private Double predictedPm25;

    private Double predictedAqi;

    private String predictedCategory;

    private LocalDateTime predictionFor;

    private LocalDateTime createdAt;
}
