package ru.mitch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mitch.dto.player.PlayerListRequestDto;
import ru.mitch.dto.player.PlayerListResponseDto;
import ru.mitch.dto.player.PlayerResponseDto;
import ru.mitch.service.PlayerService;

@RestController
@RequiredArgsConstructor
public class PlayerController extends CommonController {

    private final PlayerService playerService;

    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerListResponseDto> getPlayers(@RequestParam Integer page, @RequestParam Integer size) {
        PlayerListRequestDto request = new PlayerListRequestDto(page, size);
        return ResponseEntity.ok(playerService.getPlayerList(request));
    }

    @GetMapping(value = "/player/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerResponseDto> getPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayer(id));
    }

}
