package com.kgracie.mytutor.reporting.comparators;

import com.kgracie.mytutor.reporting.domain.SummedTransaction;

import java.util.Comparator;

public class SummedTransactionComparator implements Comparator<SummedTransaction> {

    @Override
    public int compare(SummedTransaction transaction1, SummedTransaction transaction2) {
        int copiesSold = transaction2.getNumberOfCopiesSold() - transaction1.getNumberOfCopiesSold();
        if(copiesSold == 0) {
            return (int)(transaction2.getTotalProfit() - transaction1.getTotalProfit());
        }
        return copiesSold;


    }
}
