package ru.mitch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mitch.config.DataKeeper;
import ru.mitch.dto.StatusCodeEnum;
import ru.mitch.model.Status;
import ru.mitch.model.view.BablTop;
import ru.mitch.model.view.ChristmasTop;
import ru.mitch.model.view.KnockoutTop;
import ru.mitch.model.view.TopPlace;
import ru.mitch.repository.TournamentRepository;
import ru.mitch.repository.view.BablTopRepository;
import ru.mitch.repository.view.ChristmasTopRepository;
import ru.mitch.repository.view.KnockoutTopRepository;
import ru.mitch.repository.view.TopPlaceRepository;
import ru.mitch.service.CommonStatService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonStatServiceImpl implements CommonStatService {

    private final DataKeeper dataKeeper;
    private final TournamentRepository tournamentRepository;
    private final TopPlaceRepository topPlaceRepository;
    private final ChristmasTopRepository christmasTopRepository;
    private final BablTopRepository bablTopRepository;
    private final KnockoutTopRepository knockoutTopRepository;

    @Override
    public List<TopPlace> getTopPlaces() {
        return topPlaceRepository.findAll();
    }

    @Override
    public List<ChristmasTop> getChristmasPlaces() {
        return christmasTopRepository.findAll();
    }

    @Override
    public List<BablTop> getBablPlaces() {
        return bablTopRepository.findAll();
    }

    @Override
    public List<KnockoutTop> getKnockoutTop() {
        return knockoutTopRepository.findAll();
    }

    @Override
    public Integer getCountGamesPlayed() {
        Status finishedStatus = dataKeeper.getStatuses().get(StatusCodeEnum.FINISHED.name());
        return tournamentRepository.countAllByStatus(finishedStatus);
    }

}
