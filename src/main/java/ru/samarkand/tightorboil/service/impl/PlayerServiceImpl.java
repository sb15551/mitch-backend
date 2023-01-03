package ru.samarkand.tightorboil.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.samarkand.tightorboil.model.Player;
import ru.samarkand.tightorboil.repository.PlayerRepository;
import ru.samarkand.tightorboil.service.PlayerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player findByLogin(String login) {
        return playerRepository.findByLogin(login);
    }

}
