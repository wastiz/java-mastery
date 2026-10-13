package com.javamastery.projects.p05_json;

import com.javamastery.projects.p05_json.JsonValue.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    @Test
    void literalsAndNumbers() {
        assertEquals(JsonNull.INSTANCE, Json.parse("null"));
        assertEquals(new JsonBool(true), Json.parse(" true "));
        assertEquals(new JsonNumber(new BigDecimal("-12.5e3")), Json.parse("-12.5e3"));
        assertEquals(new JsonNumber(new BigDecimal("0")), Json.parse("0"));
        assertEquals(new JsonNumber(new BigDecimal("12345678901234567890.123")), Json.parse("12345678901234567890.123"));
    }

    @Test
    void strings() {
        assertEquals(new JsonString("a\"b\\c/d\ne\tf"), Json.parse("\"a\\\"b\\\\c\\/d\\ne\\tf\""));
        assertEquals(new JsonString("Привет ☃"), Json.parse("\"\\u041f\\u0440\\u0438\\u0432\\u0435\\u0442 \\u2603\""));
        assertEquals(new JsonString(""), Json.parse("\"\""));
    }

    @Test
    void nested() {
        JsonValue v = Json.parse("""
                {
                  "name": "Valeri",
                  "skills": ["java", "spring", {"level": 3}],
                  "remote": true,
                  "manager": null,
                  "empty": {}, "list": []
                }""");
        JsonObject o = assertInstanceOf(JsonObject.class, v);
        assertEquals(List.of("name", "skills", "remote", "manager", "empty", "list"), List.copyOf(o.fields().keySet()));
        JsonArray skills = assertInstanceOf(JsonArray.class, o.get("skills"));
        assertEquals(new JsonString("spring"), skills.items().get(1));
        assertEquals(new JsonObject(Map.of("level", new JsonNumber(new BigDecimal(3)))), skills.items().get(2));
    }

    @Test
    void errorsWithPosition() {
        assertError("", 0);
        assertError("[1, 2", 5);
        assertError("[1, 2,]", 6);
        assertError("{\"a\" 1}", 5);
        assertError("{a: 1}", 1);
        assertError("\"unterminated", 13);
        assertError("tru", 0);
        assertError("01", 1);
        assertError("[1] x", 4);
        assertError("{\"a\":1,}", 7);
        assertError("-", 1);
        assertError("1.", 2);
        assertError("\"bad \\q escape\"", 6);
    }

    private void assertError(String text, int position) {
        JsonParseException e = assertThrows(JsonParseException.class, () -> Json.parse(text), "должно падать: " + text);
        assertEquals(position, e.position(), "позиция ошибки для: " + text + " (" + e.getMessage() + ")");
    }

    @Test
    void stringify() {
        String text = "{\"a\":[1,2.50,-3E+2],\"b\":{\"c\":null,\"d\":false},\"s\":\"q\\\"\\\\\\n\\u0001\"}";
        JsonValue v = Json.parse(text);
        assertEquals("{\"a\":[1,2.50,-3E+2],\"b\":{\"c\":null,\"d\":false},\"s\":\"q\\\"\\\\\\n\\u0001\"}", Json.stringify(v));
        assertEquals(v, Json.parse(Json.stringify(v)));
    }

    @Test
    void deepNesting() {
        String deep = "[".repeat(500) + "]".repeat(500);
        assertEquals(deep, Json.stringify(Json.parse(deep)));
    }
}
