package com.airquality.backend.dto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private String role;
}
