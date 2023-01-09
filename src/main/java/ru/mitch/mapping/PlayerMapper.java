package ru.mitch.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mitch.dto.player.PlayerResponseDataDto;
import ru.mitch.model.Player;
import ru.mitch.model.Role;
import ru.mitch.model.Status;

@Mapper
public interface PlayerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "firstName")
    Player createPlayer(String login, String firstName, String surname, Role role, Status status, Boolean isConfirm);

    @Mapping(target = "fullName", expression = "java(player.getName() + \" \" + player.getSurname())")
    @Mapping(source = "login", target = "telegramId")
    @Mapping(source = "chatId", target = "telegramChatId")
    @Mapping(source = "role.code", target = "role")
    PlayerResponseDataDto toResponseDto(Player player);

}
