package ru.mitch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LocationResponseDto {

    private Integer total;

    private List<LocationDto> rows;

}
