package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByCode(String code);

}
