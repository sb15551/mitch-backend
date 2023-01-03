package ru.mitch.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mitch.constant.MessageConstant;
import ru.mitch.dto.RoleCodeEnum;
import ru.mitch.dto.StatusCodeEnum;
import ru.mitch.dto.telegram.MessageTypeEnum;
import ru.mitch.helper.ExtractorContentFile;
import ru.mitch.helper.PasswordGenerator;
import ru.mitch.mapping.PlayerMapper;
import ru.mitch.model.Player;
import ru.mitch.model.TelegramData;
import ru.mitch.repository.PlayerRepository;
import ru.mitch.repository.RoleRepository;
import ru.mitch.repository.StatusRepository;
import ru.mitch.repository.TelegramDataRepository;
import ru.mitch.mapping.TelegramDataMapper;
import ru.mitch.service.PlayerService;

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
            return MessageConstant.REG_NOT_LOGIN_PLAYER_MESSAGE;
        }

        long chatId = update.getMessage().getChatId();
        if (existPlayer(chatId)) {
            return MessageConstant.REG_EXIST_PLAYER_MESSAGE;
        }

        Player newPlayer = playerMapper.createPlayer(
                login,
                update.getMessage().getChat().getFirstName(),
                update.getMessage().getChat().getLastName(),
                roleRepository.findByCode(RoleCodeEnum.PLAYER.name()),
                statusRepository.findByCode(StatusCodeEnum.ACTIVE.name()),
                true
        );
        String password = PasswordGenerator.generateRandomPassword();
        newPlayer.setPassword(passwordEncoder.encode(password));
        Player player = playerRepository.save(newPlayer);

        TelegramData telegramData = telegramDataMapper.createTelegramData(chatId, player, MessageTypeEnum.PRIVATE.name());
        telegramDataRepository.save(telegramData);

        String messageTemplate = ExtractorContentFile.getTemplateMessage(MessageConstant.REG_SUCCESS_MESSAGE_TEMPLATE);
        return String.format(messageTemplate, password);
    }

    @Transactional
    @Override
    public String restorePassword(Update update) {
        String login = update.getMessage().getChat().getUserName();
        if (StringUtils.isBlank(login)) {
            return MessageConstant.REG_NOT_LOGIN_PLAYER_MESSAGE;
        }

        Player player = playerRepository.findByLogin(login);
        String password = PasswordGenerator.generateRandomPassword();
        player.setPassword(passwordEncoder.encode(password));
        playerRepository.save(player);

        String messageTemplate = ExtractorContentFile.getTemplateMessage(MessageConstant.REG_RESTORE_PASS_MESSAGE_TEMPLATE);
        return String.format(messageTemplate, password);
    }

    private boolean existPlayer(long chatId) {
        return telegramDataRepository.existsByChatId(chatId);
    }

}
