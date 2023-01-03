package ru.samarkand.tightorboil.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.samarkand.tightorboil.model.Player;
import ru.samarkand.tightorboil.model.Role;
import ru.samarkand.tightorboil.model.Status;

@Mapper
public interface PlayerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "firstName")
    Player createPlayer(String login, String firstName, String surname, Role role, Status status, Boolean isConfirm);

}
