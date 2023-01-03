package ru.mitch.service;

import ru.mitch.dto.tournament.TournamentUpsertDto;

public interface TournamentService {

    void upsert(TournamentUpsertDto request);

}
