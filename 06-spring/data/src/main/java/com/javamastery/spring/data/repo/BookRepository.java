package com.javamastery.spring.data.repo;

import com.javamastery.spring.data.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    /** Derived query: книги, в названии которых есть подстрока (без учёта регистра), по названию. Просто убери default-тело! */
    default List<Book> findByTitleContainingIgnoreCaseOrderByTitle(String part) { throw new UnsupportedOperationException("TODO: derived query"); }

    /** Derived query по вложенному свойству + пагинация. */
    default Page<Book> findByAuthorName(String authorName, Pageable pageable) { throw new UnsupportedOperationException("TODO: derived query"); }

    default Optional<Book> findByIsbn(String isbn) { throw new UnsupportedOperationException("TODO: derived query"); }

    /** JPQL: книги с тегом (по имени тега), отсортированные по названию. */
    default List<Book> findByTagName(String tag) { throw new UnsupportedOperationException("TODO: @Query с join по tags"); }

    /** NATIVE SQL: книги, которых нет в наличии (copies = 0), по названию. */
    default List<Book> findOutOfStock() { throw new UnsupportedOperationException("TODO: native query"); }

    /** Массовое обновление одним UPDATE: добавить n копий всем книгам автора. Вернуть число обновлённых строк. */
    default int restockAuthor(Long authorId, int n) { throw new UnsupportedOperationException("TODO: @Modifying @Query"); }
}
