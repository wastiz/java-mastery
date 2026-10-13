package com.javamastery.spring.core.messaging;

import org.springframework.stereotype.Component;

/** S01 · Тоже бин. send → "sms to {to}: {text}" (текст обрезается до 20 символов). */
// TODO: аннотация
public class SmsSender implements MessageSender {

    @Override
    public String channel() {
        return "sms";
    }

    @Override
    public String send(String to, String text) {
        return "sms to " + to + ": " + (text.length() > 20 ? text.substring(0, 20) : text);
    }
}
