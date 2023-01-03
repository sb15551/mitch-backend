package ru.mitch.service;

import ru.mitch.dto.LocationDto;

public interface LocationService {

    void upsertLocation(LocationDto locationDto);

}
