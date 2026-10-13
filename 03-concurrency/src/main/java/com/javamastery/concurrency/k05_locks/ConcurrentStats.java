package com.javamastery.concurrency.k05_locks;

import java.util.Map;
import java.util.function.Function;

/**
 * K05 · ConcurrentHashMap (merge/compute атомарны!), ReadWriteLock, check-then-act.
 */
public class ConcurrentStats {

    // TODO: поля

    /** Вызывается из многих потоков одновременно. Ни один инкремент не должен потеряться. */
    public void record(String word) {
        throw new UnsupportedOperationException("TODO");
    }

    public long count(String word) {
        throw new UnsupportedOperationException("TODO");
    }

    public Map<String, Long> snapshot() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Кэш «вычислить один раз»: даже если 100 потоков одновременно попросят один ключ,
     * loader должен быть вызван для него РОВНО ОДИН раз. Подсказка: computeIfAbsent.
     */
    public static <K, V> Function<K, V> onceCache(Function<K, V> loader) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Конфигурация, которую часто читают и редко пишут: ReentrantReadWriteLock.
     * Читатели не блокируют друг друга; писатель — эксклюзивно.
     */
    public static class Config {
        // TODO: поля

        public String get(String key) {
            throw new UnsupportedOperationException("TODO");
        }

        /** Атомарно заменить несколько значений: читатель никогда не должен увидеть «половину» обновления. */
        public void putAll(Map<String, String> updates) {
            throw new UnsupportedOperationException("TODO");
        }

        /** Прочитать согласованную пару значений (под одним read-lock). */
        public String[] getPair(String k1, String k2) {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
