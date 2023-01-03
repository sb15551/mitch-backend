package ru.samarkand.tightorboil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samarkand.tightorboil.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByCode(String code);

}
