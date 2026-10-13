package com.javamastery.basics.b12_exceptions;

/**
 * B12 · Собственное checked-исключение. Должно хранить sku, requested и available,
 * а сообщение иметь вид: "Not enough SKU-1: requested 5, available 2".
 */
public class InsufficientStockException extends Exception {
    // TODO: поля

    public InsufficientStockException(String sku, int requested, int available) {
        throw new UnsupportedOperationException("TODO");
    }

    public String getSku() {
        throw new UnsupportedOperationException("TODO");
    }

    public int getRequested() {
        throw new UnsupportedOperationException("TODO");
    }

    public int getAvailable() {
        throw new UnsupportedOperationException("TODO");
    }
}
