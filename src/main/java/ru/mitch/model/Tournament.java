package ru.mitch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Tournament {

    @Id
    private Long id;

    private LocalDateTime eventDate;

    private Integer buyin;

    private Integer rebuy;

    private Integer addon;

    private Integer topPlaces;

    private Boolean isChristmas;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Status status;

}
