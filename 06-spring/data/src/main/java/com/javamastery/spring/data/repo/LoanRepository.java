package com.javamastery.spring.data.repo;

import com.javamastery.spring.data.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    /** Сколько у читателя невозвращённых книг. */
    default long countByMemberIdAndReturnedAtIsNull(Long memberId) { throw new UnsupportedOperationException("TODO: derived query"); }

    /** Сколько экземпляров книги сейчас на руках. */
    default long countByBookIdAndReturnedAtIsNull(Long bookId) { throw new UnsupportedOperationException("TODO: derived query"); }
}
