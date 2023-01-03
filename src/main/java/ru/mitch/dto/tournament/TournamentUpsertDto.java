package ru.mitch.dto.tournament;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TournamentUpsertDto {

    private Long id;

    private LocalDateTime eventDate;

    private Integer buyin;

    private Integer rebuy;

    private Integer addon;

    private Integer topPlaces;

    private Long locationId;

    private Long statusId;

    private List<TournamentParticipantDto> players;

}
