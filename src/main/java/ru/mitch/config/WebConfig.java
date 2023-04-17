package ru.mitch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(StringToEnumConverterFactory.INSTANCE);
    }

    @ReadingConverter
    public enum StringToEnumConverterFactory implements ConverterFactory<String, Enum> {
        INSTANCE;

        @Override
        @SuppressWarnings("unchecked")
        public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
            return source -> (T) Enum.valueOf(targetType, source.toUpperCase());
        }
    }

}
