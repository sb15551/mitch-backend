package ru.mitch.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mitch.mapping.LocationMapper;
import ru.mitch.repository.LocationRepository;
import ru.mitch.dto.LocationDto;
import ru.mitch.service.LocationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public void upsertLocation(LocationDto locationDto) {
        locationRepository.save(locationMapper.toEntity(locationDto));
    }

}
