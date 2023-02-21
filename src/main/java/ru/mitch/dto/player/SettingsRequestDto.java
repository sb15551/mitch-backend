package ru.mitch.dto.player;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SettingsRequestDto {

    @NotNull
    private Long id;

    private String name;

    private String surname;

    private String password;

}
