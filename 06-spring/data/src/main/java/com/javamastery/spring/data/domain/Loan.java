package com.javamastery.spring.data.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// TODO: маппинг сущности
public class Loan {

    // TODO: первичный ключ
    private Long id;

    // TODO: связь с книгой
    private Book book;

    // TODO: связь с читателем
    private Member member;

    private LocalDateTime borrowedAt;

    private LocalDateTime returnedAt;

    protected Loan() {
    }

    public Loan(Book book, Member member, LocalDateTime borrowedAt) {
        this.book = book;
        this.member = member;
        this.borrowedAt = borrowedAt;
    }

    public Long getId() { return id; }

    public Book getBook() { return book; }

    public Member getMember() { return member; }

    public LocalDateTime getBorrowedAt() { return borrowedAt; }

    public LocalDateTime getReturnedAt() { return returnedAt; }

    public void setReturnedAt(LocalDateTime returnedAt) { this.returnedAt = returnedAt; }
}
