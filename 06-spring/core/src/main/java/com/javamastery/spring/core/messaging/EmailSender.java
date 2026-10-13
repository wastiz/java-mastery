package com.javamastery.spring.core.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * S01 · Сделай класс бином. Имя отправителя возьми из свойства {@code app.sender-name} через {@code @Value}.
 * send возвращает строку: "email to {to} from {senderName}: {text}".
 */
// TODO: аннотация
public class EmailSender implements MessageSender {

    private final String senderName;

    // TODO: внедрение свойства в конструктор

    public EmailSender() { this.senderName = null; } // TODO: замени на конструктор с @Value

    @Override
    public String channel() {
        return "email";
    }

    @Override
    public String send(String to, String text) {
        return "email to " + to + " from " + senderName + ": " + text;
    }
}
