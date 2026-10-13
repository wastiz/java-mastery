package com.javamastery.spring.data;

import com.javamastery.spring.data.domain.*;
import com.javamastery.spring.data.repo.*;
import com.javamastery.spring.data.service.CatalogService;
import com.javamastery.spring.data.service.LibraryException;
import com.javamastery.spring.data.service.LoanService;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.LazyInitializationException;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class LibraryTest {

    @Autowired AuthorRepository authors;
    @Autowired BookRepository books;
    @Autowired TagRepository tags;
    @Autowired MemberRepository members;
    @Autowired LoanRepository loans;
    @Autowired AuditLogRepository auditLogs;
    @Autowired CatalogService catalog;
    @Autowired LoanService loanService;
    @Autowired JdbcTemplate jdbc;
    @Autowired EntityManagerFactory emf;
    @Autowired TransactionTemplate tx;

    Long bloch, goetz, martin, noBooks;

    @BeforeEach
    void seed() {
        for (String t : List.of("loan", "book_tag", "book", "tag", "author", "member", "audit_log")) jdbc.execute("delete from " + t);

        Tag classic = tags.save(new Tag("classic"));
        Tag concurrency = tags.save(new Tag("concurrency"));

        bloch = authors.save(new Author("Joshua Bloch", "USA")
                .addBook(new Book("Effective Java", "9780134685991", 2018, 2).tag(classic))
                .addBook(new Book("Java Puzzlers", "9780321336781", 2005, 0))).getId();
        goetz = authors.save(new Author("Brian Goetz", "USA")
                .addBook(new Book("Java Concurrency in Practice", "9780321349606", 2006, 1).tag(classic).tag(concurrency))).getId();
        martin = authors.save(new Author("Robert Martin", "USA")
                .addBook(new Book("Clean Code", "9780132350884", 2008, 0).tag(classic))
                .addBook(new Book("Clean Architecture", "9780134494166", 2017, 1))
                .addBook(new Book("The Clean Coder", "9780137081073", 2011, 5))).getId();
        noBooks = authors.save(new Author("Anonymous", null)).getId();
    }

    long bookId(String isbn) {
        return books.findByIsbn(isbn).orElseThrow().getId();
    }

    // ===== маппинг =====

    @Test
    void cascadeSavesBooks() {
        assertThat(books.count()).isEqualTo(6);
        tx.executeWithoutResult(s -> {
            Book b = books.findByIsbn("9780321349606").orElseThrow();
            assertThat(b.getAuthor().getName()).isEqualTo("Brian Goetz");
            assertThat(b.getTags()).extracting(Tag::getName).containsExactlyInAnyOrder("classic", "concurrency");
            assertThat(b.getYear()).isEqualTo(2006);
        });
    }

    @Test
    void orphanRemoval() {
        tx.executeWithoutResult(s -> {
            Author a = authors.findById(martin).orElseThrow();
            Book cc = a.getBooks().stream().filter(b -> b.getTitle().equals("The Clean Coder")).findFirst().orElseThrow();
            a.removeBook(cc);
        });
        assertThat(books.findByIsbn("9780137081073")).isEmpty();
    }

    @Test
    void booksAreLazy() {
        Author a = authors.findById(bloch).orElseThrow();
        // транзакция репозитория уже закрыта, а коллекция не загружена
        assertThatThrownBy(() -> a.getBooks().size()).isInstanceOf(LazyInitializationException.class);
    }

    // ===== запросы =====

    @Test
    void derivedQueries() {
        assertThat(books.findByTitleContainingIgnoreCaseOrderByTitle("JAVA")).extracting(Book::getTitle)
                .containsExactly("Effective Java", "Java Concurrency in Practice", "Java Puzzlers");
        Page<Book> page = books.findByAuthorName("Robert Martin", PageRequest.of(0, 2, Sort.by("title")));
        assertThat(page.getTotalElements()).isEqualTo(3);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.getContent()).extracting(Book::getTitle).containsExactly("Clean Architecture", "Clean Code");
    }

    @Test
    void jpqlAndNative() {
        assertThat(books.findByTagName("classic")).extracting(Book::getTitle)
                .containsExactly("Clean Code", "Effective Java", "Java Concurrency in Practice");
        assertThat(books.findOutOfStock()).extracting(Book::getTitle).containsExactly("Clean Code", "Java Puzzlers");
    }

    @Test
    void projection() {
        List<AuthorStats> stats = authors.statistics();
        assertThat(stats).extracting(AuthorStats::getName).containsExactly("Robert Martin", "Joshua Bloch", "Brian Goetz", "Anonymous");
        assertThat(stats).extracting(AuthorStats::getBookCount).containsExactly(3L, 2L, 1L, 0L);
    }

    @Test
    void bulkUpdate() {
        assertThat(books.restockAuthor(martin, 10)).isEqualTo(3);
        assertThat(books.findByIsbn("9780132350884").orElseThrow().getCopies()).isEqualTo(10);
        assertThat(books.findByIsbn("9780137081073").orElseThrow().getCopies()).isEqualTo(15);
        assertThat(books.findByIsbn("9780134685991").orElseThrow().getCopies()).isEqualTo(2);
    }

    // ===== N+1 =====

    @Test
    void noNPlusOne() {
        Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();
        stats.clear();
        List<String> summaries = catalog.authorSummaries();
        assertThat(summaries).containsExactly(
                "Anonymous: -",
                "Brian Goetz: Java Concurrency in Practice",
                "Joshua Bloch: Effective Java, Java Puzzlers",
                "Robert Martin: Clean Architecture, Clean Code, The Clean Coder");
        assertThat(stats.getPrepareStatementCount())
                .as("SQL-запросов выполнено: %d — это N+1!", stats.getPrepareStatementCount())
                .isLessThanOrEqualTo(2);
    }

    @Test
    void singleSummary() {
        assertThat(catalog.authorSummary(goetz)).isEqualTo("Brian Goetz: Java Concurrency in Practice");
        assertThatThrownBy(() -> catalog.authorSummary(-1)).isInstanceOf(LibraryException.NotFound.class);
    }

    // ===== транзакции =====

    @Test
    void borrowAndReturn() {
        long ann = members.save(new Member("Ann", "ann@x.ee")).getId();
        long ej = bookId("9780134685991");
        long loanId = loanService.borrow(ann, ej);
        assertThat(loans.countByMemberIdAndReturnedAtIsNull(ann)).isEqualTo(1);
        loanService.returnBook(loanId);
        assertThat(loans.findById(loanId).orElseThrow().getReturnedAt()).isNotNull();
        assertThat(loans.countByMemberIdAndReturnedAtIsNull(ann)).isZero();
        assertThatThrownBy(() -> loanService.returnBook(loanId)).isInstanceOf(IllegalStateException.class).hasMessage("Already returned");
        assertThat(auditLogs.findAllByOrderByIdAsc()).extracting(AuditLog::getAction).containsExactly("BORROW");
    }

    @Test
    void rules() {
        long ann = members.save(new Member("Ann", "ann@x.ee")).getId();
        long bob = members.save(new Member("Bob", "bob@x.ee")).getId();
        long jcip = bookId("9780321349606");   // 1 экземпляр
        loanService.borrow(ann, jcip);
        assertThatThrownBy(() -> loanService.borrow(bob, jcip)).isInstanceOf(LibraryException.NoCopiesLeft.class);
        assertThatThrownBy(() -> loanService.borrow(bob, bookId("9780132350884"))).isInstanceOf(LibraryException.NoCopiesLeft.class);
        assertThatThrownBy(() -> loanService.borrow(999_999, jcip)).isInstanceOf(LibraryException.NotFound.class);

        long coder = bookId("9780137081073");   // 5 экземпляров
        loanService.borrow(ann, coder);
        loanService.borrow(ann, coder);
        assertThatThrownBy(() -> loanService.borrow(ann, coder)).isInstanceOf(LibraryException.LoanLimitReached.class);
    }

    @Test
    void failedAttemptsAreAuditedEvenAfterRollback() {
        long ann = members.save(new Member("Ann", "ann@x.ee")).getId();
        long ej = bookId("9780134685991");          // 2 экземпляра
        long puzzlers = bookId("9780321336781");    // 0 экземпляров
        assertThatThrownBy(() -> loanService.borrowAll(ann, List.of(ej, puzzlers)))
                .isInstanceOf(LibraryException.NoCopiesLeft.class);
        assertThat(loans.count()).as("borrowAll — всё или ничего").isZero();
        List<AuditLog> log = auditLogs.findAllByOrderByIdAsc();
        assertThat(log).extracting(AuditLog::getAction).as("BORROW откатился вместе с транзакцией? Или нет? Подумай, почему он остался.")
                .containsExactly("BORROW", "BORROW_FAILED");
        assertThat(log.get(1).getDetails()).isEqualTo("No copies of book " + puzzlers + " left");
    }

    @Test
    void optimisticLocking() {
        long id = bookId("9780134685991");
        Book first = books.findById(id).orElseThrow();
        Book second = books.findById(id).orElseThrow();
        first.setTitle("Effective Java, 3rd ed.");
        books.save(first);
        second.setTitle("Lost update!");
        assertThatThrownBy(() -> books.save(second)).isInstanceOf(ObjectOptimisticLockingFailureException.class);
        assertThat(books.findById(id).orElseThrow().getTitle()).isEqualTo("Effective Java, 3rd ed.");
        assertThat(books.findById(id).orElseThrow().getVersion()).isEqualTo(1);
    }
}
