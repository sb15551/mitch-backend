package ru.mitch.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

}
