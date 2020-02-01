package com.kgracie.mytutor.impl;

import com.kgracie.mytutor.api.BookOrderingService;
import com.kgracie.mytutor.api.BookSalesFacade;
import com.kgracie.mytutor.api.BookSalesService;
import com.kgracie.mytutor.domain.BookSalesResponse;

import java.util.Optional;

public class BookSalesFacadeImpl implements BookSalesFacade {

    private final BookSalesService bookSalesService;
    private final BookOrderingService bookOrderingService;

    public BookSalesFacadeImpl(BookSalesService bookSalesService, BookOrderingService bookOrderingService) {
        this.bookSalesService = bookSalesService;
        this.bookOrderingService = bookOrderingService;
    }

    @Override
    public Optional<BookSalesResponse> sellBook(String bookTitle, int quantity) {
        return Optional.empty();
    }
}
