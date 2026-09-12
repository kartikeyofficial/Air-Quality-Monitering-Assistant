package com.airquality.backend.service;

import com.airquality.backend.dto.PredictionRequest;
import com.airquality.backend.dto.PredictionResponse;

import java.util.List;

public interface PredictionService {
    PredictionResponse createPrediction(
            Long locationId,
            PredictionRequest request
    );

    List<PredictionResponse> getPredictionsByLocation(
            Long locationId
    );

    PredictionResponse getPredictionById(Long id);

    void deletePrediction(Long id);
}
