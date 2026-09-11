package com.airquality.backend.service.impl;
import com.airquality.backend.entity.Location;
import com.airquality.backend.repository.LocationRepository;
import com.airquality.backend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;

    @Override
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Location not found"));
    }

    @Override
    public Location updateLocation(Long id, Location location) {

        Location existing = getLocationById(id);

        existing.setCity(location.getCity());
        existing.setState(location.getState());
        existing.setCountry(location.getCountry());
        existing.setLatitude(location.getLatitude());
        existing.setLongitude(location.getLongitude());

        return locationRepository.save(existing);
    }

    @Override
    public void deleteLocation(Long id) {

        Location location = getLocationById(id);

        locationRepository.delete(location);
    }

}
