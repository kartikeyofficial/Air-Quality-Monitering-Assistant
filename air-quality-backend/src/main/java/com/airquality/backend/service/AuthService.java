package com.airquality.backend.service;

import com.airquality.backend.dto.AuthResponse;
import com.airquality.backend.dto.LoginRequest;
import com.airquality.backend.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
