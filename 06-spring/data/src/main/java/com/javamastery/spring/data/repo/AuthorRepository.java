package com.javamastery.spring.data.repo;

import com.javamastery.spring.data.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    /** Все авторы, отсортированные по имени, ВМЕСТЕ с книгами — одним SQL-запросом (join fetch). */
    default List<Author> findAllWithBooks() { throw new UnsupportedOperationException("TODO: @Query с join fetch"); }

    /** Статистика: имя автора и число его книг (авторы без книг — с 0), по убыванию числа книг, затем по имени. */
    default List<AuthorStats> statistics() { throw new UnsupportedOperationException("TODO: @Query + проекция AuthorStats"); }
}
