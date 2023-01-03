package ru.mitch.helper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.mitch.exception.NotFoundEntityException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
public class ExtractorContentFile {

    public static String getTemplateMessage(String fileName) {
        try {
            Resource resource = new ClassPathResource("message_template/" + fileName,
                    ExtractorContentFile.class.getClassLoader());

            byte[] content = resource.getInputStream().readAllBytes();
            String templateMessage = new String(content, StandardCharsets.UTF_8);

            return templateMessage;
        } catch (IOException e) {
            String exceptionMessage = "Not found file " + fileName;
            log.error(exceptionMessage);
            throw new NotFoundEntityException(exceptionMessage);
        }
    }

}
