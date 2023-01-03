package ru.mitch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mitch.constant.MessageConstant;
import ru.mitch.dto.telegram.MessageTypeEnum;
import ru.mitch.dto.telegram.TelegramButtonEnum;
import ru.mitch.helper.ExtractorContentFile;
import ru.mitch.model.Player;
import ru.mitch.model.TelegramData;
import ru.mitch.repository.PlayerRepository;
import ru.mitch.repository.TelegramDataRepository;
import ru.mitch.service.PlayerService;
import ru.mitch.service.TelegramMessageService;

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
        String message = new StringBuilder(MessageConstant.HASH_TAG_FEEDBACK)
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
                String templateHelp = ExtractorContentFile.getTemplateMessage(MessageConstant.HELP_MESSAGE_TEMPLATE);
                resultMessage = String.format(templateHelp, name);
                break;
            default:
                resultMessage = MessageConstant.DEFAULT_MESSAGE;
        }
        return resultMessage;
    }

    private String getCommand(String messageText) {
        String[] messageArray = messageText.split(" ");
        return messageArray[0];
    }

    private String startCommandReceived(String name) {
        String templateMessage = ExtractorContentFile.getTemplateMessage(MessageConstant.START_MESSAGE_TEMPLATE);
        return String.format(templateMessage, name);
    }

}
