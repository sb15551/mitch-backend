package ru.mitch.dto;

import lombok.Builder;
import lombok.Data;
import ru.mitch.model.Role;

import java.util.List;

@Data
@Builder
public class AdminConfigResponse {

    private List<Role> roles;

    private List<StatusDto> statuses;

}
