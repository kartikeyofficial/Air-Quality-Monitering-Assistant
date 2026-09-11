package com.airquality.backend.service;
import com.airquality.backend.entity.AirQualityReading;

import java.util.List;

public interface AirQualityService {
    AirQualityReading createReading(
            Long locationId,
            AirQualityReading reading);

    List<AirQualityReading>
    getReadingsByLocation(Long locationId);

    AirQualityReading getReadingById(Long id);

    void deleteReading(Long id);
}
