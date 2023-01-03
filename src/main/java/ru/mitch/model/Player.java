package ru.mitch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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

}
