package com.javamastery.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Clock;

/**
 * S01 · Java-конфигурация контекста — точка входа. Тесты создают контекст так:
 * {@code new AnnotationConfigApplicationContext(AppConfig.class)}.
 *
 * <p>Задание:
 * <ol>
 *   <li>пометь класс как конфигурацию;</li>
 *   <li>включи сканирование компонентов пакета {@code com.javamastery.spring.core};</li>
 *   <li>подключи файл {@code classpath:app.properties};</li>
 *   <li>объяви бин {@link Clock} (UTC) — его инжектят другие бины.</li>
 * </ol>
 * Внимание: мы НЕ используем Spring Boot здесь, только «голый» Spring Framework — так лучше видно, что происходит.
 */
// TODO: аннотации конфигурации
public class AppConfig {

    // TODO: бин Clock
}
