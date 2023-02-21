package ru.mitch.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mitch.controller.CommonController;
import ru.mitch.dto.auth.AuthenticationRequestDto;
import ru.mitch.dto.auth.AuthenticationResponseDto;
import ru.mitch.security.auth.AuthService;

@RestController
@RequiredArgsConstructor
@Validated
public class AuthController extends CommonController {

    private final AuthService authService;

    @PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, String.join(",", "*"))
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, String.join(",", "*"))
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, String.join(",", "*"))
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString())
                .body(authService.login(requestDto));
    }

}
