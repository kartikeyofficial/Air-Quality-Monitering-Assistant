package com.airquality.backend.repository;
import com.airquality.backend.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    List<Prediction>
    findByLocationIdOrderByPredictionForAsc(Long locationId);
}
