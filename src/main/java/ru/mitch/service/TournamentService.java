package ru.mitch.service;

import ru.mitch.dto.RequestPageableDto;
import ru.mitch.dto.tournament.RegistrationDto;
import ru.mitch.dto.tournament.TournamentDto;
import ru.mitch.dto.tournament.TournamentListDto;

public interface TournamentService {

    void create(TournamentDto request);

    void update(TournamentDto request);

    TournamentListDto getTournaments(RequestPageableDto request);

    String getTitle();

    TournamentDto getTournament(Long tournamentId);

    void registerForTournament(RegistrationDto registrationDto);

}
