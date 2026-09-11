package com.airquality.backend.service;

import com.airquality.backend.dto.AirQualityReadingRequest;
import com.airquality.backend.dto.AirQualityReadingResponse;

import java.util.List;

public interface AirQualityService {
    AirQualityReadingResponse createReading(
            Long locationId,
            AirQualityReadingRequest request
    );

    List<AirQualityReadingResponse> getReadingsByLocation(
            Long locationId
    );

    AirQualityReadingResponse getReadingById(Long id);

    void deleteReading(Long id);
}
