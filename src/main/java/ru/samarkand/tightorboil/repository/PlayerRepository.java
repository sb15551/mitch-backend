package ru.samarkand.tightorboil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samarkand.tightorboil.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByLogin(String login);

}
