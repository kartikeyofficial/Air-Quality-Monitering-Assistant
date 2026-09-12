package com.airquality.backend.service.impl;

import com.airquality.backend.dto.AirQualityReadingRequest;
import com.airquality.backend.dto.AirQualityReadingResponse;
import com.airquality.backend.entity.AirQualityReading;
import com.airquality.backend.entity.Location;
import com.airquality.backend.exception.ResourceNotFoundException;
import com.airquality.backend.repository.AirQualityReadingRepository;
import com.airquality.backend.repository.LocationRepository;
import com.airquality.backend.service.AirQualityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AirQualityServiceImpl  implements AirQualityService{

    private final AirQualityReadingRepository readingRepository;
    private final LocationRepository locationRepository;

    @Override
    public Page<AirQualityReadingResponse> getReadingsByLocation(
            Long locationId,
            Pageable pageable) {

        if (!locationRepository.existsById(locationId)) {
            throw new ResourceNotFoundException(
                    "Location with id " + locationId + " not found"
            );
        }

        return readingRepository
                .findByLocationId(locationId, pageable)
                .map(this::mapToResponse);
    }

    @Override
    public AirQualityReadingResponse createReading(
            Long locationId,
            AirQualityReadingRequest request) {

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Location with id " + locationId + " not found"
                        ));

        AirQualityReading reading = AirQualityReading.builder()
                .location(location)
                .pm25(request.getPm25())
                .pm10(request.getPm10())
                .co(request.getCo())
                .no2(request.getNo2())
                .so2(request.getSo2())
                .o3(request.getO3())
                .temperature(request.getTemperature())
                .humidity(request.getHumidity())
                .windSpeed(request.getWindSpeed())
                .pressure(request.getPressure())
                .aqi(request.getAqi())
                .aqiCategory(request.getAqiCategory())
                .recordedAt(request.getRecordedAt())
                .build();

        AirQualityReading saved = readingRepository.save(reading);

        return mapToResponse(saved);
    }

    @Override
    public List<AirQualityReadingResponse> getReadingsByLocation(
            Long locationId) {

        if (!locationRepository.existsById(locationId)) {
            throw new ResourceNotFoundException(
                    "Location with id " + locationId + " not found"
            );
        }

        return readingRepository
                .findByLocationIdOrderByRecordedAtDesc(locationId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AirQualityReadingResponse getReadingById(Long id) {

        AirQualityReading reading =
                readingRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Air quality reading with id "
                                                + id + " not found"
                                ));

        return mapToResponse(reading);
    }

    @Override
    public void deleteReading(Long id) {

        AirQualityReading reading =
                readingRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Air quality reading with id "
                                                + id + " not found"
                                ));

        readingRepository.delete(reading);
    }

    private AirQualityReadingResponse mapToResponse(
            AirQualityReading reading) {

        return AirQualityReadingResponse.builder()
                .id(reading.getId())
                .locationId(reading.getLocation().getId())
                .city(reading.getLocation().getCity())
                .pm25(reading.getPm25())
                .pm10(reading.getPm10())
                .co(reading.getCo())
                .no2(reading.getNo2())
                .so2(reading.getSo2())
                .o3(reading.getO3())
                .temperature(reading.getTemperature())
                .humidity(reading.getHumidity())
                .windSpeed(reading.getWindSpeed())
                .pressure(reading.getPressure())
                .aqi(reading.getAqi())
                .aqiCategory(reading.getAqiCategory())
                .recordedAt(reading.getRecordedAt())
                .build();
    }
}
