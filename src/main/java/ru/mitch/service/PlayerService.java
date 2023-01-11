package ru.mitch.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mitch.dto.player.PlayerListRequestDto;
import ru.mitch.dto.player.PlayerListResponseDto;
import ru.mitch.dto.player.PlayerResponseDto;
import ru.mitch.model.Player;

public interface PlayerService {

    Player findByLogin(String login);

    String registration(Update update);

    String restorePassword(Update update);

    PlayerListResponseDto getPlayerList(PlayerListRequestDto request);

    PlayerResponseDto getPlayer(Long id);

}
