package com.kgracie.mytutor.reporting;

import com.kgracie.mytutor.reporting.api.BookReportingService;
import com.kgracie.mytutor.reporting.impl.BookReportingServiceImpl;
import com.kgracie.mytutor.sales.api.TransactionService;
import com.kgracie.mytutor.sales.domain.Transaction;
import com.kgracie.mytutor.sales.domain.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookReportingServiceImplTest {

    @Mock
    private TransactionService transactionService;

    private BookReportingService bookReportingService;

    @BeforeEach
    void setup() {
        bookReportingService = new BookReportingServiceImpl(transactionService);
    }

    @Test
    void shouldCalculateBookTransactions() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("Book D | 4 Copies Sold | £40.30 Total Profit\n");
        stringBuilder.append("Book A | 2 Copies Sold | £28.60 Total Profit\n");
        stringBuilder.append("Book B | 2 Copies Sold | £24.00 Total Profit\n");

        given(transactionService.retrieveTransactions()).willReturn(dummyTransactions());

        var result = bookReportingService.generateReport();

        assertThat(result).isEqualTo(stringBuilder.toString());
    }

    private List<Transaction> dummyTransactions() {
        return List.of(
                new Transaction(TransactionType.SALE, "Book A", 2, 44.00),
                new Transaction(TransactionType.SALE, "Book B", 1, 12.00),
                new Transaction(TransactionType.SALE, "Book D", 4, 62.00),
                new Transaction(TransactionType.SALE, "Book B", 1, 12.00),
                new Transaction(TransactionType.PURCHASE, "Book D", 2, -21.70),
                new Transaction(TransactionType.PURCHASE, "Book A", 1, -15.40)
        );
    }
}
