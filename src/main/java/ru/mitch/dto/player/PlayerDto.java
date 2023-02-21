package ru.mitch.dto.player;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlayerDto {

    private Long id;

    private String login;

    private String name;

    private String surname;

    private LocalDateTime createdDate;

    private Long chatId;

}
