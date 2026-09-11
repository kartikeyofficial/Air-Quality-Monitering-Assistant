package com.airquality.backend.controller;

import com.airquality.backend.entity.AirQualityReading;
import com.airquality.backend.service.AirQualityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/air-quality")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class AirQualityController {

    private final AirQualityService airQualityService;

    @PostMapping("/location/{locationId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AirQualityReading createReading(
            @PathVariable Long locationId,
            @RequestBody AirQualityReading reading) {

        return airQualityService
                .createReading(locationId, reading);
    }

    @GetMapping("/location/{locationId}")
    public List<AirQualityReading> getReadings(
            @PathVariable Long locationId) {

        return airQualityService
                .getReadingsByLocation(locationId);
    }

    @GetMapping("/{id}")
    public AirQualityReading getReading(
            @PathVariable Long id) {

        return airQualityService
                .getReadingById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReading(
            @PathVariable Long id) {

        airQualityService.deleteReading(id);
    }
}
