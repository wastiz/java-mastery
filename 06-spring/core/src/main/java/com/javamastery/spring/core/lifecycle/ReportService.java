package com.javamastery.spring.core.lifecycle;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

/**
 * S01 · Singleton, которому на КАЖДЫЙ вызов нужен свежий prototype-бин RequestContext.
 * Внедри {@code ObjectProvider<RequestContext>} и Clock (бин из AppConfig).
 * {@code generate()} → "report " + id нового RequestContext + " at " + Instant из clock.
 */
// TODO: аннотация
public class ReportService {

    // TODO: поля и конструктор

    public String generate() {
        throw new UnsupportedOperationException("TODO");
    }
}
