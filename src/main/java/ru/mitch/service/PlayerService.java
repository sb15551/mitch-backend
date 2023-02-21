package ru.mitch.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mitch.dto.RequestPageableDto;
import ru.mitch.dto.player.*;
import ru.mitch.model.Player;

import java.util.List;

public interface PlayerService {

    Player findByLogin(String login);

    Player findById(Long id);

    String registration(Update update);

    String restorePassword(Update update);

    PlayerListResponseDto getPlayerList(RequestPageableDto request);

    List<PlayerResponseDto> getAllPlayers();

    PlayerResponseDto getPlayer(Long id);

    void savePlayer(PlayerRequestDto request);

    Player savePlayerSettings(Player player, SettingsRequestDto request);

}
