package ru.mitch.dto.stat;

import lombok.Builder;
import lombok.Data;
import ru.mitch.model.view.BablTop;
import ru.mitch.model.view.ChristmasTop;
import ru.mitch.model.view.KnockoutTop;
import ru.mitch.model.view.TopPlace;

import java.util.List;

@Data
@Builder
public class CommonStatDto {

    private int countGamesPlayed;

    private List<TopPlace> firstPlaces;

    private List<TopPlace> secondPlaces;

    private List<TopPlace> thirdPlaces;

    private List<ChristmasTop> christmasTops;

    private List<BablTop> bablTops;

    private List<KnockoutTop> knockoutTops;

}
