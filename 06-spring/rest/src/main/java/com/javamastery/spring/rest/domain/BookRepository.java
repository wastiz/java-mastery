package com.javamastery.spring.rest.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/** Потокобезопасное in-memory хранилище (в теме S04 заменим на Spring Data JPA). Готово. */
@Repository
public class BookRepository {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong ids = new AtomicLong();

    /** Сохраняет книгу; если id == null — присваивает новый. Возвращает сохранённую книгу. */
    public Book save(Book book) {
        Book b = book.id() == null ? book.withId(ids.incrementAndGet()) : book;
        books.put(b.id(), b);
        return b;
    }

    public Optional<Book> findById(long id) {
        return Optional.ofNullable(books.get(id));
    }

    public Optional<Book> findByIsbn(String isbn) {
        return books.values().stream().filter(b -> b.isbn().equals(isbn)).findFirst();
    }

    public List<Book> findAll() {
        return List.copyOf(books.values());
    }

    /** true, если книга была и её удалили. */
    public boolean deleteById(long id) {
        return books.remove(id) != null;
    }

    public void deleteAll() {
        books.clear();
    }
}
