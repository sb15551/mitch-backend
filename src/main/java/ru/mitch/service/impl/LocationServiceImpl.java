package ru.mitch.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mitch.dto.LocationDto;
import ru.mitch.dto.LocationResponseDto;
import ru.mitch.dto.RequestPageableDto;
import ru.mitch.mapping.LocationMapper;
import ru.mitch.model.Location;
import ru.mitch.repository.LocationRepository;
import ru.mitch.service.LocationService;

import java.util.List;

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

    @Override
    public LocationResponseDto getLocationList(RequestPageableDto request) {
        Integer total = locationRepository.countAll();
        if (total == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        List<LocationDto> locationDtoList = locationRepository.findAll(PageRequest.of(request.getPage(), request.getSize(), Sort.by("id")))
                .get()
                .map(locationMapper::toDto)
                .toList();
        return new LocationResponseDto(total, locationDtoList);
    }

    @Override
    public LocationDto getLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return locationMapper.toDto(location);
    }

}
