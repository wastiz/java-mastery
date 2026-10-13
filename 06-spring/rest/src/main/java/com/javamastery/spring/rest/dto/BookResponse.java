package com.javamastery.spring.rest.dto;

import com.javamastery.spring.rest.domain.Book;

import java.math.BigDecimal;

/** DTO ответа. Готово. Почему мы не отдаём доменный объект напрямую? (см. теорию S03) */
public record BookResponse(long id, String isbn, String title, String author, int year, BigDecimal price, int stock) {
    public static BookResponse from(Book b) {
        return new BookResponse(b.id(), b.isbn(), b.title(), b.author(), b.year(), b.price(), b.stock());
    }
}
