package com.kgracie.mytutor.sales.repository;

import com.kgracie.mytutor.sales.domain.Book;

public interface BookRepository {

    Book fetchBook(String bookTitle);

    void incrementBookStock(String bookTitle, int quantity);

    void decrementBookStock(String bookTitle, int quantity);
}
