package com.javamastery.core.c05_hashmap;

/**
 * C05 · Свой HashMap — вопрос №1 на собеседованиях по коллекциям.
 *
 * <p>Требования (как в JDK 8+, кроме деревьев в бакетах):
 * <ul>
 *   <li>массив бакетов, в каждом — односвязный список узлов (key, value, hash, next) — разрешение коллизий цепочками;</li>
 *   <li>ёмкость — всегда степень двойки, по умолчанию 16; конструктор с ёмкостью округляет вверх до степени двойки;</li>
 *   <li>индекс бакета = hash &amp; (capacity - 1), где hash = h ^ (h &gt;&gt;&gt; 16) (spread) — зачем так, см. теорию;</li>
 *   <li>loadFactor 0.75: когда size &gt; capacity * 0.75 — увеличить ёмкость вдвое и перераспределить (rehash);</li>
 *   <li>ключ null поддерживается (лежит в бакете 0);</li>
 *   <li>put возвращает предыдущее значение или null; remove — удалённое значение или null.</li>
 * </ul>
 * java.util.HashMap/Hashtable использовать, разумеется, нельзя.
 */
public class MyHashMap<K, V> {
    // TODO: узел, массив бакетов, size

    public MyHashMap() {
        throw new UnsupportedOperationException("TODO");
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Spread-функция. Уже готова — используй её. */
    static int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public V put(K key, V value) {
        throw new UnsupportedOperationException("TODO");
    }

    public V get(Object key) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("TODO");
    }

    public V getOrDefault(Object key, V defaultValue) {
        throw new UnsupportedOperationException("TODO");
    }

    public V remove(Object key) {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Текущее число бакетов. */
    public int capacity() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Длина самой длинной цепочки — для тестов качества распределения. */
    public int maxChainLength() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Все ключи (порядок любой). */
    public java.util.List<K> keys() {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы
}
