package ru.mitch.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mitch.dto.LocationDto;
import ru.mitch.dto.tournament.TournamentDto;
import ru.mitch.service.ProcessingService;
import ru.mitch.service.TournamentService;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final TournamentService tournamentService;

    @Override
    public void processingLocation(LocationDto request) {
    }

    @Override
    public void processingTournament(TournamentDto request) {
        if (request.getId() == 0) {
            tournamentService.create(request);
        } else {
            tournamentService.update(request);
        }
    }

}
