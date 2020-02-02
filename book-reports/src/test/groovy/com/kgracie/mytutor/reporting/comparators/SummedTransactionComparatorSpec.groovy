package com.kgracie.mytutor.reporting.comparators

import com.kgracie.mytutor.reporting.domain.SummedTransaction
import spock.lang.Specification

class SummedTransactionComparatorSpec extends Specification {

    def summedTransactionComparator = new SummedTransactionComparator()

    def 'should order SummedTransactions by number of copies and then by total profit'() {
        given:
        def transactions = [
                new SummedTransaction('Book A', 2, 22.00),
                new SummedTransaction('Book B', 2, 33.00),
                new SummedTransaction('Book C', 3, 11.00)
        ]

        when:
        transactions.sort(summedTransactionComparator)

        then:
        transactions == [
                new SummedTransaction('Book C', 3, 11.00),
                new SummedTransaction('Book B', 2, 33.00),
                new SummedTransaction('Book A', 2, 22.00),
        ]
    }
}
