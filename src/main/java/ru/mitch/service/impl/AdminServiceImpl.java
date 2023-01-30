package ru.mitch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mitch.config.DataKeeper;
import ru.mitch.dto.AdminConfigResponse;
import ru.mitch.dto.RoleCodeEnum;
import ru.mitch.dto.StatusDto;
import ru.mitch.dto.StatusTypeEnum;
import ru.mitch.mapping.StatusMapper;
import ru.mitch.service.AdminService;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final DataKeeper dataKeeper;
    private final StatusMapper statusMapper;

    @Override
    public AdminConfigResponse getConfig() {
        List<StatusDto> statusDtoList = statusMapper.toListDto(dataKeeper.getStatusesByType(StatusTypeEnum.TOURNAMENT.name()))
                .stream()
                .sorted(Comparator.comparing(StatusDto::getId))
                .toList();
        return AdminConfigResponse.builder()
                .roles(dataKeeper.getRoles().values().stream()
                        .filter(role -> !role.getCode().equals(RoleCodeEnum.ROOT.name()))
                        .toList())
                .statuses(statusDtoList)
                .build();
    }

}
