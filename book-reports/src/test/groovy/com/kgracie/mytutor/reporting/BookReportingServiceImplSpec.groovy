package com.kgracie.mytutor.reporting

import com.kgracie.mytutor.sales.api.TransactionService
import com.kgracie.mytutor.sales.domain.Transaction
import com.kgracie.mytutor.sales.domain.TransactionType
import spock.lang.Specification

class BookReportingServiceImpl extends Specification {

    def transactionService
    def bookReportingService

    def setup() {
        transactionService = Mock(TransactionService)
        bookReportingService = new BookReportingServiceImpl(transactionService)
    }

    def 'should calculate book transactions'() {
        given:
        transactionService.fetchTransactions() >> transactions()

        when:
        def result = bookReportingService.generateReport()

        then:
        result == 'Book D | 4 Copies Sold | £62.00 Total Profit\n' +
                'Book A | 2 Copies Sold | £44.00 Total Profit\n' +
                'Book B | 1 Copies Sold | £12.00 Total Profit'
    }

    def transactions() {
        return [
                new Transaction(TransactionType.SALE, 'Book A', 2, 44.00),
                new Transaction(TransactionType.SALE, 'Book B', 1, 12.00),
                new Transaction(TransactionType.SALE, 'Book D', 4, 62.00)
        ] as List
    }

}
