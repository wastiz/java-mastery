package com.javamastery.spring.rest.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * Тело POST/PUT. Добавь аннотации Bean Validation с сообщениями из README
 * (подсказки: @NotBlank, @Pattern, @Min, @Max, @NotNull, @DecimalMin, @PositiveOrZero / @Min(0)).
 */
public record CreateBookRequest(
        // TODO: валидация isbn
        String isbn,
        // TODO: валидация title
        String title,
        // TODO: валидация author
        String author,
        // TODO: валидация year
        int year,
        // TODO: валидация price
        BigDecimal price,
        // TODO: валидация stock
        int stock) {
}
