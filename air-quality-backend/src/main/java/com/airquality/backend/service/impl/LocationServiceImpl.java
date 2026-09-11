package com.airquality.backend.service.impl;

import com.airquality.backend.dto.LocationRequest;
import com.airquality.backend.dto.LocationResponse;
import com.airquality.backend.entity.Location;
import com.airquality.backend.exception.ResourceNotFoundException;
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
    public LocationResponse createLocation(LocationRequest request) {

        Location location = Location.builder()
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

        Location savedLocation = locationRepository.save(location);

        return mapToResponse(savedLocation);
    }

    @Override
    public List<LocationResponse> getAllLocations() {

        return locationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public LocationResponse getLocationById(Long id) {

        Location location = locationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Location with id " + id + " not found"
                        ));

        return mapToResponse(location);
    }

    @Override
    public LocationResponse updateLocation(
            Long id,
            LocationRequest request) {

        Location location = locationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Location with id " + id + " not found"
                        ));

        location.setCity(request.getCity());
        location.setState(request.getState());
        location.setCountry(request.getCountry());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());

        Location updatedLocation = locationRepository.save(location);

        return mapToResponse(updatedLocation);
    }

    @Override
    public void deleteLocation(Long id) {

        Location location = locationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Location with id " + id + " not found"
                        ));

        locationRepository.delete(location);
    }

    private LocationResponse mapToResponse(Location location) {

        return LocationResponse.builder()
                .id(location.getId())
                .city(location.getCity())
                .state(location.getState())
                .country(location.getCountry())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }


}
