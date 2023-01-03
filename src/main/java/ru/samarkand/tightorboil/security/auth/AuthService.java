package ru.samarkand.tightorboil.security.auth;

import ru.samarkand.tightorboil.dto.auth.AuthenticationRequestDto;
import ru.samarkand.tightorboil.dto.auth.AuthenticationResponseDto;

public interface AuthService {

    AuthenticationResponseDto login(AuthenticationRequestDto requestDto);

}
