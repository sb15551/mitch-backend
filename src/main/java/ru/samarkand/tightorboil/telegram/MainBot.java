package ru.samarkand.tightorboil.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.samarkand.tightorboil.service.TelegramMessageService;

import java.util.ArrayList;
import java.util.List;

import static ru.samarkand.tightorboil.dto.telegram.TelegramButtonEnum.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainBot extends TelegramLongPollingBot {

    private final TelegramProperty property;
    private final TelegramMessageService telegramMessageService;

    @Override
    public String getBotUsername() {
        return property.getMainName();
    }

    @Override
    public String getBotToken() {
        return property.getMainToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String message = telegramMessageService.messageProcessing(update);
            sendMessage(chatId, message);
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        message.setReplyMarkup(initKeyBoard());
        message.enableMarkdownV2(true);

        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private ReplyKeyboardMarkup initKeyBoard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add(REGISTER.getCommand());
        row.add(FORGOT.getCommand());

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add(HELP.getCommand());

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }

}
