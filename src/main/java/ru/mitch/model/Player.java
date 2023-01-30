package ru.mitch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private String name;

    private String surname;

    private Boolean isConfirm;

    private LocalDateTime createdDate;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Status status;

    @OneToMany(mappedBy = "player")
    private List<TournamentParticipant> tournaments;

    @OneToMany(mappedBy = "byPlayer")
    private List<TournamentParticipant> cutDownHeads;

    @Transient
    private Long chatId;

}
