package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.TournamentParticipant;

public interface TournamentParticipantRepository extends JpaRepository<TournamentParticipant, Long> {
}
