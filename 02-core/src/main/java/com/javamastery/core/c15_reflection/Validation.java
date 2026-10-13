package com.javamastery.core.c15_reflection;

import java.lang.annotation.*;
import java.util.List;

/**
 * C15 · Свои аннотации + Reflection = мини Bean Validation (как jakarta.validation / Hibernate Validator).
 *
 * <p>Аннотации уже объявлены. Реализуй {@link #validate(Object)}: пройти по ВСЕМ полям объекта
 * (включая private и унаследованные от суперклассов), прочитать значения и проверить аннотации.
 * Вернуть список нарушений в формате "field: message" в порядке объявления полей (сначала поля суперкласса).
 * <ul>
 *   <li>@NotBlank — строка не null и не пустая после strip(); message "must not be blank";</li>
 *   <li>@Min(n) / @Max(n) — для чисел (int/long/Integer/Long); null пропускается; "must be &gt;= n" / "must be &lt;= n";</li>
 *   <li>@Size(min, max) — длина строки или size() коллекции; null пропускается; "size must be between min and max";</li>
 *   <li>@Email — строка содержит ровно одну "@" и точку после неё; null пропускается; "must be a valid email".</li>
 * </ul>
 * Подумай: зачем нужен @Retention(RUNTIME)? Что будет без него?
 */
public final class Validation {
    private Validation() {}

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD)
    public @interface NotBlank {}

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD)
    public @interface Min { long value(); }

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD)
    public @interface Max { long value(); }

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD)
    public @interface Size { int min() default 0; int max() default Integer.MAX_VALUE; }

    @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD)
    public @interface Email {}

    public static List<String> validate(Object obj) {
        throw new UnsupportedOperationException("TODO");
    }
}
