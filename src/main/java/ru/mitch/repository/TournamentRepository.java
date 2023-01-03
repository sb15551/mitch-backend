package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
