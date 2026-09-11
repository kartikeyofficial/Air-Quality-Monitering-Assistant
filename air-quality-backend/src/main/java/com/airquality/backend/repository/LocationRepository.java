package com.airquality.backend.repository;

import com.airquality.backend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long>  {
}
