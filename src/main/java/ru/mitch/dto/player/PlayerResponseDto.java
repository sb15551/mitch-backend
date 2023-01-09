package ru.mitch.dto.player;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlayerResponseDto {

    private Integer total;

    private List<PlayerResponseDataDto> rows;

}
