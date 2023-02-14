package ru.mitch.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.view.TopPlace;

public interface TopPlaceRepository extends JpaRepository<TopPlace, Long> {
}
