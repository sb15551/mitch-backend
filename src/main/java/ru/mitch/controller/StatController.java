package ru.mitch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mitch.config.CommonStatKeeper;
import ru.mitch.dto.stat.CommonStatDto;

@RestController
@RequiredArgsConstructor
public class StatController extends CommonController {

    private final CommonStatKeeper commonStatKeeper;

    @GetMapping(value = "/stat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonStatDto> getCommonStat() {
        return ResponseEntity.ok(commonStatKeeper.getCommonStat());
    }

}
