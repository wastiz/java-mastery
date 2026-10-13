package com.javamastery.core.c06_lru;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class LinkedHashMapLru<K, V> implements LruCache<K, V> {
    // TODO: внутренний LinkedHashMap с accessOrder=true и removeEldestEntry

    public LinkedHashMapLru(int capacity) {
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
}
