package ru.mitch.mapping;

import org.mapstruct.Mapper;
import ru.mitch.model.Player;
import ru.mitch.model.TelegramData;

@Mapper
public interface TelegramDataMapper {

    TelegramData createTelegramData(long chatId, Player player, String type);

}
