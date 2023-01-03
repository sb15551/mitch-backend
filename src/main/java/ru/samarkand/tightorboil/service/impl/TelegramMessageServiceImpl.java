package ru.samarkand.tightorboil.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.samarkand.tightorboil.dto.telegram.MessageTypeEnum;
import ru.samarkand.tightorboil.dto.telegram.TelegramButtonEnum;
import ru.samarkand.tightorboil.helper.ExtractorContentFile;
import ru.samarkand.tightorboil.model.Player;
import ru.samarkand.tightorboil.model.TelegramData;
import ru.samarkand.tightorboil.repository.PlayerRepository;
import ru.samarkand.tightorboil.repository.TelegramDataRepository;
import ru.samarkand.tightorboil.service.PlayerService;
import ru.samarkand.tightorboil.service.TelegramMessageService;

import static ru.samarkand.tightorboil.constant.MessageConstant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramMessageServiceImpl implements TelegramMessageService {

    private final PlayerService playerService;
    private final TelegramDataRepository telegramDataRepository;
    private final PlayerRepository playerRepository;

    @Override
    public String messageProcessing(Update update) {
        MessageTypeEnum type = MessageTypeEnum.valueOf(update.getMessage().getChat().getType().toUpperCase());
        return type == MessageTypeEnum.GROUP ? messageGroup() : messagePrivate(update);
    }

    @Override
    public String processingSupportMessages(Update update) {
        String text = update.getMessage().getText();
        String message = new StringBuilder(HASH_TAG_FEEDBACK)
                .append("From @").append(update.getMessage().getChat().getUserName())
                .append(System.lineSeparator())
                .append(text)
                .toString();
        return message;
    }

    @Override
    public TelegramData getTelegramDataWithRoleRoot() {
        Player rootPlayer = playerRepository.findByWithRoleRoot();
        return telegramDataRepository.findByPlayer(rootPlayer);
    }

    private String messageGroup() {
        return "GROUP";
    }

    private String messagePrivate(Update update) {
        String command = getCommand(update.getMessage().getText());
        TelegramButtonEnum button = TelegramButtonEnum.of(command);
        String name = update.getMessage().getChat().getFirstName();

        String resultMessage;
        switch (button) {
            case START:
                resultMessage = startCommandReceived(name);
                break;
            case REGISTER:
                resultMessage = playerService.registration(update);
                break;
            case FORGOT:
                resultMessage = playerService.restorePassword(update);
                break;
            case HELP:
                String templateHelp = ExtractorContentFile.getTemplateMessage(HELP_MESSAGE_TEMPLATE);
                resultMessage = String.format(templateHelp, name);
                break;
            default:
                resultMessage = DEFAULT_MESSAGE;
        }
        return resultMessage;
    }

    private String getCommand(String messageText) {
        String[] messageArray = messageText.split(" ");
        return messageArray[0];
    }

    private String startCommandReceived(String name) {
        String templateMessage = ExtractorContentFile.getTemplateMessage(START_MESSAGE_TEMPLATE);
        return String.format(templateMessage, name);
    }

}
