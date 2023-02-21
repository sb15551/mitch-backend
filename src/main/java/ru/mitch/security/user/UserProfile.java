package ru.mitch.security.user;

import lombok.Getter;
import ru.mitch.model.Role;

@Getter
public class UserProfile {

    private final Long id;

    private final String login;

    private final String name;

    private final String surname;

    private final Role role;

    public UserProfile(Long id, String login, String name, String surname, Role role) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
}
