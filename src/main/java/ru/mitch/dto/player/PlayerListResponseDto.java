package ru.mitch.dto.player;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlayerListResponseDto {

    private Integer total;

    private List<PlayerListResponseDataDto> rows;

}
