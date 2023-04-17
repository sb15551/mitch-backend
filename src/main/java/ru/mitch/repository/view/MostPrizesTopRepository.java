package ru.mitch.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.view.MostPrize;

public interface MostPrizesTopRepository extends JpaRepository<MostPrize, Long> {
}
