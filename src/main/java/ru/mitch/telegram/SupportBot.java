package ru.mitch.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mitch.constant.MessageConstant;
import ru.mitch.model.TelegramData;
import ru.mitch.service.TelegramMessageService;

import static ru.mitch.dto.telegram.TelegramButtonEnum.START;

@Slf4j
@Component
@RequiredArgsConstructor
public class SupportBot extends TelegramLongPollingBot {

    private final TelegramProperty property;
    private final TelegramMessageService telegramMessageService;

    @Override
    public String getBotUsername() {
        return property.getSupportName();
    }

    @Override
    public String getBotToken() {
        return property.getSupportToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String[] messageArray = update.getMessage().getText().split(" ");
            String command = messageArray[0];
            if (!START.getCommand().equals(command)) {
                TelegramData telegramData = telegramMessageService.getTelegramDataWithRoleRoot();
                String message = telegramMessageService.processingSupportMessages(update);
                sendMessage(telegramData.getChatId(), message);
            }
        } else {
            long chatId = update.getMessage().getChatId();
            sendMessage(chatId, MessageConstant.ONLY_TEXT);
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

}
