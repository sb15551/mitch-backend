package ru.mitch.security.jwt;

import lombok.NoArgsConstructor;
import ru.mitch.model.Player;

@NoArgsConstructor
public class JwtUserFactory {

    public static JwtUser create(Player player) {
        return new JwtUser(player);
    }

}
