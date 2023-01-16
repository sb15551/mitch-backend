package ru.mitch.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mitch.dto.RequestPageableDto;
import ru.mitch.dto.player.*;
import ru.mitch.model.Player;

public interface PlayerService {

    Player findByLogin(String login);

    String registration(Update update);

    String restorePassword(Update update);

    PlayerListResponseDto getPlayerList(RequestPageableDto request);

    PlayerResponseDto getPlayer(Long id);

    void savePlayer(PlayerRequestDto request);

}
