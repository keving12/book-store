package com.kgracie.mytutor.sales.repository;

import com.kgracie.mytutor.sales.domain.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> fetchTransactions();

    void recordBookTransaction(Transaction transaction);
}
