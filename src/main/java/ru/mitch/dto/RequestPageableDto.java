package ru.mitch.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestPageableDto {

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;

}
