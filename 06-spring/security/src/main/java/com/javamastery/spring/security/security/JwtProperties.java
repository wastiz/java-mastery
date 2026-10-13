package com.javamastery.spring.security.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/** Типобезопасные настройки из application.yml (app.jwt.*). Готово. */
@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(String secret, Duration ttl) {}
