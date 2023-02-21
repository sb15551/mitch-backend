package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByCode(String code);

}
