package ru.mitch.service;

import ru.mitch.dto.LocationDto;
import ru.mitch.dto.tournament.TournamentDto;

public interface ProcessingService {

    void processingLocation(LocationDto request);

    void processingTournament(TournamentDto request);

}
