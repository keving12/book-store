package com.kgracie.mytutor.sales;

import com.kgracie.mytutor.sales.api.BookSalesService;
import com.kgracie.mytutor.sales.api.TransactionService;
import com.kgracie.mytutor.sales.domain.BookBuilder;
import com.kgracie.mytutor.sales.domain.TransactionType;
import com.kgracie.mytutor.sales.impl.BookSalesServiceImpl;
import com.kgracie.mytutor.sales.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookSalesServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private TransactionService transactionService;

    private BookSalesService bookSalesService;

    @BeforeEach
    void setup() {
        bookSalesService = new BookSalesServiceImpl(bookRepository, transactionService);
    }

    @ParameterizedTest
    @CsvSource({
            "Book A, 10, 1",
            "Book A, 10, 3"
    })
    void shouldReduceBookCountWhenEnoughStockToCoverPurchase(String title, int stockLevel, int quantity) {
        var book = BookBuilder.newInstance()
                .title(title)
                .price(10.00)
                .stock(stockLevel)
                .build();

        given(bookRepository.fetchBook(title)).willReturn(book);

        bookSalesService.processBookSale(title, quantity);

        verify(bookRepository).fetchBook(title);
        verify(bookRepository).decrementBookStock(title, quantity);
        verify(transactionService).recordTransaction(TransactionType.SALE, title, quantity, 10.00);
    }

    @ParameterizedTest
    @CsvSource({
            "Book A, 2, 3",
            "Book A, 4, 5"
    })
    void shouldNotChangeStockCountWhenNotEnoughStock(String title, int stockLevel, int quantity) {
        var book = BookBuilder.newInstance()
                .title(title)
                .price(10.00)
                .stock(stockLevel)
                .build();

        given(bookRepository.fetchBook(title)).willReturn(book);

        bookSalesService.processBookSale(title, quantity);

        verify(bookRepository).fetchBook(title);
        verify(bookRepository, never()).decrementBookStock(anyString(), anyInt());
        verify(transactionService, never()).recordTransaction(any(TransactionType.class), anyString(), anyInt(), anyDouble());
    }

    @ParameterizedTest
    @CsvSource({
            "Book A, 10, 2, Thank you for your purchase!",
            "Book A, 2, 3, 'Sorry, we are out of stock.'"
    })
    void shouldReturnMessageIndicatingBookPurchaseSuccessOrFailure(String title, int stockLevel, int quantity, String expectedMessage) {
        var book = BookBuilder.newInstance()
                .title(title)
                .price(15.00)
                .stock(stockLevel)
                .build();

        given(bookRepository.fetchBook(title)).willReturn(book);

        var result = bookSalesService.processBookSale(title, quantity);

        assertThat(result.responseMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void shouldIndicateWhenBookCannotBeFoundForGivenTitle() {
        var bookTitle = "Book X";

        given(bookRepository.fetchBook(bookTitle)).willReturn(null);

        var result = bookSalesService.processBookSale(bookTitle, 1);

        assertThat(result.responseMessage()).isEqualTo("Sorry, we do not stock the book requested");
    }

    @Test
    void shouldBuyMoreBookWhenStockLevelBelow3AfterSale() {
        var bookTitle = "Book X";
        var book = BookBuilder.newInstance()
                .title(bookTitle)
                .price(10.00)
                .stock(4)
                .build();

        given(bookRepository.fetchBook(bookTitle)).willReturn(book);

        bookSalesService.processBookSale(bookTitle, 2);

        verify(bookRepository).incrementBookStock(bookTitle, 10);
        verify(transactionService).recordTransaction(TransactionType.PURCHASE, bookTitle, 10, 10.00);
    }
}
