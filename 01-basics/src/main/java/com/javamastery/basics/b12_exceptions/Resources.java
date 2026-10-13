package com.javamastery.basics.b12_exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * B12 · try-with-resources, suppressed exceptions, finally, retry.
 */
public final class Resources {
    private Resources() {}

    /** Ресурс, который логирует открытие/закрытие. Готов, не меняй. */
    public static class TrackedResource implements AutoCloseable {
        private final String name;
        private final List<String> log;
        private final boolean failOnClose;

        public TrackedResource(String name, List<String> log, boolean failOnClose) {
            this.name = name;
            this.log = log;
            this.failOnClose = failOnClose;
            log.add("open " + name);
        }

        public void use(boolean fail) {
            log.add("use " + name);
            if (fail) throw new IllegalStateException("use failed: " + name);
        }

        @Override
        public void close() {
            log.add("close " + name);
            if (failOnClose) throw new IllegalStateException("close failed: " + name);
        }
    }

    /**
     * Открыть два ресурса "A" и "B" через ОДИН try-with-resources, использовать оба (сначала A, потом B),
     * передав failUse в use() ресурса B. Ресурсу A передай failOnClose=failCloseA.
     * Исключения не ловить — пусть летят наружу (тест проверит основное исключение и suppressed).
     * Обрати внимание, в каком порядке закрываются ресурсы.
     */
    public static void useTwo(List<String> log, boolean failUse, boolean failCloseA) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Выполнить задачу; при исключении повторить, всего не более maxAttempts попыток.
     * Если все попытки неудачны — бросить ПОСЛЕДНЕЕ исключение, добавив к нему все предыдущие как suppressed.
     * Checked-исключения оборачивать в RuntimeException не нужно — метод объявляет throws Exception.
     * maxAttempts &lt; 1 → IllegalArgumentException.
     */
    public static <T> T retry(Callable<T> task, int maxAttempts) throws Exception {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Вопрос с собеседований, который многих ловит. Что вернёт этот метод? Сначала ответь сам, потом запусти тест.
     * НЕ МЕНЯЙ этот метод. В тесте впиши ответ в константу.
     */
    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public static int tricky() {
        int x = 1;
        try {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

    /** И ещё один. НЕ МЕНЯЙ. */
    @SuppressWarnings({"finally"})
    public static int trickier() {
        try {
            throw new RuntimeException("boom");
        } finally {
            return 42;
        }
    }
}
