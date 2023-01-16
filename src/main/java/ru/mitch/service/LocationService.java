package ru.mitch.service;

import ru.mitch.dto.LocationDto;
import ru.mitch.dto.LocationResponseDto;
import ru.mitch.dto.RequestPageableDto;

public interface LocationService {

    void upsertLocation(LocationDto locationDto);

    LocationResponseDto getLocationList(RequestPageableDto request);

    LocationDto getLocation(Long id);

}
