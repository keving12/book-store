package com.kgracie.mytutor.sales.domain;

public class TransactionBuilder {
    private TransactionType transactionType;
    private String title;
    private int quantity;
    private double value;

    private TransactionBuilder() {}

    public static TransactionBuilder newInstance() {
        return new TransactionBuilder();
    }

    public TransactionBuilder transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public TransactionBuilder title(String title) {
        this.title = title;
        return this;
    }

    public TransactionBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public TransactionBuilder value(double value) {
        this.value = value;
        return this;
    }

    public Transaction build() {
        return new Transaction(transactionType, title, quantity, value);
    }
}
