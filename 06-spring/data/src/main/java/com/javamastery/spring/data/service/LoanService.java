package com.javamastery.spring.data.service;

import com.javamastery.spring.data.domain.Book;
import com.javamastery.spring.data.domain.Loan;
import com.javamastery.spring.data.domain.Member;
import com.javamastery.spring.data.repo.BookRepository;
import com.javamastery.spring.data.repo.LoanRepository;
import com.javamastery.spring.data.repo.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

/**
 * S05 · Транзакции.
 *
 * <ul>
 *   <li>{@code borrow(memberId, bookId)}: читатель и книга должны существовать (иначе NotFound("Member"/"Book", id));
 *       у читателя не больше 3 невозвращённых книг (иначе LoanLimitReached); на руках меньше экземпляров, чем copies
 *       (иначе NoCopiesLeft). Успех → сохранить Loan, записать аудит ("BORROW", "member=M book=B") и вернуть id займа.
 *       Неудача → записать аудит ("BORROW_FAILED", сообщение исключения) и пробросить исключение.</li>
 *   <li>{@code borrowAll(memberId, bookIds)}: выдать несколько книг В ОДНОЙ транзакции — всё или ничего.
 *       Если хоть одна не выдана, ни одного займа не должно остаться в БД (а аудит неудач — остаться!).</li>
 *   <li>{@code returnBook(loanId)}: проставить returnedAt = now(clock). Явный save не нужен — почему? (dirty checking).
 *       Повторный возврат — IllegalStateException("Already returned").</li>
 * </ul>
 */
@Service
public class LoanService {

    private final MemberRepository members;
    private final BookRepository books;
    private final LoanRepository loans;
    private final AuditService audit;
    private final Clock clock;

    public LoanService(MemberRepository members, BookRepository books, LoanRepository loans, AuditService audit, Clock clock) {
        this.members = members;
        this.books = books;
        this.loans = loans;
        this.audit = audit;
        this.clock = clock;
    }

    // TODO: транзакционная аннотация
    public long borrow(long memberId, long bookId) {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: транзакционная аннотация
    public List<Long> borrowAll(long memberId, List<Long> bookIds) {
        throw new UnsupportedOperationException("TODO: подсказка: вызов this.borrow(...) изнутри НЕ проходит через прокси — но здесь это и не нужно, мы уже в транзакции");
    }

    // TODO: транзакционная аннотация
    public void returnBook(long loanId) {
        throw new UnsupportedOperationException("TODO");
    }
}
