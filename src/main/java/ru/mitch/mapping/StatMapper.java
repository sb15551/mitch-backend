package ru.mitch.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mitch.dto.stat.StatDataDto;
import ru.mitch.model.view.*;

@Mapper
public interface StatMapper {

    @Mapping(source = "playerId", target = "id")
    StatDataDto toStatDataDto(TopPlace topPlace);

    @Mapping(source = "playerId", target = "id")
    StatDataDto toStatDataDto(ChristmasTop christmasTop);

    @Mapping(source = "playerId", target = "id")
    StatDataDto toStatDataDto(BablTop bablTop);

    @Mapping(source = "playerId", target = "id")
    StatDataDto toStatDataDto(KnockoutTop knockoutTop);

    @Mapping(source = "playerId", target = "id")
    StatDataDto toStatDataDto(MostPrize mostPrize);

    @Mapping(source = "playerId", target = "id")
    StatDataDto toStatDataDto(MostRebuy mostRebuy);

    @Mapping(source = "playerId", target = "id")
    StatDataDto toStatDataDto(Loser loser);

}
