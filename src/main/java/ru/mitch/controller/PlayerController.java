package ru.mitch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mitch.dto.player.PlayerRequestDto;
import ru.mitch.dto.player.PlayerResponseDto;
import ru.mitch.service.PlayerService;

@RestController
@RequiredArgsConstructor
public class PlayerController extends CommonController {

    private final PlayerService playerService;

    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerResponseDto> getPlayers(@RequestParam Integer page, @RequestParam Integer size) {
        PlayerRequestDto request = new PlayerRequestDto(page, size);
        return ResponseEntity.ok(playerService.getPlayerList(request));
    }

}
