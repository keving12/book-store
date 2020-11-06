package com.kgracie.mytutor.sales;

import com.kgracie.mytutor.sales.repository.BookRepository;
import com.kgracie.mytutor.sales.repository.BookRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryImplTest {

    private BookRepository bookRepository;

    @BeforeEach
    void setup() {
        bookRepository = BookRepositoryImpl.getInstance();
    }

    @ParameterizedTest
    @CsvSource({
            "Book A, 25.00",
            "Book B, 20.00",
            "Book C, 23.00",
            "Book D, 30.00",
            "Book E, 27.00"
    })
    void shouldHaveInitialStateWithBooksOf10(String bookTitle, double price) {
        var book = bookRepository.fetchBook(bookTitle);

        assertThat(book.title()).isEqualTo(bookTitle);
        assertThat(book.price()).isEqualTo(price);
        assertThat(book.stock()).isEqualTo(10);
    }

    @Test
    void shouldReturnNullWhenBookDoesNotExist() {
        assertThat(bookRepository.fetchBook("Book X")).isNull();
    }

    @Test
    void shouldIncrementBookStock() {
        bookRepository.incrementBookStock("Book A", 3);

        var bookA = bookRepository.fetchBook("Book A");
        assertThat(bookA.stock()).isEqualTo(13);
    }

    @Test
    void shouldDecrementBookStock() {
        bookRepository.decrementBookStock("Book A", 3);

        var bookB = bookRepository.fetchBook("Book B");
        assertThat(bookB.stock()).isEqualTo(10);
    }
}
