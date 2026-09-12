package com.airquality.backend.controller;

import com.airquality.backend.dto.AirQualityReadingRequest;
import com.airquality.backend.dto.AirQualityReadingResponse;
import com.airquality.backend.service.AirQualityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/api/air-quality")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class AirQualityController {

    private final AirQualityService airQualityService;

    @PostMapping("/location/{locationId}")
    public ResponseEntity<AirQualityReadingResponse> createReading(
            @PathVariable Long locationId,
            @Valid @RequestBody AirQualityReadingRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        airQualityService.createReading(
                                locationId,
                                request
                        )
                );
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<Page<AirQualityReadingResponse>>
    getReadingsByLocation(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("recordedAt").descending()
        );

        return ResponseEntity.ok(
                airQualityService.getReadingsByLocation(
                        locationId,
                        pageable
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirQualityReadingResponse> getReadingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                airQualityService.getReadingById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReading(
            @PathVariable Long id) {

        airQualityService.deleteReading(id);

        return ResponseEntity.noContent().build();
    }
}
