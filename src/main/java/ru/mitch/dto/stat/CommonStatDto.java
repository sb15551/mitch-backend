package ru.mitch.dto.stat;

import lombok.Data;

import java.util.List;

@Data
public class CommonStatDto {

    private int countGamesPlayed;

    private List<MetricValue> metricsValues;

}
