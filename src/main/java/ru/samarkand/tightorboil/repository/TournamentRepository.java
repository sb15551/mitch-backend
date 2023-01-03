package ru.samarkand.tightorboil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samarkand.tightorboil.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
