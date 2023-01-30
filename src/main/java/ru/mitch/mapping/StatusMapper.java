package ru.mitch.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.mitch.dto.StatusDto;
import ru.mitch.model.Status;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StatusMapper {

    List<StatusDto> toListDto(List<Status> statuses);

}
