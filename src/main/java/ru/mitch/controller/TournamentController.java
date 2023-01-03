package ru.mitch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mitch.dto.tournament.TournamentUpsertDto;
import ru.mitch.service.TournamentService;

@RestController
@RequiredArgsConstructor
@Validated
public class TournamentController extends CommonController {

    private final TournamentService tournamentService;

    @PostMapping(value = "/tournament/upsert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> upsertLocation(@Valid @RequestBody TournamentUpsertDto request) {

        return ResponseEntity.ok().build();
    }

}
