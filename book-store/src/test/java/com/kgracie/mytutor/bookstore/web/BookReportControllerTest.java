package com.kgracie.mytutor.bookstore.web;

import com.kgracie.mytutor.sales.domain.Transaction;
import com.kgracie.mytutor.sales.domain.TransactionType;
import com.kgracie.mytutor.sales.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookReportControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    void shouldPrintReport() throws Exception {
        var transactions = List.of(
                new Transaction.Builder()
                        .transactionType(TransactionType.SALE)
                        .title("Book B")
                        .quantity(2)
                        .value(64.00)
                        .build()
        );

        given(transactionRepository.fetchTransactions()).willReturn(transactions);

        var result = mockMvc.perform(get("/book-store/report"))
                .andReturn();

        var responseString = result.getResponse().getContentAsString();

        assertThat(responseString).isEqualTo("Book B | 2 Copies Sold | £64.00 Total Profit\n");
    }
}
