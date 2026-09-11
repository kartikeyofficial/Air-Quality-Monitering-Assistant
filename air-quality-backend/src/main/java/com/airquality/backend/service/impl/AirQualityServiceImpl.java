package com.airquality.backend.service.impl;
import com.airquality.backend.entity.AirQualityReading;
import com.airquality.backend.entity.Location;
import com.airquality.backend.repository.AirQualityReadingRepository;
import com.airquality.backend.repository.LocationRepository;
import com.airquality.backend.service.AirQualityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AirQualityServiceImpl  implements AirQualityService{


        private final AirQualityReadingRepository readingRepository;
        private final LocationRepository locationRepository;

        @Override
        public AirQualityReading createReading(
                Long locationId,
                AirQualityReading reading) {

            Location location = locationRepository
                    .findById(locationId)
                    .orElseThrow(() ->
                            new RuntimeException("Location not found"));

            reading.setLocation(location);

            return readingRepository.save(reading);
        }

        @Override
        public List<AirQualityReading>
        getReadingsByLocation(Long locationId) {

            return readingRepository
                    .findByLocationIdOrderByRecordedAtDesc(locationId);
        }

        @Override
        public AirQualityReading getReadingById(Long id) {

            return readingRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Air quality reading not found"));
        }

        @Override
        public void deleteReading(Long id) {

            AirQualityReading reading =
                    getReadingById(id);

            readingRepository.delete(reading);
        }
}
