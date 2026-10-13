package com.javamastery.spring.data.service;

import com.javamastery.spring.data.domain.Author;
import com.javamastery.spring.data.domain.Book;
import com.javamastery.spring.data.repo.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * S04 · Классическая проблема N+1.
 */
@Service
public class CatalogService {

    private final AuthorRepository authors;

    public CatalogService(AuthorRepository authors) {
        this.authors = authors;
    }

    /**
     * Для каждого автора (по имени) строка "Имя: Книга1, Книга2" (книги по названию; без книг — "Имя: -").
     * Должно выполняться НЕ БОЛЕЕ 2 SQL-запросов при любом числе авторов.
     * Наивная версия (findAll + getBooks() в цикле) делает 1 + N запросов — тест это поймает.
     */
    @Transactional(readOnly = true)
    public List<String> authorSummaries() {
        throw new UnsupportedOperationException("TODO");
    }

    /** То же для одного автора; нет такого → LibraryException.NotFound("Author", id). */
    @Transactional(readOnly = true)
    public String authorSummary(long id) {
        throw new UnsupportedOperationException("TODO");
    }
}
