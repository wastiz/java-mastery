package com.javamastery.spring.data.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

// TODO: маппинг сущности
public class Book {

    // TODO: первичный ключ
    private Long id;

    private String title;

    private String isbn;

    // TODO: имя колонки отличается от имени поля
    private int year;

    private int copies;

    // TODO: оптимистическая блокировка
    private long version;

    // TODO: связь с автором (LAZY!)
    private Author author;

    // TODO: связь многие-ко-многим через book_tag
    private Set<Tag> tags = new HashSet<>();

    protected Book() {
    }

    public Book(String title, String isbn, int year, int copies) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.copies = copies;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }

    public int getYear() { return year; }

    public int getCopies() { return copies; }

    public void setCopies(int copies) { this.copies = copies; }

    public long getVersion() { return version; }

    public Author getAuthor() { return author; }

    public void setAuthor(Author author) { this.author = author; }

    public Set<Tag> getTags() { return tags; }

    public Book tag(Tag t) {
        tags.add(t);
        return this;
    }
}
