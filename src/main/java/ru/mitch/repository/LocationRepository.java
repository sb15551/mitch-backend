package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
