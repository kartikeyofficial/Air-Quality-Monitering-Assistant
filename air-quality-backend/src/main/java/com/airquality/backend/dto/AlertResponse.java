package com.airquality.backend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder

public class AlertResponse {
    private Long id;

    private Long userId;

    private Long locationId;

    private String city;

    private String alertType;

    private String message;

    private String severity;

    private Boolean isRead;

    private LocalDateTime createdAt;
}
