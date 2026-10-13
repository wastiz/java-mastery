package com.javamastery.spring.core.messaging;

/** Канал отправки сообщений. Готово. */
public interface MessageSender {
    /** "email" или "sms". */
    String channel();

    /** Возвращает «квитанцию» об отправке — для тестов. */
    String send(String to, String text);
}
