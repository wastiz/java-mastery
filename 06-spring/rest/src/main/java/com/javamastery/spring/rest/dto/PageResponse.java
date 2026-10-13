package com.javamastery.spring.rest.dto;

import java.util.List;

/** Страница результатов. Готово. */
public record PageResponse<T>(List<T> content, int page, int size, long totalElements, int totalPages) {}
