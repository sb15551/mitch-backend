package ru.mitch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mitch.dto.LocationDto;
import ru.mitch.service.LocationService;

@RestController
@RequiredArgsConstructor
@Validated
public class LocationController extends CommonController {

    private final LocationService locationService;

    @PostMapping(value = "/location/upsert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> upsertLocation(@Valid @RequestBody LocationDto request) {
        locationService.upsertLocation(request);
        return ResponseEntity.ok().build();
    }

}
