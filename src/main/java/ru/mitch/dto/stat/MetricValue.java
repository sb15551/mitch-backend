package ru.mitch.dto.stat;

import lombok.Data;

import java.util.List;

@Data
public class MetricValue {

    private String metricCode;

    private String metricName;

    private Integer metricOrder;

    private List<StatDataDto> values;

}
