package ru.samarkand.tightorboil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samarkand.tightorboil.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByCode(String code);

}
