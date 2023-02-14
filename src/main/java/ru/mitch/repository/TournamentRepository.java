package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mitch.model.Status;
import ru.mitch.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Query("select count(*) from Tournament")
    Integer countAll();

    Integer countAllByStatus(Status status);

}
