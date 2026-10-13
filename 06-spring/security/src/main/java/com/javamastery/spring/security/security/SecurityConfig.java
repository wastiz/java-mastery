package com.javamastery.spring.security.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * S06 · Конфигурация безопасности. Нужно объявить бины:
 * <ul>
 *   <li>{@code SecurityFilterChain} — правила доступа из README, stateless, без CSRF, resource server с JWT;</li>
 *   <li>{@code PasswordEncoder} — BCrypt;</li>
 *   <li>{@code JwtEncoder} / {@code JwtDecoder} — HS256 на ключе из {@link JwtProperties#secret()} (base64);</li>
 *   <li>конвертер: authorities из claim "roles" с префиксом "ROLE_" (чтобы работал hasRole("ADMIN")).</li>
 * </ul>
 */
// TODO: аннотации
public class SecurityConfig {

    // TODO: бины
}
