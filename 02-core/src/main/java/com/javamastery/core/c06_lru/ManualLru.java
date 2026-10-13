package com.javamastery.core.c06_lru;

import java.util.HashMap;
import java.util.List;

/** HashMap&lt;K, Node&gt; + свой двусвязный список. LinkedHashMap использовать нельзя. */
public class ManualLru<K, V> implements LruCache<K, V> {
    // TODO: узел списка, map, head/tail (подсказка: «сторожевые» фиктивные узлы упрощают код), capacity

    public ManualLru(int capacity) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public V get(K key) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void put(K key, V value) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public List<K> keysOldestFirst() {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы
}
