package com.kgracie.mytutor.sales.impl;

import com.kgracie.mytutor.sales.api.BookSalesService;
import com.kgracie.mytutor.sales.api.TransactionService;
import com.kgracie.mytutor.sales.domain.Book;
import com.kgracie.mytutor.sales.domain.BookSalesResponse;
import com.kgracie.mytutor.sales.domain.TransactionType;
import com.kgracie.mytutor.sales.repository.BookRepository;

public class BookSalesServiceImpl implements BookSalesService {

    private final BookRepository bookRepository;
    private final TransactionService transactionService;
    private static final int ORDER_QUANTITY = 10;

    public BookSalesServiceImpl(BookRepository bookRepository, TransactionService transactionService) {
        this.bookRepository = bookRepository;
        this.transactionService = transactionService;
    }

    @Override
    public BookSalesResponse processBookSale(String bookTitle, int quantity) {
        Book book = bookRepository.fetchBook(bookTitle);

        if(book == null) {
            return new BookSalesResponse("Sorry, we do not stock the book requested");
        }

        if(book.getStock() < quantity) {
            return new BookSalesResponse("Sorry, we are out of stock.");
        }

        recordTransactionAndReturnSuccessMessage(bookTitle, quantity, book.getPrice());

        if(book.getStock() - quantity < 3) {
            restockBookAndRecordTransaction(bookTitle, book.getPrice());
        }

        return new BookSalesResponse("Thank you for your purchase!");
    }

    private void recordTransactionAndReturnSuccessMessage(String bookTitle, int quantity, double price) {
        bookRepository.decrementBookStock(bookTitle, quantity);
        transactionService.recordTransaction(TransactionType.SALE, bookTitle, quantity, price);
    }

    private void restockBookAndRecordTransaction(String bookTitle, double bookPrice) {
        bookRepository.incrementBookStock(bookTitle, ORDER_QUANTITY);
        transactionService.recordTransaction(TransactionType.PURCHASE, bookTitle, ORDER_QUANTITY, bookPrice);
    }
}
