package ru.mitch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mitch.dto.LocationDto;
import ru.mitch.dto.LocationResponseDto;
import ru.mitch.dto.RequestPageableDto;
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

    @GetMapping(value = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResponseDto> getLocations(@RequestParam Integer page, @RequestParam Integer size) {
        RequestPageableDto request = new RequestPageableDto(page, size);
        return ResponseEntity.ok(locationService.getLocationList(request));
    }

    @GetMapping(value = "/location/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationDto> getLocation(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocation(id));
    }

}
