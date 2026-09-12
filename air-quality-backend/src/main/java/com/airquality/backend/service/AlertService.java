package com.airquality.backend.service;

import com.airquality.backend.dto.AlertRequest;
import com.airquality.backend.dto.AlertResponse;

import java.util.List;

public interface AlertService {
    AlertResponse createAlert(AlertRequest request);

    List<AlertResponse> getAlertsByUser(Long userId);

    List<AlertResponse> getAlertsByLocation(Long locationId);

    AlertResponse getAlertById(Long id);

    AlertResponse markAsRead(Long id);

    void deleteAlert(Long id);
}
