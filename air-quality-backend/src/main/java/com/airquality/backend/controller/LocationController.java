package com.airquality.backend.controller;

import com.airquality.backend.entity.Location;
import com.airquality.backend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class LocationController {
    private final LocationService locationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location createLocation(
            @RequestBody Location location) {

        return locationService.createLocation(location);
    }

    @GetMapping
    public List<Location> getAllLocations() {

        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public Location getLocationById(
            @PathVariable Long id) {

        return locationService.getLocationById(id);
    }

    @PutMapping("/{id}")
    public Location updateLocation(
            @PathVariable Long id,
            @RequestBody Location location) {

        return locationService.updateLocation(id, location);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(
            @PathVariable Long id) {

        locationService.deleteLocation(id);
    }
}
