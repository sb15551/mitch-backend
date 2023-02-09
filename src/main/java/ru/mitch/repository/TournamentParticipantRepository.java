package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.Player;
import ru.mitch.model.Tournament;
import ru.mitch.model.TournamentParticipant;

public interface TournamentParticipantRepository extends JpaRepository<TournamentParticipant, Long> {

    TournamentParticipant findByTournamentAndPlayer(Tournament tournament, Player player);

    Boolean existsByTournamentAndPlayer(Tournament tournament, Player player);

}
