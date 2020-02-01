package com.kgracie.mytutor.repository;

public interface BookRepository {

    Integer checkBookStock(String bookTitle);

    void incremementBookStock(String bookTitle, int quantity);

    void decrementBookStock(String bookTitle, int quantity);


}
