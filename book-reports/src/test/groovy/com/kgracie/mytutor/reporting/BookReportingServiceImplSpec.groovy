package com.kgracie.mytutor.reporting

import com.kgracie.mytutor.reporting.impl.BookReportingServiceImpl
import com.kgracie.mytutor.sales.api.TransactionService
import com.kgracie.mytutor.sales.domain.Transaction
import com.kgracie.mytutor.sales.domain.TransactionType
import spock.lang.Specification

class BookReportingServiceImplSpec extends Specification {

    def transactionService
    def bookReportingService

    def setup() {
        transactionService = Mock(TransactionService)
        bookReportingService = new BookReportingServiceImpl(transactionService)
    }

    def 'should calculate book transactions'() {
        given:
        def stringBuilder = new StringBuilder()
        stringBuilder.append('Book D | 4 Copies Sold | £40.30 Total Profit\n')
        stringBuilder.append('Book A | 2 Copies Sold | £28.60 Total Profit\n')
        stringBuilder.append('Book B | 2 Copies Sold | £24.00 Total Profit\n')
        transactionService.retrieveTransactions() >> transactions()

        when:
        def result = bookReportingService.generateReport()

        then:
        result == stringBuilder.toString()
    }

    def transactions() {
        return [
                new Transaction(TransactionType.SALE, 'Book A', 2, 44.00),
                new Transaction(TransactionType.SALE, 'Book B', 1, 12.00),
                new Transaction(TransactionType.SALE, 'Book D', 4, 62.00),
                new Transaction(TransactionType.SALE, 'Book B', 1, 12.00),
                new Transaction(TransactionType.PURCHASE, 'Book D', 2, -21.70),
                new Transaction(TransactionType.PURCHASE, 'Book A', 1, -15.40)
        ] as List
    }

}
