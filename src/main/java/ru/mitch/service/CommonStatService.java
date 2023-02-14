package ru.mitch.service;

import ru.mitch.model.view.BablTop;
import ru.mitch.model.view.ChristmasTop;
import ru.mitch.model.view.KnockoutTop;
import ru.mitch.model.view.TopPlace;

import java.util.List;

public interface CommonStatService {

    List<TopPlace> getTopPlaces();

    List<ChristmasTop> getChristmasPlaces();

    List<BablTop> getBablPlaces();

    List<KnockoutTop> getKnockoutTop();

    Integer getCountGamesPlayed();

}
