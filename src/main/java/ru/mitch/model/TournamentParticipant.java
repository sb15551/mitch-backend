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

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "by_player_id")
    private Player byPlayer;

    private Integer place;

    private Integer countRebuy;

    private Boolean isAddon;

}
