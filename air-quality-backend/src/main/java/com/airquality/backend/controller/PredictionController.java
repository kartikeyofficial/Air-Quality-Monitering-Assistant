package com.airquality.backend.controller;

import com.airquality.backend.dto.PredictionRequest;
import com.airquality.backend.dto.PredictionResponse;
import com.airquality.backend.service.PredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class PredictionController {
    private final PredictionService predictionService;

    @PostMapping("/location/{locationId}")
    public ResponseEntity<PredictionResponse> createPrediction(
            @PathVariable Long locationId,
            @Valid @RequestBody PredictionRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        predictionService.createPrediction(
                                locationId,
                                request
                        )
                );
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<PredictionResponse>>
    getPredictionsByLocation(
            @PathVariable Long locationId) {

        return ResponseEntity.ok(
                predictionService.getPredictionsByLocation(locationId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredictionResponse> getPredictionById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                predictionService.getPredictionById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrediction(
            @PathVariable Long id) {

        predictionService.deletePrediction(id);

        return ResponseEntity.noContent().build();
    }
}
