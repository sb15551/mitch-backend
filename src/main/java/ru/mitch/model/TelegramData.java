package ru.mitch.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TelegramData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

    private String type;

    @ManyToOne
    private Player player;

}
