package ru.mitch.dto.tournament;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationDto {

    @NotNull
    private Long tournamentId;

    @NotNull
    private Long playerId;

    @NotNull
    private Boolean isRegistered;

}
