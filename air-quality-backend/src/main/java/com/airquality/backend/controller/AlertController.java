package com.airquality.backend.controller;

import com.airquality.backend.dto.AlertRequest;
import com.airquality.backend.dto.AlertResponse;
import com.airquality.backend.service.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class AlertController {
    private final AlertService alertService;

    @PostMapping
    public ResponseEntity<AlertResponse> createAlert(
            @Valid @RequestBody AlertRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(alertService.createAlert(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlertResponse>> getAlertsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                alertService.getAlertsByUser(userId)
        );
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<AlertResponse>> getAlertsByLocation(
            @PathVariable Long locationId) {

        return ResponseEntity.ok(
                alertService.getAlertsByLocation(locationId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertResponse> getAlertById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                alertService.getAlertById(id)
        );
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<AlertResponse> markAsRead(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                alertService.markAsRead(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(
            @PathVariable Long id) {

        alertService.deleteAlert(id);

        return ResponseEntity.noContent().build();
    }
}
