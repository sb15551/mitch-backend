package ru.mitch.dto.player;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerRequestDto {

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;

}
