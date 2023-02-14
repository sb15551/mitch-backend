package ru.mitch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TopPlacesEnum {

    FIRST(1),
    SECOND(2),
    THIRD(3);

    private final int place;

}
