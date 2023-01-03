package ru.samarkand.tightorboil.service;

import ru.samarkand.tightorboil.model.Player;

public interface PlayerService {

    Player findByLogin(String login);

}
