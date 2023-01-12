package ru.mitch.dto.player;

import lombok.Data;

@Data
public class PlayerResponseDto extends PlayerDto {

    private RoleDto role;

}
