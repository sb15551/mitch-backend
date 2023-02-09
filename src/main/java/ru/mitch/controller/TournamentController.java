package ru.mitch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mitch.dto.RequestPageableDto;
import ru.mitch.dto.tournament.RegistrationDto;
import ru.mitch.dto.tournament.TournamentListDto;
import ru.mitch.dto.tournament.TournamentDto;
import ru.mitch.service.ProcessingService;
import ru.mitch.service.TournamentService;

@RestController
@RequiredArgsConstructor
@Validated
public class TournamentController extends CommonController {

    private final TournamentService tournamentService;
    private final ProcessingService processingService;

    @PostMapping(value = "/tournament/upsert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> upsertLocation(@Valid @RequestBody TournamentDto request) {
        processingService.processingTournament(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/tournaments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TournamentListDto> getTournaments(@RequestParam Integer page, @RequestParam Integer size) {
        RequestPageableDto request = new RequestPageableDto(page, size);
        return ResponseEntity.ok(tournamentService.getTournaments(request));
    }

    @GetMapping(value = "/tournament/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TournamentDto> getTournaments(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.getTournament(id));
    }

    @GetMapping(value = "/random_title", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTitle() {
        return ResponseEntity.ok(tournamentService.getTitle());
    }

    @PostMapping(value = "/tournament/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerForTournament(@Valid @RequestBody RegistrationDto registrationDto) {
        tournamentService.registerForTournament(registrationDto);
        return ResponseEntity.ok().build();
    }

}
