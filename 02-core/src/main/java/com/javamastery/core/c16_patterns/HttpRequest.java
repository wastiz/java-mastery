package com.javamastery.core.c16_patterns;

import java.util.List;
import java.util.Map;

/**
 * C16 · Builder. Неизменяемый HTTP-запрос со множеством необязательных параметров.
 *
 * <ul>
 *   <li>HttpRequest.builder("https://api.example.com/users").method("POST").header("Accept", "json")
 *       .query("page", "2").body("{...}").timeoutMs(5000).build();</li>
 *   <li>по умолчанию: method GET, timeout 30000, без тела;</li>
 *   <li>url обязателен и должен начинаться с http:// или https://; иначе IllegalArgumentException в builder(...);</li>
 *   <li>body у GET → IllegalStateException в build();</li>
 *   <li>header с тем же именем (без учёта регистра) — значения накапливаются в списке;</li>
 *   <li>fullUrl() — url + "?a=1&amp;b=2" в порядке добавления параметров (без URL-энкодинга).</li>
 * </ul>
 */
public final class HttpRequest {
    // TODO: поля

    private HttpRequest(Builder b) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Builder builder(String url) {
        throw new UnsupportedOperationException("TODO");
    }

    public String url() {
        throw new UnsupportedOperationException("TODO");
    }

    public String method() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Заголовки по имени; поиск без учёта регистра; нет — пустой список. */
    public List<String> header(String name) {
        throw new UnsupportedOperationException("TODO");
    }

    public String body() {
        throw new UnsupportedOperationException("TODO");
    }

    public int timeoutMs() {
        throw new UnsupportedOperationException("TODO");
    }

    public String fullUrl() {
        throw new UnsupportedOperationException("TODO");
    }

    public static final class Builder {
        // TODO: поля билдера

        private Builder(String url) {
            throw new UnsupportedOperationException("TODO");
        }

        public Builder method(String method) {
            throw new UnsupportedOperationException("TODO");
        }

        public Builder header(String name, String value) {
            throw new UnsupportedOperationException("TODO");
        }

        public Builder query(String name, String value) {
            throw new UnsupportedOperationException("TODO");
        }

        public Builder body(String body) {
            throw new UnsupportedOperationException("TODO");
        }

        public Builder timeoutMs(int ms) {
            throw new UnsupportedOperationException("TODO");
        }

        public HttpRequest build() {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
