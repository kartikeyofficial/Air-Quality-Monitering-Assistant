package com.airquality.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AlertRequest {
    private Long userId;

    @NotNull(message = "Location ID is required")
    private Long locationId;

    @NotBlank(message = "Alert type is required")
    private String alertType;

    @NotBlank(message = "Alert message is required")
    private String message;

    @NotBlank(message = "Severity is required")
    private String severity;
}
