package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.Player;
import ru.mitch.model.TelegramData;

public interface TelegramDataRepository extends JpaRepository<TelegramData, Long> {

    Boolean existsByChatId(long chatId);

    TelegramData findByPlayer(Player player);

}
