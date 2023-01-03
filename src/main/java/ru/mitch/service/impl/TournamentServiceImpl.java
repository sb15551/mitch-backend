package ru.mitch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mitch.service.TournamentService;
import ru.mitch.dto.tournament.TournamentUpsertDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    @Override
    public void upsert(TournamentUpsertDto request) {

    }

}
