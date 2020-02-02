package com.kgracie.mytutor.sales.api;

import com.kgracie.mytutor.sales.domain.Transaction;
import com.kgracie.mytutor.sales.domain.TransactionType;

import java.util.List;

public interface TransactionService {

    List<Transaction> retrieveTransactions();

    void recordTransaction(TransactionType transactionType, String bookTitle, int quantity, double unitPrice);
}
