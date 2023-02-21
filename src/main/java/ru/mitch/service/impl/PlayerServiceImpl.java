package ru.mitch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mitch.config.DataKeeper;
import ru.mitch.constant.MessageConstant;
import ru.mitch.dto.RequestPageableDto;
import ru.mitch.dto.RoleCodeEnum;
import ru.mitch.dto.StatusCodeEnum;
import ru.mitch.dto.TelegramDataTypeEnum;
import ru.mitch.dto.player.*;
import ru.mitch.dto.telegram.MessageTypeEnum;
import ru.mitch.helper.ExtractorContentFile;
import ru.mitch.helper.PasswordGenerator;
import ru.mitch.mapping.PlayerMapper;
import ru.mitch.mapping.TelegramDataMapper;
import ru.mitch.model.Player;
import ru.mitch.model.Status;
import ru.mitch.model.TelegramData;
import ru.mitch.repository.PlayerRepository;
import ru.mitch.repository.RoleRepository;
import ru.mitch.repository.StatusRepository;
import ru.mitch.repository.TelegramDataRepository;
import ru.mitch.service.PlayerService;

import java.util.List;

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
    private final DataKeeper dataKeeper;

    @Override
    public Player findByLogin(String login) {
        return playerRepository.findByLogin(login);
    }

    @Override
    public Player findById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
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

    @Override
    public PlayerListResponseDto getPlayerList(RequestPageableDto request) {
        Status statusActive = dataKeeper.getStatuses().get(StatusCodeEnum.ACTIVE.name());
        Integer total = telegramDataRepository.countAllByAllActivePlayers(statusActive);
        List<PlayerListResponseDataDto> data =
                telegramDataRepository.findAllActivePlayers(statusActive, PageRequest.of(request.getPage(), request.getSize()))
                        .stream()
                        .map(tgData -> {
                            Player player = tgData.getPlayer();
                            player.setChatId(tgData.getChatId());
                            return playerMapper.toResponseDto(player);
                        })
                        .toList();
        return new PlayerListResponseDto(total, data);
    }

    @Override
    public List<PlayerResponseDto> getAllPlayers() {
        Status statusActive = dataKeeper.getStatuses().get(StatusCodeEnum.ACTIVE.name());
        return telegramDataRepository.findAllActivePlayers(statusActive)
                .stream()
                .map(tgData -> {
                    Player player = tgData.getPlayer();
                    player.setChatId(tgData.getChatId());
                    return playerMapper.toPlayerResponseDto(player);
                })
                .toList();
    }

    @Override
    public PlayerResponseDto getPlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        TelegramData tgData = telegramDataRepository.findByPlayer(player);
        return playerMapper.toResponseDto(player, tgData);
    }

    @Transactional
    @Override
    public void savePlayer(PlayerRequestDto request) {
        if (request.getId().equals(0L)) {
            Player player = playerRepository.save(playerMapper.toEntity(request,
                    dataKeeper.getRoles().get(request.getRole().name()),
                    dataKeeper.getStatuses().get(StatusCodeEnum.ACTIVE.name())));

            telegramDataRepository.save(telegramDataMapper.createTelegramData(0, player, TelegramDataTypeEnum.PRIVATE.name()));
        } else {
            Player player = playerRepository.findById(request.getId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            if (!dataKeeper.getRoles().get(request.getRole().name()).getCode()
                    .equals(player.getRole().getCode())) {
                player.setRole(dataKeeper.getRoles().get(request.getRole().name()));
            }
            playerRepository.save(playerMapper.toEntity(player, request));
        }
    }

    @Override
    public Player savePlayerSettings(Player player, SettingsRequestDto request) {
        return playerRepository.save(playerMapper.toEntity(player, request));
    }

    private boolean existPlayer(long chatId) {
        return telegramDataRepository.existsByChatId(chatId);
    }

}
