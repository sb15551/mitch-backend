package ru.samarkand.tightorboil.dto.telegram;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TelegramButtonEnum {

    START("/start"),
    REGISTER("/register"),
    FORGOT("/forgot"),
    HELP("/help"),
    DEFAULT("");

    private final String command;

    public static TelegramButtonEnum of(String command) {
        return Arrays.stream(values())
                .filter(button -> button.getCommand().equals(command))
                .findFirst()
                .orElse(DEFAULT);
    }
}
