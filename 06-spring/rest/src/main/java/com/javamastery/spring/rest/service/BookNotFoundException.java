package com.javamastery.spring.rest.service;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(long id) {
        super("Book " + id + " not found");
    }
}
