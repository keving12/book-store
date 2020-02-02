package com.kgracie.mytutor.reporting.impl;

import com.kgracie.mytutor.reporting.api.BookReportingService;
import com.kgracie.mytutor.sales.api.TransactionService;

public class BookReportingServiceImpl implements BookReportingService {

    private final TransactionService transactionService;

    public BookReportingServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
