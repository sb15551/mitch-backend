package ru.mitch.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mitch.service.ProcessingService;
import ru.mitch.dto.LocationDto;
import ru.mitch.service.LocationService;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final LocationService locationService;

    @Override
    public void processingLocation(LocationDto request) {
    }

}
