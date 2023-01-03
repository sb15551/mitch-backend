package ru.mitch.security.auth;

import ru.mitch.dto.auth.AuthenticationRequestDto;
import ru.mitch.dto.auth.AuthenticationResponseDto;

public interface AuthService {

    AuthenticationResponseDto login(AuthenticationRequestDto requestDto);

}
