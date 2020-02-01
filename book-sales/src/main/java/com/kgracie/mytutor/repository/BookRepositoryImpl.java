package com.kgracie.mytutor.repository;

import java.util.HashMap;
import java.util.Map;

public class BookRepositoryImpl implements BookRepository {

    private Map<String, Integer> bookStore = new HashMap<>();
    private static BookRepositoryImpl bookRepository;

    private BookRepositoryImpl() {
                bookStore.put("Book A", 10);
                bookStore.put("Book B", 10);
                bookStore.put("Book C", 10);
                bookStore.put("Book D", 10);
                bookStore.put("Book E", 10);
    }

    public static BookRepository getInstance() {
        if(bookRepository == null) {
            bookRepository = new BookRepositoryImpl();
        }
        return bookRepository;
    }


    @Override
    public Integer checkBookStock(String bookTitle) {
        return null;
    }

    @Override
    public Integer incremementBookStock(String bookTitle, int quantity) {
        return null;
    }

    @Override
    public Integer decrementBookStock(String bookTitle, int quantity) {
        return null;
    }
}