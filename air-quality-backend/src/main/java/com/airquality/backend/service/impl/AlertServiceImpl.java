package com.airquality.backend.service.impl;

import com.airquality.backend.dto.AlertRequest;
import com.airquality.backend.dto.AlertResponse;
import com.airquality.backend.entity.Alert;
import com.airquality.backend.entity.Location;
import com.airquality.backend.entity.User;
import com.airquality.backend.exception.ResourceNotFoundException;
import com.airquality.backend.repository.AlertRepository;
import com.airquality.backend.repository.LocationRepository;
import com.airquality.backend.repository.UserRepository;
import com.airquality.backend.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class AlertServiceImpl implements AlertService {
    private final AlertRepository alertRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Override
    public AlertResponse createAlert(AlertRequest request) {

        Location location = locationRepository
                .findById(request.getLocationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Location with id "
                                        + request.getLocationId()
                                        + " not found"
                        ));

        User user = null;

        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "User with id "
                                            + request.getUserId()
                                            + " not found"
                            ));
        }

        Alert alert = Alert.builder()
                .user(user)
                .location(location)
                .alertType(request.getAlertType())
                .message(request.getMessage())
                .severity(request.getSeverity())
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        Alert saved = alertRepository.save(alert);

        return mapToResponse(saved);
    }

    @Override
    public List<AlertResponse> getAlertsByUser(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(
                    "User with id " + userId + " not found"
            );
        }

        return alertRepository
                .findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AlertResponse> getAlertsByLocation(Long locationId) {

        if (!locationRepository.existsById(locationId)) {
            throw new ResourceNotFoundException(
                    "Location with id " + locationId + " not found"
            );
        }

        return alertRepository
                .findByLocationIdOrderByCreatedAtDesc(locationId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AlertResponse getAlertById(Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Alert with id " + id + " not found"
                        ));

        return mapToResponse(alert);
    }

    @Override
    public AlertResponse markAsRead(Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Alert with id " + id + " not found"
                        ));

        alert.setIsRead(true);

        return mapToResponse(alertRepository.save(alert));
    }

    @Override
    public void deleteAlert(Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Alert with id " + id + " not found"
                        ));

        alertRepository.delete(alert);
    }

    private AlertResponse mapToResponse(Alert alert) {

        return AlertResponse.builder()
                .id(alert.getId())
                .userId(
                        alert.getUser() != null
                                ? alert.getUser().getId()
                                : null
                )
                .locationId(
                        alert.getLocation() != null
                                ? alert.getLocation().getId()
                                : null
                )
                .city(
                        alert.getLocation() != null
                                ? alert.getLocation().getCity()
                                : null
                )
                .alertType(alert.getAlertType())
                .message(alert.getMessage())
                .severity(alert.getSeverity())
                .isRead(alert.getIsRead())
                .createdAt(alert.getCreatedAt())
                .build();
    }
}
