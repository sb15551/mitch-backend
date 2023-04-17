package ru.mitch.mapping;

import org.mapstruct.Mapper;
import ru.mitch.dto.stat.MetricValue;
import ru.mitch.dto.stat.StatDataDto;
import ru.mitch.model.RefMetric;

import java.util.List;

@Mapper
public interface MetricMapper {

    MetricValue toMetricValue(RefMetric refMetric, List<StatDataDto> values);

}
