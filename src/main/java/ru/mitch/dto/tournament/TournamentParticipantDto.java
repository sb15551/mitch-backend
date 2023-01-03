package ru.mitch.dto.tournament;

import lombok.Data;

@Data
public class TournamentParticipantDto {

    private Long id;

    private Long tournamentId;

    private Long playerId;

    private Long byPlayerId;

    private Integer place;

    private Integer countRebuy;

    private Boolean isAddon;

}
