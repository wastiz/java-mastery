package com.javamastery.spring.data.repo;

/** Интерфейсная проекция (Spring Data сам создаст реализацию). Готово. */
public interface AuthorStats {
    String getName();

    long getBookCount();
}
