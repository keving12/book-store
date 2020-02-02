package com.kgracie.mytutor.api;

import com.kgracie.mytutor.domain.Transaction;
import com.kgracie.mytutor.domain.TransactionType;

import java.util.List;

public interface TransactionService {

    List<Transaction> retrieveTransactions();

    void recordTransaction(TransactionType transactionType, String bookTitle, int quantity, double unitPrice);
}
