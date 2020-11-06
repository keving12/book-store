package com.kgracie.mytutor.sales;

import com.kgracie.mytutor.sales.domain.TransactionBuilder;
import com.kgracie.mytutor.sales.domain.TransactionType;
import com.kgracie.mytutor.sales.repository.TransactionRepository;
import com.kgracie.mytutor.sales.repository.TransactionRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionRepositoryImplTest {

    private TransactionRepository transactionRepository;

    @BeforeEach
    void setup() {
        transactionRepository = TransactionRepositoryImpl.getInstance();
    }

    @Test
    void shouldRecordTransaction() {
        var salesTransaction = TransactionBuilder.newInstance()
                .transactionType(TransactionType.SALE)
                .title("Book A")
                .quantity(1)
                .value(25.00)
                .build();

        transactionRepository.recordBookTransaction(salesTransaction);
        var result = transactionRepository.fetchTransactions();

        assertThat(result).containsOnly(salesTransaction);
    }
}
