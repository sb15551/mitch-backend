package ru.samarkand.tightorboil.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.samarkand.tightorboil.dto.auth.AuthenticationRequestDto;
import ru.samarkand.tightorboil.dto.auth.AuthenticationResponseDto;
import ru.samarkand.tightorboil.model.Player;
import ru.samarkand.tightorboil.security.jwt.JwtTokenProvider;
import ru.samarkand.tightorboil.service.PlayerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final PlayerService playerService;

    @Override
    public AuthenticationResponseDto login(AuthenticationRequestDto requestDto) {
        try {
            String login = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, requestDto.getPassword()));
            Player player = playerService.findByLogin(login);

            if (player == null) {
                throw new UsernameNotFoundException("Player with login: " + login + " not found");
            }

            String token = jwtTokenProvider.createToken(player);

            return new AuthenticationResponseDto(login, token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
