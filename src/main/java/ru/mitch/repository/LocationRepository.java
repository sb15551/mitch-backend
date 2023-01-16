package ru.mitch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mitch.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Page<Location> findAll(Pageable pageable);

    @Query("select count(*) from Location ")
    Integer countAll();

}
