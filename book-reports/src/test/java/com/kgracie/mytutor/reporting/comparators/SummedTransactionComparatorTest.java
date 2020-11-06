package com.kgracie.mytutor.reporting.comparators;

import com.kgracie.mytutor.reporting.domain.SummedTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SummedTransactionComparatorTest {

    private SummedTransactionComparator summedTransactionComparator;

    @BeforeEach
    void setup() {
        summedTransactionComparator = new SummedTransactionComparator();
    }

    @Test
    void shouldOrderSummedTransactionsByNumberOfCopiesAndThenByTotalProfits() {
        var transactions = dummyTransactions();

        transactions.sort(summedTransactionComparator);

        assertThat(transactions).containsExactlyElementsOf(List.of(
                new SummedTransaction("Book C", 3, 11.00),
                new SummedTransaction("Book B", 2, 33.00),
                new SummedTransaction("Book A", 2, 22.00)
        ));
    }

    private List<SummedTransaction> dummyTransactions() {
        var transactions = new ArrayList<SummedTransaction>();
        transactions.add(new SummedTransaction("Book A", 2, 22.00));
        transactions.add(new SummedTransaction("Book B", 2, 33.00));
        transactions.add(new SummedTransaction("Book C", 3, 11.00));

        return transactions;
    }
}
