package com.airquality.backend.service;

import com.airquality.backend.dto.AirQualityReadingRequest;
import com.airquality.backend.dto.AirQualityReadingResponse;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AirQualityService {
    Page<AirQualityReadingResponse> getReadingsByLocation(
            Long locationId,
            Pageable pageable
    );

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
