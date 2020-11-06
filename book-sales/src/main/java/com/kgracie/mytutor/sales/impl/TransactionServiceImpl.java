package com.kgracie.mytutor.sales.impl;

import com.kgracie.mytutor.sales.api.TransactionService;
import com.kgracie.mytutor.sales.domain.Transaction;
import com.kgracie.mytutor.sales.domain.TransactionBuilder;
import com.kgracie.mytutor.sales.domain.TransactionType;
import com.kgracie.mytutor.sales.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kgracie.mytutor.sales.domain.TransactionType.*;

@Service
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
        double signedTransactionAmount = transactionType == PURCHASE ? -transactionAmount : transactionAmount;

        var transaction = TransactionBuilder.newInstance()
                .transactionType(transactionType)
                .title(title)
                .quantity(quantity)
                .value(signedTransactionAmount)
                .build();

        transactionRepository.recordBookTransaction(transaction);
    }
}
