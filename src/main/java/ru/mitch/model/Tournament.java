package ru.mitch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime eventDate;

    private Integer buyin;

    private Integer rebuy;

    private Integer addon;

    private Integer topPlaces;

    private Boolean isChristmas;

    @OneToMany(mappedBy = "tournament")
    private List<TournamentParticipant> participants;

    @ManyToOne
    private Location location;

    @OneToOne
    private Album album;

    @ManyToOne
    private Status status;

}
