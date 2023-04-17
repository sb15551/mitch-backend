package ru.mitch.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mitch.dto.stat.CommonStatDto;
import ru.mitch.service.CommonStatService;

@Component
public class CommonStatKeeper {

    @Getter
    private CommonStatDto commonStatDto;

    private final CommonStatService commonStatService;

    @Autowired
    public CommonStatKeeper(CommonStatService commonStatService) {
        this.commonStatService = commonStatService;
        initCommonStat();
    }

    public void initCommonStat() {
        commonStatDto = new CommonStatDto();
        commonStatDto.setCountGamesPlayed(commonStatService.getCountGamesPlayed());
        commonStatDto.setMetricsValues(commonStatService.getMetricsValues());
    }

    public CommonStatDto getCommonStat() {
        return commonStatDto;
    }

}
