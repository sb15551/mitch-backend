package ru.mitch.service;

import ru.mitch.dto.MetricCodeEnum;
import ru.mitch.dto.stat.MetricValue;

import java.util.List;

public interface CommonStatService {

    MetricValue getMetricValueByCode(MetricCodeEnum metricCode);

    Integer getCountGamesPlayed();

    List<MetricValue> getMetricsValues();

}
