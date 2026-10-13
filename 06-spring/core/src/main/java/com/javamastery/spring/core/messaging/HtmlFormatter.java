package com.javamastery.spring.core.messaging;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/** S01 · Альтернативная реализация, доступная по квалификатору "html". */
// TODO: аннотации
public class HtmlFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        return "<p>" + text.strip().replace("<", "&lt;").replace(">", "&gt;") + "</p>";
    }
}
