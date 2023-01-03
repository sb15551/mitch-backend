package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mitch.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByLogin(String login);

    @Query("from Player p where p.role.code = 'ROOT'")
    Player findByWithRoleRoot();

}
