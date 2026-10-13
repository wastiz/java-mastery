package com.javamastery.spring.core.messaging;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * S01 · Внедрение через конструктор (единственно правильный способ — объясни на собесе, почему).
 *
 * <ul>
 *   <li>получи ВСЕ реализации MessageSender списком (Spring умеет внедрять List&lt;Interface&gt;);</li>
 *   <li>получи TextFormatter по умолчанию (будет @Primary) и HTML-форматтер по @Qualifier("html");</li>
 *   <li>{@code notify(channel, to, text)} — отформатировать plain-форматтером и отправить через sender с нужным каналом;
 *       неизвестный канал → IllegalArgumentException;</li>
 *   <li>{@code notifyHtml(to, text)} — email с html-форматированием;</li>
 *   <li>{@code channels()} — список доступных каналов по алфавиту.</li>
 * </ul>
 */
// TODO: аннотация
public class NotificationService {

    // TODO: поля и конструктор

    public String notify(String channel, String to, String text) {
        throw new UnsupportedOperationException("TODO");
    }

    public String notifyHtml(String to, String text) {
        throw new UnsupportedOperationException("TODO");
    }

    public List<String> channels() {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы
}
