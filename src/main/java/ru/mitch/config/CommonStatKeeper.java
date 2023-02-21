package ru.mitch.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mitch.dto.TopPlacesEnum;
import ru.mitch.dto.stat.CommonStatDto;
import ru.mitch.model.view.BablTop;
import ru.mitch.model.view.ChristmasTop;
import ru.mitch.model.view.KnockoutTop;
import ru.mitch.model.view.TopPlace;
import ru.mitch.service.CommonStatService;

import java.util.Comparator;
import java.util.List;

@Component
public class CommonStatKeeper {

    @Getter
    private List<TopPlace> topPlaces;
    @Getter
    private List<TopPlace> firstPlaces;
    @Getter
    private List<TopPlace> secondPlaces;
    @Getter
    private List<TopPlace> thirdPlaces;
    @Getter
    private List<ChristmasTop> christmasTops;
    @Getter
    private List<BablTop> bablTops;
    @Getter
    private List<KnockoutTop> knockoutTops;

    private final CommonStatService commonStatService;

    @Autowired
    public CommonStatKeeper(CommonStatService commonStatService) {
        this.commonStatService = commonStatService;
        initCommonStat();
    }

    public void initCommonStat() {
        topPlaces = commonStatService.getTopPlaces();
        firstPlaces = filterListByPlace(topPlaces, TopPlacesEnum.FIRST.getPlace());
        secondPlaces = filterListByPlace(topPlaces, TopPlacesEnum.SECOND.getPlace());
        thirdPlaces = filterListByPlace(topPlaces, TopPlacesEnum.THIRD.getPlace());
        christmasTops = commonStatService.getChristmasPlaces();
        bablTops = commonStatService.getBablPlaces();
        knockoutTops = commonStatService.getKnockoutTop();
    }

    public CommonStatDto getCommonStat() {
        return CommonStatDto.builder()
                .countGamesPlayed(commonStatService.getCountGamesPlayed())
                .firstPlaces(firstPlaces)
                .secondPlaces(secondPlaces)
                .thirdPlaces(thirdPlaces)
                .christmasTops(christmasTops)
                .bablTops(bablTops)
                .knockoutTops(knockoutTops)
                .build();
    }

    private List<TopPlace> filterListByPlace(List<TopPlace> topPlaces, int place) {
        return topPlaces.stream()
                .filter(top -> top.getPlace().equals(place))
                .sorted(Comparator.comparing(TopPlace::getCountTop).reversed())
                .toList();
    }

}
