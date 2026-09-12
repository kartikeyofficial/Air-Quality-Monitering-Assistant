package com.airquality.backend.service.impl;

import com.airquality.backend.dto.PredictionRequest;
import com.airquality.backend.dto.PredictionResponse;
import com.airquality.backend.entity.Location;
import com.airquality.backend.entity.Prediction;
import com.airquality.backend.exception.ResourceNotFoundException;
import com.airquality.backend.repository.LocationRepository;
import com.airquality.backend.repository.PredictionRepository;
import com.airquality.backend.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PredictionServiceImpl implements PredictionService{
    private final PredictionRepository predictionRepository;
    private final LocationRepository locationRepository;

    @Override
    public PredictionResponse createPrediction(
            Long locationId,
            PredictionRequest request) {

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Location with id " + locationId + " not found"
                        ));

        Prediction prediction = Prediction.builder()
                .location(location)
                .predictedPm25(request.getPredictedPm25())
                .predictedAqi(request.getPredictedAqi())
                .predictedCategory(request.getPredictedCategory())
                .predictionFor(request.getPredictionFor())
                .createdAt(LocalDateTime.now())
                .build();

        Prediction saved = predictionRepository.save(prediction);

        return mapToResponse(saved);
    }

    @Override
    public List<PredictionResponse> getPredictionsByLocation(
            Long locationId) {

        if (!locationRepository.existsById(locationId)) {
            throw new ResourceNotFoundException(
                    "Location with id " + locationId + " not found"
            );
        }

        return predictionRepository
                .findByLocationIdOrderByPredictionForAsc(locationId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PredictionResponse getPredictionById(Long id) {

        Prediction prediction = predictionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Prediction with id " + id + " not found"
                        ));

        return mapToResponse(prediction);
    }

    @Override
    public void deletePrediction(Long id) {

        Prediction prediction = predictionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Prediction with id " + id + " not found"
                        ));

        predictionRepository.delete(prediction);
    }

    private PredictionResponse mapToResponse(Prediction prediction) {

        return PredictionResponse.builder()
                .id(prediction.getId())
                .locationId(prediction.getLocation().getId())
                .city(prediction.getLocation().getCity())
                .predictedPm25(prediction.getPredictedPm25())
                .predictedAqi(prediction.getPredictedAqi())
                .predictedCategory(prediction.getPredictedCategory())
                .predictionFor(prediction.getPredictionFor())
                .createdAt(prediction.getCreatedAt())
                .build();
    }

}
