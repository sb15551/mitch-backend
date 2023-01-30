package ru.mitch.dto.tournament;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TournamentListDto {

    private Integer total;

    private List<TournamentListDataDto> rows;

}
