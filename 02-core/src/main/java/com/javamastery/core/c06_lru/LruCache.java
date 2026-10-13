package com.javamastery.core.c06_lru;

/**
 * C06 · LRU-кэш. Классическая задача (LeetCode 146) — её часто просят написать прямо на собесе.
 *
 * <p>Это интерфейс. Реализуй его ДВАЖДЫ:
 * <ol>
 *   <li>{@link LinkedHashMapLru} — через наследование LinkedHashMap (accessOrder=true + removeEldestEntry) — 10 строк;</li>
 *   <li>{@link ManualLru} — HashMap + собственный двусвязный список, все операции O(1). Именно это хотят увидеть.</li>
 * </ol>
 */
public interface LruCache<K, V> {
    /** Значение или null. Обращение делает ключ «самым свежим». */
    V get(K key);

    /** Положить/обновить; ключ становится самым свежим. Если превышена ёмкость — вытеснить самый старый. */
    void put(K key, V value);

    int size();

    /** Ключи от самого старого к самому свежему (для тестов). */
    java.util.List<K> keysOldestFirst();
}
