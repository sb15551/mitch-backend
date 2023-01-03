package ru.mitch.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequestDto {

    @NotNull(message = "Login can't be null")
    private String login;

    @NotNull(message = "Password can't be null")
    private String password;

}
