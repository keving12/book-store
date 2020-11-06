package com.kgracie.mytutor.sales;

import com.kgracie.mytutor.sales.api.TransactionService;
import com.kgracie.mytutor.sales.domain.Transaction;
import com.kgracie.mytutor.sales.domain.TransactionType;
import com.kgracie.mytutor.sales.impl.TransactionServiceImpl;
import com.kgracie.mytutor.sales.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    void shouldRetrieveAllTransactions() {
        var transaction = new Transaction.Builder()
                .transactionType(TransactionType.SALE)
                .title("Book X")
                .quantity(6)
                .value(10.00)
                .build();

        given(transactionRepository.fetchTransactions()).willReturn(List.of(transaction));

        var result = transactionService.retrieveTransactions();

        assertThat(result).containsOnly(transaction);
    }

    @ParameterizedTest
    @CsvSource({
            "SALE, Book A, 2, 10.00, 20.00",
            "SALE, Book C, 10, 20.00, 200.00"
    })
    void shouldStoreSalesTransactionWithValueAsProductOfUnitPriceAndQuantity(String transactionType, String title, int quantity,
                                                                             double unitPrice, double totalPrice) {
        var transaction = new Transaction.Builder()
                .transactionType(TransactionType.valueOf(transactionType))
                .title(title)
                .quantity(quantity)
                .value(totalPrice)
                .build();

        transactionService.recordTransaction(TransactionType.valueOf(transactionType), title, quantity, unitPrice);

        verify(transactionRepository).recordBookTransaction(transaction);
    }

    @ParameterizedTest
    @CsvSource({
            "PURCHASE, Book A, 2, 10.00, -14.00",
            "PURCHASE, Book C, 10, 20.00, -140.00"
    })
    void shouldStorePurchaseTransactionWithValueAs70PercentOfProduceOfUnitPriceAndQuantity(String transactionType, String title, int quantity,
                                                                                           double unitPrice, double totalPrice) {
        var transaction = new Transaction.Builder()
                .transactionType(TransactionType.valueOf(transactionType))
                .title(title)
                .quantity(quantity)
                .value(totalPrice)
                .build();

        transactionService.recordTransaction(TransactionType.valueOf(transactionType), title, quantity, unitPrice);

        verify(transactionRepository).recordBookTransaction(transaction);
    }
}
