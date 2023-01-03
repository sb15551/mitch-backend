package ru.samarkand.tightorboil.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.samarkand.tightorboil.dto.auth.AuthenticationRequestDto;
import ru.samarkand.tightorboil.dto.auth.AuthenticationResponseDto;
import ru.samarkand.tightorboil.security.auth.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }

}
