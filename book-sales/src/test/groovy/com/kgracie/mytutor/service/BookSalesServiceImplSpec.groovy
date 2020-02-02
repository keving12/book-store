package com.kgracie.mytutor.service

import com.kgracie.mytutor.api.TransactionService
import com.kgracie.mytutor.domain.Book
import com.kgracie.mytutor.domain.TransactionType
import com.kgracie.mytutor.impl.BookSalesServiceImpl
import com.kgracie.mytutor.repository.BookRepository
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class BookSalesServiceImplSpec extends Specification {

    def bookRepository
    def transactionService
    def bookSalesService

    def setup() {
        bookRepository = Mock(BookRepository)
        transactionService = Mock(TransactionService)
        bookSalesService = new BookSalesServiceImpl(bookRepository, transactionService)
    }

    def 'should reduce book count when enough stock to purchase stock'() {
        given:
        def book = new Book(bookTitle, 10.00, stockLevel)

        when:
        bookSalesService.processBookSale(bookTitle, quantity)

        then:
        1 * bookRepository.fetchBook(bookTitle) >> book
        1 * bookRepository.decrementBookStock(bookTitle, quantity)
        1 * transactionService.recordTransaction(_ as TransactionType, _ as String, _ as Integer, _ as Double)

        where:
        bookTitle   | stockLevel    | quantity
        'Book A'    | 10            | 1
        'Book A'    | 10            | 3

    }

    def 'should not change stock count when not enough stock'() {
        given:
        def book = new Book(bookTitle, 10.00, stockLevel)

        when:
        bookSalesService.processBookSale(bookTitle, quantity)

        then:
        1 * bookRepository.fetchBook(bookTitle) >> book
        0 * bookRepository.decrementBookStock(_ as String, _ as int)
        0 * transactionService.recordTransaction(_ as TransactionType, _ as String, _ as Integer, _ as Double)

        where:
        bookTitle   | stockLevel    | quantity
        'Book A'    | 2             | 3
        'Book A'    | 4             | 5
    }

    def 'should return message indicating book purchase success or failure'() {
        given:
        def book = new Book(bookTitle, 15.00, stockLevel)

        when:
        def result = bookSalesService.processBookSale(bookTitle, quantity)

        then:
        1 * bookRepository.fetchBook(bookTitle) >> book
        result.responseMessage == expectedResponseMessage

        where:
        bookTitle   | stockLevel    | quantity      | expectedResponseMessage
        'Book A'    | 10            | 2             | 'Thank you for your purchase!'
        'Book A'    | 2             | 3             | 'Sorry, we are out of stock.'
    }

    def 'should indicate when book cannot be found for given title'() {
        given:
        def bookTitle = 'Book X'
        bookRepository.fetchBook(bookTitle) >> null

        when:
        def result = bookSalesService.processBookSale(bookTitle, 1)

        then:
        result.responseMessage == 'Sorry, we do not stock the book requested'
    }

    def 'should buy more books when stock level below 3 after sale'() {
        given:
        def book = new Book('Book X', 10.00, 4)
        bookRepository.fetchBook('Book X') >> book

        when:
        bookSalesService.processBookSale('Book X', 2)

        then:
        1 * bookRepository.incrementBookStock('Book X', 10)
        1 * transactionService.recordTransaction(TransactionType.PURCHASE, 'Book X', 10, 10.00)
    }
}
