package com.kgracie.mytutor.repository;

import com.kgracie.mytutor.domain.Book;

import java.util.HashMap;
import java.util.Map;

public class BookRepositoryImpl implements BookRepository {

    private Map<String, Book> bookStore = new HashMap<>();
    private static BookRepositoryImpl bookRepository;

    private BookRepositoryImpl() {
                bookStore.put("Book A", new Book("Book A", 25.00, 10));
                bookStore.put("Book B", new Book("Book B", 20.00, 10));
                bookStore.put("Book C", new Book("Book C", 23.00, 10));
                bookStore.put("Book D", new Book("Book D", 30.00, 10));
                bookStore.put("Book E", new Book("Book E", 27.00, 10));
    }

    public static BookRepository getInstance() {
        if(bookRepository == null) {
            bookRepository = new BookRepositoryImpl();
        }
        return bookRepository;
    }

    @Override
    public Book fetchBook(String bookTitle) {
        return bookStore.get(bookTitle);
    }

    @Override
    public void incrementBookStock(String bookTitle, int quantity) {
        Book book = bookStore.get(bookTitle);
        bookStore.put(bookTitle, bookWithNewQuantity(book, book.getStock() + quantity));
    }

    @Override
    public void decrementBookStock(String bookTitle, int quantity) {
        Book book = bookStore.get(bookTitle);
        bookStore.put(bookTitle, bookWithNewQuantity(book, book.getStock() - quantity));
    }

    private Book bookWithNewQuantity(Book book, int newQuantity) {
        return new Book(book.getTitle(), book.getPrice(), newQuantity);
    }
}
