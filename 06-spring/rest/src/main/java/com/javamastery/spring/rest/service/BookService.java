package com.javamastery.spring.rest.service;

import com.javamastery.spring.rest.domain.Book;
import com.javamastery.spring.rest.domain.BookRepository;
import com.javamastery.spring.rest.dto.BookResponse;
import com.javamastery.spring.rest.dto.CreateBookRequest;
import com.javamastery.spring.rest.dto.PageResponse;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * S03 · Бизнес-логика. Контроллер должен быть «тонким» и только вызывать сервис.
 * Правила — в README модуля. Бросай исключения из этого пакета, а их перевод в HTTP — дело GlobalExceptionHandler.
 */
@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public PageResponse<BookResponse> list(String author, int page, int size, String sort) {
        throw new UnsupportedOperationException("TODO");
    }

    public BookResponse get(long id) {
        throw new UnsupportedOperationException("TODO");
    }

    public BookResponse create(CreateBookRequest r) {
        throw new UnsupportedOperationException("TODO");
    }

    public BookResponse update(long id, CreateBookRequest r) {
        throw new UnsupportedOperationException("TODO");
    }

    public BookResponse changeStock(long id, int delta) {
        throw new UnsupportedOperationException("TODO");
    }

    public void delete(long id) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: вспомогательные методы
}
