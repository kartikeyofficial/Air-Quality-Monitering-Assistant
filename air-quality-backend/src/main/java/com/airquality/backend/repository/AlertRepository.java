package com.airquality.backend.repository;

import com.airquality.backend.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long>{
    List<Alert> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Alert> findByLocationIdOrderByCreatedAtDesc(Long locationId);

    List<Alert> findByUserIdAndIsReadOrderByCreatedAtDesc(
            Long userId,
            Boolean isRead
    );
}
