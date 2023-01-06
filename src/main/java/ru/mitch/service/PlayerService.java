package ru.mitch.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mitch.model.Player;

public interface PlayerService {

    Player findByLogin(String login);

    String registration(Update update);

    String restorePassword(Update update);

}