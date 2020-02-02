package com.kgracie.mytutor.sales.domain;

import java.util.Objects;

public class Transaction {

    private final TransactionType transactionType;
    private final String title;
    private final int quantity;
    private final double value;

    public Transaction(TransactionType transactionType, String title, int quantity, double value) {
        this.transactionType = transactionType;
        this.title = title;
        this.quantity = quantity;
        this.value = value;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return quantity == that.quantity &&
                Double.compare(that.value, value) == 0 &&
                transactionType == that.transactionType &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionType, title, quantity, value);
    }

    public static class Builder {
        private TransactionType transactionType;
        private String title;
        private int quantity;
        private double value;

        public Builder transactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder value(double value) {
            this.value = value;
            return this;
        }

        public Transaction build() {
            return new Transaction(transactionType, title, quantity, value);
        }
    }
}
