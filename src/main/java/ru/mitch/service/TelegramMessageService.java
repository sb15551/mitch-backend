package ru.mitch.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.mitch.model.TelegramData;

public interface TelegramMessageService {

    String messageProcessing(Update update);

    String processingSupportMessages(Update update);

    TelegramData getTelegramDataWithRoleRoot();

}
