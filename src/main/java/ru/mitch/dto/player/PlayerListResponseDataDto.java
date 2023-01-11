package ru.mitch.dto.player;

import lombok.Data;

@Data
public class PlayerListResponseDataDto {

    private Long id;

    private String fullName;

    private RoleDto role;

    private String telegramId;

    private Long telegramChatId;

}
