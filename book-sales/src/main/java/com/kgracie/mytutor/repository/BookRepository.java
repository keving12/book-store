package com.kgracie.mytutor.repository;

import com.kgracie.mytutor.domain.Book;

public interface BookRepository {

    Book fetchBook(String bookTitle);

    void incrementBookStock(String bookTitle, int quantity);

    void decrementBookStock(String bookTitle, int quantity);
}
