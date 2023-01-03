package ru.samarkand.tightorboil.mapping;

import org.mapstruct.Mapper;
import ru.samarkand.tightorboil.model.Player;
import ru.samarkand.tightorboil.model.TelegramData;

@Mapper
public interface TelegramDataMapper {

    TelegramData createTelegramData(long chatId, Player player, String type);

}
