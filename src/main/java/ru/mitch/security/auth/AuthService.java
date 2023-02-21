package ru.mitch.security.auth;

import ru.mitch.dto.auth.AuthenticationRequestDto;
import ru.mitch.dto.auth.AuthenticationResponseDto;
import ru.mitch.dto.player.SettingsRequestDto;

public interface AuthService {

    AuthenticationResponseDto login(AuthenticationRequestDto requestDto);

    AuthenticationResponseDto saveSettings(SettingsRequestDto request);

}
