package ru.mitch.dto.auth;

import lombok.Data;
import ru.mitch.model.Player;

@Data
public class AuthenticationResponseDto {

    private Long id;

    private String login;

    private String token;

    private String name;

    private String surname;

    public AuthenticationResponseDto(Player player, String token) {
        this.login = player.getLogin();
        this.token = token;
        id = player.getId();
        name = player.getName();
        surname = player.getSurname();
    }

}
