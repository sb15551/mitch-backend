package ru.mitch.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mitch.dto.auth.AuthenticationRequestDto;
import ru.mitch.dto.auth.AuthenticationResponseDto;
import ru.mitch.model.Player;
import ru.mitch.security.jwt.JwtTokenProvider;
import ru.mitch.service.PlayerService;

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
