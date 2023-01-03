package ru.mitch.mapping;

import org.mapstruct.Mapper;
import ru.mitch.dto.LocationDto;
import ru.mitch.model.Location;

@Mapper
public interface LocationMapper {

    Location toEntity(LocationDto dto);

}
