package com.kgracie.mytutor.repository;

import com.kgracie.mytutor.domain.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> fetchTransactions();

    void recordBookTransaction(Transaction transaction);
}
