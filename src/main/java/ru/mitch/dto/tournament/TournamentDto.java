package ru.mitch.dto.tournament;

import lombok.Data;
import ru.mitch.dto.LocationDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TournamentDto {

    private Long id;

    private String title;

    private LocalDateTime eventDate;

    private Integer buyin;

    private Integer rebuy;

    private Integer addon;

    private Integer topPlaces;

    private LocationDto location;

    private String statusCode;

    private Boolean isChristmas;

    private List<TournamentParticipantDto> participants;

}
