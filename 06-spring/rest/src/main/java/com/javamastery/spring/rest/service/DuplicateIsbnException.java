package com.javamastery.spring.rest.service;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String isbn) {
        super("ISBN " + isbn + " already exists");
    }
}
