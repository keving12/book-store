package com.kgracie.mytutor.impl;

import com.kgracie.mytutor.api.BookSalesService;
import com.kgracie.mytutor.domain.BookSalesResponse;
import com.kgracie.mytutor.repository.BookRepository;

public class BookSalesServiceImpl implements BookSalesService {

    private final BookRepository bookRepository;

    public BookSalesServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookSalesResponse processBookSale(String bookTitle, int quantity) {
        return null;
    }
}
