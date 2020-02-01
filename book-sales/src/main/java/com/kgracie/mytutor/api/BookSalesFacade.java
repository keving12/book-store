package com.kgracie.mytutor.api;

import com.kgracie.mytutor.domain.BookSalesResponse;

import java.util.Optional;

public interface BookSalesFacade {

    Optional<BookSalesResponse> sellBook(String bookTitle, int quantity);
}
