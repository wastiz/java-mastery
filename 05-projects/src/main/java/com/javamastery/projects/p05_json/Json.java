package com.javamastery.projects.p05_json;

import com.javamastery.projects.p05_json.JsonValue.*;

/**
 * P05 · Свой JSON-парсер (recursive descent) и сериализатор. Очень хороший проект для «вспомнить, как думать».
 *
 * <ul>
 *   <li>поддержать: null, true, false, числа (целые, дробные, с экспонентой, отрицательные) → BigDecimal,
 *       строки с escape-последовательностями (\" \\ \/ \b \f \n \r \t \\uXXXX), массивы, объекты;</li>
 *   <li>пробелы между токенами допустимы (пробел, \t, \n, \r);</li>
 *   <li>любой мусор после значения, незакрытые скобки/строки, лишние запятые, ключ не-строка, ведущие нули ("01") —
 *       {@link JsonParseException} с позицией, где обнаружена ошибка;</li>
 *   <li>{@code stringify} — компактный вывод без пробелов; строки экранируются (кавычка, обратный слэш, управляющие символы
 *       &lt; 0x20 как \\uXXXX, кроме \n \r \t \b \f), числа — {@code BigDecimal.toString()}.</li>
 * </ul>
 */
public final class Json {
    private Json() {}

    public static JsonValue parse(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    public static String stringify(JsonValue value) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: внутренний парсер и вспомогательные методы
}
