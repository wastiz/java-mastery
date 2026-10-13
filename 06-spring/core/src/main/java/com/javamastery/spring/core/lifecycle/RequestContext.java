package com.javamastery.spring.core.lifecycle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * S01 · Scope. Каждый запрос бина из контекста должен давать НОВЫЙ объект (prototype).
 * Вопрос с собеса: что будет, если внедрить prototype-бин в singleton? (Ответ и решения — в теории S01.)
 */
// TODO: аннотации
public class RequestContext {
    private final String id = UUID.randomUUID().toString();

    public String id() {
        return id;
    }
}
