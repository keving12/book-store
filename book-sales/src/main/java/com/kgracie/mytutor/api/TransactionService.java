package com.kgracie.mytutor.api;

import com.kgracie.mytutor.domain.TransactionType;

public interface TransactionService {

    void recordTransaction(TransactionType transactionType, String bookTitle, int quantity);
}
