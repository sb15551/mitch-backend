package ru.samarkand.tightorboil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samarkand.tightorboil.model.Player;
import ru.samarkand.tightorboil.model.TelegramData;

public interface TelegramDataRepository extends JpaRepository<TelegramData, Long> {

    Boolean existsByChatId(long chatId);

    TelegramData findByPlayer(Player player);

}
