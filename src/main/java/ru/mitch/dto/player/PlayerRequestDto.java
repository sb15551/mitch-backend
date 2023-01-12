package ru.mitch.dto.player;

import lombok.Data;
import ru.mitch.dto.RoleCodeEnum;

@Data
public class PlayerRequestDto extends PlayerDto {

    private RoleCodeEnum role;

}
