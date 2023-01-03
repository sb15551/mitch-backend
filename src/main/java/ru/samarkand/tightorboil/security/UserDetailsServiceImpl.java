package ru.samarkand.tightorboil.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.samarkand.tightorboil.model.Player;
import ru.samarkand.tightorboil.repository.PlayerRepository;
import ru.samarkand.tightorboil.security.jwt.JwtUser;
import ru.samarkand.tightorboil.security.jwt.JwtUserFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByLogin(username);

        if (player == null) {
            throw new UsernameNotFoundException("Player with login: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(player);
        log.info("IN loadUserByUsername - player with id: {} successfully loaded", jwtUser.getId());
        return jwtUser;
    }

}
