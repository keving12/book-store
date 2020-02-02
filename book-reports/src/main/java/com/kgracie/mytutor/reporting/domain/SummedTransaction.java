package com.kgracie.mytutor.reporting.domain;

import java.util.Objects;

public class SummedTransaction {

    private final String title;
    private final int numberOfCopiesSold;
    private final double totalProfit;

    public SummedTransaction(String title, int numberOfCopiesSold, double totalProfit) {
        this.title = title;
        this.numberOfCopiesSold = numberOfCopiesSold;
        this.totalProfit = totalProfit;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfCopiesSold() {
        return numberOfCopiesSold;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummedTransaction that = (SummedTransaction) o;
        return numberOfCopiesSold == that.numberOfCopiesSold &&
                Double.compare(that.totalProfit, totalProfit) == 0 &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numberOfCopiesSold, totalProfit);
    }

    public static class Builder {
        private String title;
        private int numberOfCopiesSold;
        private double totalProfit;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder numberOfCopiesSold(int numberOfCopiesSold) {
            this.numberOfCopiesSold = numberOfCopiesSold;
            return this;
        }

        public Builder totalProfit(double totalProfit) {
            this.totalProfit = totalProfit;
            return this;
        }

        public SummedTransaction build() {
            return new SummedTransaction(title, numberOfCopiesSold, totalProfit);
        }
    }
}
