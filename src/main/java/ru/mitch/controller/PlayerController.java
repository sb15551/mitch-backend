package ru.mitch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mitch.dto.RequestPageableDto;
import ru.mitch.dto.auth.AuthenticationResponseDto;
import ru.mitch.dto.player.*;
import ru.mitch.security.auth.AuthService;
import ru.mitch.service.PlayerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerController extends CommonController {

    private final PlayerService playerService;
    private final AuthService authService;

    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerListResponseDto> getPlayers(@RequestParam Integer page, @RequestParam Integer size) {
        RequestPageableDto request = new RequestPageableDto(page, size);
        return ResponseEntity.ok(playerService.getPlayerList(request));
    }

    @GetMapping(value = "/all_players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping(value = "/player/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerResponseDto> getPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayer(id));
    }

    @PostMapping(value = "/player", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> savePlayer(@RequestBody PlayerRequestDto request) {
        playerService.savePlayer(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/player/settings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponseDto> saveSettings(@Valid @RequestBody SettingsRequestDto request) {
        return ResponseEntity.ok(authService.saveSettings(request));
    }

}
