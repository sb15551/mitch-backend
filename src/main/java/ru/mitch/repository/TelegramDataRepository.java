package ru.mitch.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mitch.model.Player;
import ru.mitch.model.Status;
import ru.mitch.model.TelegramData;

import java.util.List;

public interface TelegramDataRepository extends JpaRepository<TelegramData, Long> {

    Boolean existsByChatId(long chatId);

    TelegramData findByPlayer(Player player);

    @Query("from TelegramData td where td.player.status = :status order by td.player.createdDate")
    List<TelegramData> findAllActivePlayers(Status status, Pageable pageable);

    @Query("from TelegramData td where td.player.status = :status order by td.player.createdDate")
    List<TelegramData> findAllActivePlayers(Status status);

    @Query("select count(*) from TelegramData td where td.player.status = :status")
    Integer countAllByAllActivePlayers(Status status);

}
