package ru.mitch.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.view.MostRebuy;

public interface MostRebuyTopRepository extends JpaRepository<MostRebuy, Long> {
}
