package com.kgracie.mytutor.repository;

public interface BookRepository {

    Integer checkBookStock(String bookTitle);

    Integer incremementBookStock(String bookTitle, int quantity);

    Integer decrementBookStock(String bookTitle, int quantity);


}
