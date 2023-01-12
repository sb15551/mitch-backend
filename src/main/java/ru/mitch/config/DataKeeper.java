package ru.mitch.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mitch.model.Role;
import ru.mitch.model.Status;
import ru.mitch.repository.RoleRepository;
import ru.mitch.repository.StatusRepository;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DataKeeper {

    @Getter
    private Map<String, Role> roles;
    @Getter
    private Map<String, Status> statuses;

    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public DataKeeper(RoleRepository roleRepository, StatusRepository statusRepository) {
        this.roleRepository = roleRepository;
        this.statusRepository = statusRepository;
        roles = roleRepository.findAll().stream()
                .collect(Collectors.toMap(Role::getCode, Function.identity()));
        statuses = statusRepository.findAll().stream()
                .collect(Collectors.toMap(Status::getCode, Function.identity()));
    }

}
