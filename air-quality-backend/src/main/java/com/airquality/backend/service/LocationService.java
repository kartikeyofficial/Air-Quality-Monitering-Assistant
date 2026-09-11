package com.airquality.backend.service;


import com.airquality.backend.dto.LocationRequest;
import com.airquality.backend.dto.LocationResponse;

import java.util.List;

public interface LocationService {
    LocationResponse createLocation(LocationRequest request);

    List<LocationResponse> getAllLocations();

    LocationResponse getLocationById(Long id);

    LocationResponse updateLocation(Long id, LocationRequest request);

    void deleteLocation(Long id);
}
