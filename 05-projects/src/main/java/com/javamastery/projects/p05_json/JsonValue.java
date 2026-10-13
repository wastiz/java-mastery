package com.javamastery.projects.p05_json;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * P05 · Модель JSON — sealed-иерархия records. Уже готова, менять не нужно.
 */
public sealed interface JsonValue {

    record JsonNull() implements JsonValue {
        public static final JsonNull INSTANCE = new JsonNull();
    }

    record JsonBool(boolean value) implements JsonValue {}

    record JsonNumber(BigDecimal value) implements JsonValue {}

    record JsonString(String value) implements JsonValue {}

    record JsonArray(List<JsonValue> items) implements JsonValue {
        public JsonArray {
            items = List.copyOf(items);
        }
    }

    /** Порядок ключей сохраняется (LinkedHashMap внутри). */
    record JsonObject(Map<String, JsonValue> fields) implements JsonValue {
        public JsonObject {
            fields = java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<>(fields));
        }

        public JsonValue get(String key) {
            return fields.get(key);
        }
    }
}
