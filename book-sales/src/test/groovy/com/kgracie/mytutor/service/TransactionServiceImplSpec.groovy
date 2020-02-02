package com.kgracie.mytutor.service

import com.kgracie.mytutor.domain.Transaction
import com.kgracie.mytutor.domain.TransactionType
import com.kgracie.mytutor.impl.TransactionServiceImpl
import com.kgracie.mytutor.repository.TransactionRepository
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class TransactionServiceImplSpec extends Specification {

    def transactionRepository
    def transactionService

    def setup() {
        transactionRepository = Mock(TransactionRepository)
        transactionService = new TransactionServiceImpl(transactionRepository)
    }

    def 'should retrieve all transactions'() {
        given:
        def transaction = new Transaction.Builder()
                .transactionType(TransactionType.SALE)
                .title('Book X')
                .quantity(6)
                .value(10.00)
                .build()

        transactionRepository.fetchTransactions() >> [transaction]

        when:
        def result = transactionService.retrieveTransactions()

        then:
        result == [transaction]
    }

    def 'should store sales transaction with value as unit price * quantity'() {
        given:
        def transaction = new Transaction.Builder()
                .transactionType(transactionType)
                .title(title)
                .quantity(quantity)
                .value(transactionValue)
                .build()

        when:
        transactionService.recordTransaction(transactionType, title, quantity, unitPrice)

        then:
        1 * transactionRepository.recordBookTransaction(transaction)

        where:
        transactionType         | title    | quantity | unitPrice  | transactionValue
        TransactionType.SALE    | 'Book A' | 2        | 10.00      | 20.00
        TransactionType.SALE    | 'Book C' | 10       | 20.00      | 200.00
    }

    def 'should store purchase transactions with 70% of unit price * quantity'() {
        given:
        def transaction = new Transaction.Builder()
                .transactionType(transactionType)
                .title(title)
                .quantity(quantity)
                .value(transactionValue)
                .build()

        when:
        transactionService.recordTransaction(transactionType, title, quantity, unitPrice)

        then:
        1 * transactionRepository.recordBookTransaction(transaction)

        where:
        transactionType             | title    | quantity | unitPrice  | transactionValue
        TransactionType.PURCHASE    | 'Book A' | 2        | 10.00      | 14.00
        TransactionType.PURCHASE    | 'Book C' | 10       | 20.00      | 140.00
    }
}
