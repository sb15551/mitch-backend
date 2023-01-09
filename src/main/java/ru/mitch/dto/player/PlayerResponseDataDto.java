package ru.mitch.dto.player;

import lombok.Data;

@Data
public class PlayerResponseDataDto {

    private Long id;

    private String fullName;

    private String role;

    private String telegramId;

    private Long telegramChatId;

}
