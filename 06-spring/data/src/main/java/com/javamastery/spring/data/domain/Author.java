package com.javamastery.spring.data.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// TODO: маппинг сущности
public class Author {

    // TODO: первичный ключ
    private Long id;

    private String name;

    private String country;

    // TODO: связь с книгами (двунаправленная, каскад, orphanRemoval)
    private List<Book> books = new ArrayList<>();

    protected Author() {
    }

    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }

    /** Помощник для двунаправленной связи: обе стороны должны быть согласованы. */
    public Author addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
        return this;
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }

    public List<Book> getBooks() { return books; }
}
