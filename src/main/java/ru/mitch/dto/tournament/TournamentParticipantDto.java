package ru.mitch.dto.tournament;

import lombok.Data;
import ru.mitch.dto.player.PlayerDto;

@Data
public class TournamentParticipantDto {

    private Long id;

    private Integer place;

    private Integer countRebuy;

    private Boolean isAddon;

    private PlayerDto player;

    private PlayerDto byPlayer;

    private Boolean status;

}
