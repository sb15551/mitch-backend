package ru.mitch.repository.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.view.TopPlace;

import java.util.List;

public interface TopPlaceRepository extends JpaRepository<TopPlace, Long> {

    Page<TopPlace> findAllByPlace(Integer place, Pageable pageable);

    List<TopPlace> findAllByPlace(Integer place);

}
