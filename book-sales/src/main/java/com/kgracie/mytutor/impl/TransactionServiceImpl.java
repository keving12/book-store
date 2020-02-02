package com.kgracie.mytutor.impl;

import com.kgracie.mytutor.api.TransactionService;
import com.kgracie.mytutor.domain.Transaction;
import com.kgracie.mytutor.domain.TransactionType;
import com.kgracie.mytutor.repository.TransactionRepository;

import java.util.List;

import static com.kgracie.mytutor.domain.TransactionType.*;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> retrieveTransactions() {
        return transactionRepository.fetchTransactions();
    }

    @Override
    public void recordTransaction(TransactionType transactionType, String title, int quantity, double unitPrice) {
        double multiplier = transactionType == PURCHASE ? 0.7 : 1;
        double transactionAmount = (unitPrice * multiplier) * quantity;

        Transaction transaction = new Transaction(transactionType, title, quantity, transactionAmount);
        transactionRepository.recordBookTransaction(transaction);
    }
}
