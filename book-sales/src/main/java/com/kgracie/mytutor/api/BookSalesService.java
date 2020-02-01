package com.kgracie.mytutor.api;

import com.kgracie.mytutor.domain.BookSalesResponse;

public interface BookSalesService {

    BookSalesResponse processBookSale(String bookTitle, int quantity);
}
