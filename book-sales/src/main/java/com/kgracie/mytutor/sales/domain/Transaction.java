package com.kgracie.mytutor.sales.domain;

public record Transaction(TransactionType transactionType, String title, int quantity, double value) { }