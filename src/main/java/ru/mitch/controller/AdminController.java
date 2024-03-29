package ru.mitch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mitch.config.CommonStatKeeper;
import ru.mitch.dto.AdminConfigResponse;
import ru.mitch.service.AdminService;

@RestController
@RequiredArgsConstructor
public class AdminController extends CommonController {

    private final AdminService adminService;
    private final CommonStatKeeper commonStatKeeper;

    @GetMapping(value = "/admin_config", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminConfigResponse> getAdminConfig() {
        return ResponseEntity.ok(adminService.getConfig());
    }

    @PostMapping(value = "/update_common_stat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> initCommonStat() {
        commonStatKeeper.initCommonStat();
        return ResponseEntity.ok().build();
    }

}
