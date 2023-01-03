package ru.samarkand.tightorboil.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.samarkand.tightorboil.helper.ExtractorContentFile;
import ru.samarkand.tightorboil.helper.PasswordGenerator;
import ru.samarkand.tightorboil.mapping.PlayerMapper;
import ru.samarkand.tightorboil.mapping.TelegramDataMapper;
import ru.samarkand.tightorboil.model.Player;
import ru.samarkand.tightorboil.model.TelegramData;
import ru.samarkand.tightorboil.repository.PlayerRepository;
import ru.samarkand.tightorboil.repository.RoleRepository;
import ru.samarkand.tightorboil.repository.StatusRepository;
import ru.samarkand.tightorboil.repository.TelegramDataRepository;
import ru.samarkand.tightorboil.service.PlayerService;

import static ru.samarkand.tightorboil.constant.MessageConstant.*;
import static ru.samarkand.tightorboil.dto.RoleCodeEnum.PLAYER;
import static ru.samarkand.tightorboil.dto.StatusCodeEnum.ACTIVE;
import static ru.samarkand.tightorboil.dto.telegram.MessageTypeEnum.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TelegramDataRepository telegramDataRepository;
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;

    private final PlayerMapper playerMapper;
    private final TelegramDataMapper telegramDataMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Player findByLogin(String login) {
        return playerRepository.findByLogin(login);
    }

    @Transactional
    @Override
    public String registration(Update update) {
        String login = update.getMessage().getChat().getUserName();
        if (StringUtils.isBlank(login)) {
            return REG_NOT_LOGIN_PLAYER_MESSAGE;
        }

        long chatId = update.getMessage().getChatId();
        if (existPlayer(chatId)) {
            return REG_EXIST_PLAYER_MESSAGE;
        }

        Player newPlayer = playerMapper.createPlayer(
                login,
                update.getMessage().getChat().getFirstName(),
                update.getMessage().getChat().getLastName(),
                roleRepository.findByCode(PLAYER.name()),
                statusRepository.findByCode(ACTIVE.name()),
                true
        );
        String password = PasswordGenerator.generateRandomPassword();
        newPlayer.setPassword(passwordEncoder.encode(password));
        Player player = playerRepository.save(newPlayer);

        TelegramData telegramData = telegramDataMapper.createTelegramData(chatId, player, PRIVATE.name());
        telegramDataRepository.save(telegramData);

        String messageTemplate = ExtractorContentFile.getTemplateMessage(REG_SUCCESS_MESSAGE_TEMPLATE);
        return String.format(messageTemplate, password);
    }

    @Transactional
    @Override
    public String restorePassword(Update update) {
        String login = update.getMessage().getChat().getUserName();
        if (StringUtils.isBlank(login)) {
            return REG_NOT_LOGIN_PLAYER_MESSAGE;
        }

        Player player = playerRepository.findByLogin(login);
        String password = PasswordGenerator.generateRandomPassword();
        player.setPassword(passwordEncoder.encode(password));
        playerRepository.save(player);

        String messageTemplate = ExtractorContentFile.getTemplateMessage(REG_RESTORE_PASS_MESSAGE_TEMPLATE);
        return String.format(messageTemplate, password);
    }

    private boolean existPlayer(long chatId) {
        return telegramDataRepository.existsByChatId(chatId);
    }

}
