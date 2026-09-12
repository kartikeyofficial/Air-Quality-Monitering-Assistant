package com.airquality.backend.repository;

import com.airquality.backend.entity.AirQualityReading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirQualityReadingRepository extends JpaRepository<AirQualityReading, Long>{
    List<AirQualityReading> findByLocationIdOrderByRecordedAtDesc(
            Long locationId
    );

    Page<AirQualityReading> findByLocationId(
            Long locationId,
            Pageable pageable
    );
}
