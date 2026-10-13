package com.javamastery.spring.core.messaging;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/** S01 · Реализация ПО УМОЛЧАНИЮ (её получают все, кто просит TextFormatter без уточнений). */
// TODO: аннотации
public class PlainFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        return text.strip();
    }
}
