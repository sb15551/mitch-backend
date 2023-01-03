package ru.mitch.telegram;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "bot")
public class TelegramProperty {

    private String mainName;

    private String mainToken;

    private String supportName;

    private String supportToken;

}
