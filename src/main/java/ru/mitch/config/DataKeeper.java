package ru.mitch.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mitch.model.Role;
import ru.mitch.model.Status;
import ru.mitch.repository.RoleRepository;
import ru.mitch.repository.StatusRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DataKeeper {

    @Getter
    private final Map<String, Role> roles;
    @Getter
    private final Map<String, Status> statuses;
    private final Map<String, Map<String, Status>> statusesByType;

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
        statusesByType = statuses.values().stream()
                .collect(Collectors.groupingBy(Status::getType,
                        Collectors.toMap(Status::getCode, Function.identity())));
    }

    public List<Status> getStatusesByType(String type) {
        return statusesByType.get(type).values().stream().toList();
    }

}
