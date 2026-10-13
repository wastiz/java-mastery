package com.javamastery.spring.rest.service;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(long id, int stock, int delta) {
        super("Book " + id + " has " + stock + " in stock, cannot apply " + delta);
    }
}
