package ru.mitch.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mitch.model.Player;
import ru.mitch.model.TelegramData;

@Mapper
public interface TelegramDataMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "chatId", target = "chatId")
    @Mapping(source = "player", target = "player")
    TelegramData createTelegramData(long chatId, Player player, String type);

}
