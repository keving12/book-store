package com.kgracie.mytutor.reporting.domain;

public class SummedTransactionBuilder {
    private String title;
    private int numberOfCopiesSold;
    private double totalProfit;

    private SummedTransactionBuilder() {}

    public static SummedTransactionBuilder newInstance() {
        return new SummedTransactionBuilder();
    }

    public SummedTransactionBuilder title(String title) {
        this.title = title;
        return this;
    }

    public SummedTransactionBuilder numberOfCopiesSold(int numberOfCopiesSold) {
        this.numberOfCopiesSold = numberOfCopiesSold;
        return this;
    }

    public SummedTransactionBuilder totalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
        return this;
    }

    public SummedTransaction build() {
        return new SummedTransaction(title, numberOfCopiesSold, totalProfit);
    }
}
