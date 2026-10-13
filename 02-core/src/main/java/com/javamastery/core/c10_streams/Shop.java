package com.javamastery.core.c10_streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/** Модель интернет-магазина для задач по Stream API. Готова. */
public final class Shop {
    private Shop() {}

    public enum Category { BOOKS, ELECTRONICS, GAMES, FOOD, TOYS }

    public record Customer(long id, String name, String city, int tier) {}

    public record Product(long id, String name, Category category, BigDecimal price) {}

    public record OrderLine(Product product, int quantity) {
        public BigDecimal total() {
            return product.price().multiply(BigDecimal.valueOf(quantity));
        }
    }

    public enum Status { NEW, PAID, SHIPPED, CANCELLED }

    public record Order(long id, Customer customer, LocalDate date, Status status, List<OrderLine> lines) {
        public BigDecimal total() {
            return lines.stream().map(OrderLine::total).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }
}
