package com.javamastery.spring.core.messaging;

/** Форматирование текста. Две реализации — значит, нужно разрешить неоднозначность. Готово. */
public interface TextFormatter {
    String format(String text);
}
