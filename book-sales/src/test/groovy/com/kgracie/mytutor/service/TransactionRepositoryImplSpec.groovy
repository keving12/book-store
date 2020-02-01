package com.kgracie.mytutor.service

import com.kgracie.mytutor.domain.Transaction
import com.kgracie.mytutor.domain.TransactionType
import com.kgracie.mytutor.repository.TransactionRepositoryImpl
import spock.lang.Specification

class TransactionRepositoryImplSpec extends Specification {

    def transactionRepository

    def setup() {
        transactionRepository = TransactionRepositoryImpl.getInstance()
    }

    def 'should record transaction'() {
        given:
        def salesTransaction = new Transaction.Builder()
                .transactionType(TransactionType.SALE)
                .title('Book A')
                .quantity(1)
                .value(25.00)
                .build()

        when:
        transactionRepository.recordBookTransaction(salesTransaction)
        def result = transactionRepository.fetchTransactions()

        then:
        result == [salesTransaction]
    }
}
