package com.airquality.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class PredictionRequest {

    @PositiveOrZero(message = "Predicted PM2.5 cannot be negative")
    private Double predictedPm25;

    @PositiveOrZero(message = "Predicted AQI cannot be negative")
    private Double predictedAqi;

    private String predictedCategory;

    @NotNull(message = "Prediction time is required")
    private LocalDateTime predictionFor;
}
