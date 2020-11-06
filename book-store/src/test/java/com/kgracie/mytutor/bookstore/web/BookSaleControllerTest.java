package com.kgracie.mytutor.bookstore.web;

import com.kgracie.mytutor.sales.domain.BookBuilder;
import com.kgracie.mytutor.sales.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookSaleControllerTest {

    private static final String BOOK_TITLE = "Book A";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookRepository bookRepository;

    @Test
    void shouldReturnSuccessMessageWhenEnoughStock() throws Exception {
        var book = BookBuilder.newInstance()
                .title(BOOK_TITLE)
                .stock(1)
                .price(10.00)
                .build();

        given(bookRepository.fetchBook(BOOK_TITLE)).willReturn(book);

        var result = mockMvc.perform(get("/book/Book A/buy/1"))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("Thank you for your purchase!");
    }

    @Test
    void shouldReturnFailureMessageWhenNotEnoughStock() throws Exception {
        var book = BookBuilder.newInstance()
                .title(BOOK_TITLE)
                .stock(1)
                .price(10.00)
                .build();
        given(bookRepository.fetchBook(BOOK_TITLE)).willReturn(book);

        var result = mockMvc.perform(get("/book/Book A/buy/2"))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("Sorry, we are out of stock.");
    }

    @Test
    void shouldReturnFailureMessageWhenBookNotFound() throws Exception {
        var result = mockMvc.perform(get("/book/Book X/buy/1"))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("Sorry, we do not stock the book requested");
    }
}
