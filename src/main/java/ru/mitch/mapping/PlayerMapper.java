package ru.mitch.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mitch.dto.player.PlayerListResponseDataDto;
import ru.mitch.dto.player.PlayerResponseDto;
import ru.mitch.model.Player;
import ru.mitch.model.Role;
import ru.mitch.model.Status;
import ru.mitch.model.TelegramData;

@Mapper
public interface PlayerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "firstName")
    Player createPlayer(String login, String firstName, String surname, Role role, Status status, Boolean isConfirm);

    @Mapping(target = "fullName", expression = "java(player.getName() + \" \" + player.getSurname())")
    @Mapping(source = "login", target = "telegramId")
    @Mapping(source = "chatId", target = "telegramChatId")
    PlayerListResponseDataDto toResponseDto(Player player);

    @Mapping(source = "player.id", target = "id")
    @Mapping(source = "telegramData.chatId", target = "chatId")
    @Mapping(source = "player.role", target = "role")
    PlayerResponseDto toResponseDto(Player player, TelegramData telegramData);

}
