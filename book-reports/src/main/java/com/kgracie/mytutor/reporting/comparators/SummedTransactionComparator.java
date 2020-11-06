package com.kgracie.mytutor.reporting.comparators;

import com.kgracie.mytutor.reporting.domain.SummedTransaction;

import java.util.Comparator;

public class SummedTransactionComparator implements Comparator<SummedTransaction> {

    @Override
    public int compare(SummedTransaction transaction1, SummedTransaction transaction2) {
        int copiesSold = transaction2.numberOfCopiesSold() - transaction1.numberOfCopiesSold();
        if(copiesSold == 0) {
            return (int)(transaction2.totalProfit() - transaction1.totalProfit());
        }
        return copiesSold;


    }
}
