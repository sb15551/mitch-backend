package ru.mitch.dto.tournament;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TournamentListDataDto {

    private Long id;

    private String title;

    private LocalDateTime eventDate;

    private String status;

}
