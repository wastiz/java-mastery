package com.javamastery.projects.p05_json;

/** Ошибка разбора с позицией (индекс символа во входной строке). Готово. */
public class JsonParseException extends RuntimeException {
    private final int position;

    public JsonParseException(String message, int position) {
        super(message + " at position " + position);
        this.position = position;
    }

    public int position() {
        return position;
    }
}
