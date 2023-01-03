package ru.mitch.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tournaments_participants")
public class TournamentParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;

    private Long playerId;

    private Long byPlayerId;

    private Integer place;

    private Integer countRebuy;

    private Boolean isAddon;

}
