package com.javamastery.spring.rest.domain;

import java.math.BigDecimal;

/** Доменная модель. Готово. */
public record Book(Long id, String isbn, String title, String author, int year, BigDecimal price, int stock) {

    public Book withId(Long newId) {
        return new Book(newId, isbn, title, author, year, price, stock);
    }

    public Book withStock(int newStock) {
        return new Book(id, isbn, title, author, year, price, newStock);
    }
}
