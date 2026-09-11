package com.airquality.backend.service;

import com.airquality.backend.entity.Location;

import java.util.List;

public interface LocationService {
    Location createLocation(Location location);

    List<Location> getAllLocations();

    Location getLocationById(Long id);

    Location updateLocation(Long id, Location location);

    void deleteLocation(Long id);
}
