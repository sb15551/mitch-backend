package ru.samarkand.tightorboil.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.samarkand.tightorboil.model.TelegramData;

public interface TelegramMessageService {

    String messageProcessing(Update update);

    String processingSupportMessages(Update update);

    TelegramData getTelegramDataWithRoleRoot();

}
