package com.kgracie.mytutor.repository;

public interface BookRepository {

    Integer checkBookStock(String bookTitle);

    Integer incrementBookStock(String bookTitle, int quantity);

    Integer decrementBookStock(String bookTitle, int quantity);
}
