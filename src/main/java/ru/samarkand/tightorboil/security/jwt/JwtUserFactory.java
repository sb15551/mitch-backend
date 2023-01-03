package ru.samarkand.tightorboil.security.jwt;

import lombok.NoArgsConstructor;
import ru.samarkand.tightorboil.model.Player;

@NoArgsConstructor
public class JwtUserFactory {

    public static JwtUser create(Player player) {
        return new JwtUser(player);
    }

}
