package com.airquality.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class LocationResponse {

    private Long id;
    private String city;
    private String state;
    private String country;
    private Double latitude;
    private Double longitude;
}
