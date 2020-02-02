package com.kgracie.mytutor.sales.api;

import com.kgracie.mytutor.sales.domain.BookSalesResponse;

public interface BookSalesService {

    BookSalesResponse processBookSale(String bookTitle, int quantity);
}
