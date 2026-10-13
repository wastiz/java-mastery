package com.javamastery.spring.data.service;

/** Базовое бизнес-исключение. Готово. Подклассы ниже. */
public class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }

    public static class NotFound extends LibraryException {
        public NotFound(String what, long id) {
            super(what + " " + id + " not found");
        }
    }

    public static class LoanLimitReached extends LibraryException {
        public LoanLimitReached(long memberId) {
            super("Member " + memberId + " already has 3 books");
        }
    }

    public static class NoCopiesLeft extends LibraryException {
        public NoCopiesLeft(long bookId) {
            super("No copies of book " + bookId + " left");
        }
    }
}
