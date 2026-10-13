package com.javamastery.spring.security.security;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

/**
 * S06 · Выпуск JWT. Используй JwtEncoder: заголовок {@code JwsHeader.with(MacAlgorithm.HS256)},
 * claims через {@code JwtClaimsSet.builder()}: issuer "java-mastery", subject, issuedAt, expiresAt, claim "roles" (List&lt;String&gt;, по алфавиту).
 */
@Service
public class TokenService {

    // TODO: зависимости (JwtEncoder, JwtProperties) и конструктор

    /** Токен со сроком жизни из настроек. */
    public String issue(String username, Collection<String> roles) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Токен с явным сроком жизни (отрицательный — уже истёкший, пригодится в тестах). */
    public String issue(String username, Collection<String> roles, Duration ttl) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Срок жизни токена в секундах — для ответа логина. */
    public long ttlSeconds() {
        throw new UnsupportedOperationException("TODO");
    }
}
