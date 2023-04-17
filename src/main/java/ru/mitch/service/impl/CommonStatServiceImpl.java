package ru.mitch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.mitch.config.DataKeeper;
import ru.mitch.dto.MetricCodeEnum;
import ru.mitch.dto.StatusCodeEnum;
import ru.mitch.dto.TopPlacesEnum;
import ru.mitch.dto.stat.MetricValue;
import ru.mitch.dto.stat.StatDataDto;
import ru.mitch.mapping.MetricMapper;
import ru.mitch.mapping.StatMapper;
import ru.mitch.model.RefMetric;
import ru.mitch.model.Status;
import ru.mitch.repository.RefMetricRepository;
import ru.mitch.repository.TournamentRepository;
import ru.mitch.repository.view.*;
import ru.mitch.service.CommonStatService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.mitch.dto.TopPlacesEnum.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonStatServiceImpl implements CommonStatService {

    private static final PageRequest TOP = PageRequest.of(0, 3);
    private static final boolean ONLY_TOP = true;
    private static final boolean ALL = false;

    private final DataKeeper dataKeeper;
    private final TournamentRepository tournamentRepository;
    private final TopPlaceRepository topPlaceRepository;
    private final ChristmasTopRepository christmasTopRepository;
    private final BablTopRepository bablTopRepository;
    private final KnockoutTopRepository knockoutTopRepository;
    private final RefMetricRepository refMetricRepository;
    private final MostPrizesTopRepository mostPrizesTopRepository;
    private final MostRebuyTopRepository mostRebuyTopRepository;
    private final LosersTopRepository losersTopRepository;

    private final StatMapper statMapper;
    private final MetricMapper metricMapper;

    @Override
    public MetricValue getMetricValueByCode(MetricCodeEnum metricCode) {
        RefMetric refMetric = refMetricRepository.findByMetricCode(metricCode.name());
        return switch (metricCode) {
            case FIRST_PLACES -> metricMapper.toMetricValue(refMetric, getTopPlacesByPlace(FIRST, ALL));
            case SECOND_PLACES -> metricMapper.toMetricValue(refMetric, getTopPlacesByPlace(SECOND, ALL));
            case THIRD_PLACES -> metricMapper.toMetricValue(refMetric, getTopPlacesByPlace(THIRD, ALL));
            case CHRISTMAS_PLACES -> metricMapper.toMetricValue(refMetric, getChristmasTop(ALL));
            case BABL_PLACES -> metricMapper.toMetricValue(refMetric, getBablTop(ALL));
            case KNOCKOUTS -> metricMapper.toMetricValue(refMetric, getKnockoutsTop(ALL));
            case MOST_PRIZES -> metricMapper.toMetricValue(refMetric, getMostPrizesTop(ALL));
            case MOST_REBUY -> metricMapper.toMetricValue(refMetric, getMostRebuyTop(ALL));
            case LOSERS -> metricMapper.toMetricValue(refMetric, getLosersTop(ALL));
            default -> new MetricValue();
        };
    }

    @Override
    public Integer getCountGamesPlayed() {
        Status finishedStatus = dataKeeper.getStatuses().get(StatusCodeEnum.FINISHED.name());
        return tournamentRepository.countAllByStatus(finishedStatus);
    }

    @Override
    public List<MetricValue> getMetricsValues() {
        List<MetricValue> metricValues = new ArrayList<>();
        List<RefMetric> refMetrics = refMetricRepository.findAll();
        refMetrics.forEach(refMetric -> {
            MetricCodeEnum metricCode = MetricCodeEnum.valueOf(refMetric.getMetricCode());
            switch (metricCode) {
                case FIRST_PLACES ->
                        metricValues.add(metricMapper.toMetricValue(refMetric, getTopPlacesByPlace(FIRST, ONLY_TOP)));
                case SECOND_PLACES ->
                        metricValues.add(metricMapper.toMetricValue(refMetric, getTopPlacesByPlace(SECOND, ONLY_TOP)));
                case THIRD_PLACES ->
                        metricValues.add(metricMapper.toMetricValue(refMetric, getTopPlacesByPlace(THIRD, ONLY_TOP)));
                case CHRISTMAS_PLACES ->
                        metricValues.add(metricMapper.toMetricValue(refMetric, getChristmasTop(ONLY_TOP)));
                case BABL_PLACES -> metricValues.add(metricMapper.toMetricValue(refMetric, getBablTop(ONLY_TOP)));
                case KNOCKOUTS -> metricValues.add(metricMapper.toMetricValue(refMetric, getKnockoutsTop(ONLY_TOP)));
                case MOST_PRIZES -> metricValues.add(metricMapper.toMetricValue(refMetric, getMostPrizesTop(ONLY_TOP)));
                case MOST_REBUY -> metricValues.add(metricMapper.toMetricValue(refMetric, getMostRebuyTop(ONLY_TOP)));
                case LOSERS -> metricValues.add(metricMapper.toMetricValue(refMetric, getLosersTop(ONLY_TOP)));
                default -> log.info("Illegal argument {}", metricCode.name());
            }
        });
        return metricValues.stream()
                .sorted(Comparator.comparing(MetricValue::getMetricOrder))
                .collect(Collectors.toList());
    }

    private List<StatDataDto> getTopPlacesByPlace(TopPlacesEnum topPlacesEnum, boolean isOnlyTop) {
        return isOnlyTop ?
                topPlaceRepository.findAllByPlace(topPlacesEnum.getPlace(), TOP)
                        .stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList()) :
                topPlaceRepository.findAllByPlace(topPlacesEnum.getPlace())
                        .stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList());
    }

    private List<StatDataDto> getChristmasTop(boolean isOnlyTop) {
        return isOnlyTop ?
                christmasTopRepository.findAll(TOP).stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList()) :
                christmasTopRepository.findAll().stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList());
    }

    private List<StatDataDto> getBablTop(boolean isOnlyTop) {
        return isOnlyTop ?
                bablTopRepository.findAll(TOP).stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList()) :
                bablTopRepository.findAll().stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList());
    }

    private List<StatDataDto> getKnockoutsTop(boolean isOnlyTop) {
        return isOnlyTop ?
                knockoutTopRepository.findAll(TOP).stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList()) :
                knockoutTopRepository.findAll().stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList());
    }

    private List<StatDataDto> getMostPrizesTop(boolean isOnlyTop) {
        return isOnlyTop ?
                mostPrizesTopRepository.findAll(TOP).stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList()) :
                mostPrizesTopRepository.findAll().stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList());
    }

    private List<StatDataDto> getMostRebuyTop(boolean isOnlyTop) {
        return isOnlyTop ?
                mostRebuyTopRepository.findAll(TOP).stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList()) :
                mostRebuyTopRepository.findAll().stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList());
    }

    private List<StatDataDto> getLosersTop(boolean isOnlyTop) {
        return isOnlyTop ?
                losersTopRepository.findAll(TOP).stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList()) :
                losersTopRepository.findAll().stream()
                        .map(statMapper::toStatDataDto)
                        .collect(Collectors.toList());
    }

}
