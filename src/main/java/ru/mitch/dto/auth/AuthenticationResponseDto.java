package ru.mitch.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {

    private Long id;

    private String login;

    private String token;

}
