package ru.mitch.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.mitch.dto.tournament.TournamentDto;
import ru.mitch.dto.tournament.TournamentListDataDto;
import ru.mitch.dto.tournament.TournamentParticipantDto;
import ru.mitch.model.Player;
import ru.mitch.model.Tournament;
import ru.mitch.model.TournamentParticipant;

import java.util.List;

@Mapper(uses = {PlayerMapper.class})
public interface TournamentMapper {

    @Mapping(source = "status.name", target = "status")
    TournamentListDataDto toDto(Tournament tournament);

    @Mapping(source = "participants", target = "participants", qualifiedByName = "toListParticipantDto")
    @Mapping(source = "status.code", target = "statusCode")
    TournamentDto toTournamentDto(Tournament tournament);

    @Mapping(source = "player", target = "player", qualifiedByName = "toPlayerDto")
    @Mapping(source = "byPlayer", target = "byPlayer", qualifiedByName = "toPlayerDto")
    @Mapping(target = "status", expression = "java(true)")
    TournamentParticipantDto toTournamentParticipantDto(TournamentParticipant tournamentParticipant);

    @Named("toListParticipantDto")
    List<TournamentParticipantDto> toListParticipantDto(List<TournamentParticipant> participants);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "participants", ignore = true)
    Tournament createTournament(TournamentDto tournamentDto);

    @Mapping(target = "participants", ignore = true)
    Tournament toTournament(TournamentDto tournamentDto);

    @Mapping(target = "id", ignore = true)
    TournamentParticipant createParticipant(TournamentParticipantDto participantDto);

    TournamentParticipant toParticipant(TournamentParticipantDto participantDto);

    @Named("handleParticipant")
    default TournamentParticipantDto handleParticipant(TournamentParticipantDto participantDto) {
        if (participantDto.getByPlayer() != null && participantDto.getByPlayer().getId() == 0) {
            participantDto.setByPlayer(null);
        }
        return participantDto;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "place", expression = "java(0)")
    @Mapping(target = "countRebuy", expression = "java(0)")
    @Mapping(target = "isAddon", expression = "java(false)")
    TournamentParticipant newParticipant(Tournament tournament, Player player);

}
