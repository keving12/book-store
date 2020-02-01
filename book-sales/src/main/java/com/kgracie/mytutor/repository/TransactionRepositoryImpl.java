package com.kgracie.mytutor.repository;

import com.kgracie.mytutor.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();
    private static TransactionRepository transactionRepository;

    private TransactionRepositoryImpl() {

    }

    public static TransactionRepository getInstance() {
        if(transactionRepository == null) {
            transactionRepository = new TransactionRepositoryImpl();
        }
        return transactionRepository;
    }

    @Override
    public List<Transaction> fetchTransactions() {
        return transactions;
    }

    @Override
    public void recordBookTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
