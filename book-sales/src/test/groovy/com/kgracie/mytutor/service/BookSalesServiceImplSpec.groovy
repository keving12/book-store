package com.kgracie.mytutor.service

import com.kgracie.mytutor.impl.BookSalesServiceImpl
import com.kgracie.mytutor.repository.BookRepository
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class BookSalesServiceImplSpec extends Specification {

    def bookRepository
    def bookSalesService

    def setup() {
        bookRepository = Mock(BookRepository)
        bookSalesService = new BookSalesServiceImpl()
    }

    def 'should reduce book count when enough stock to purchase stock'() {
        given:
        bookRepository.checkBookStock(bookTitle) >> stockLevel

        when:
        bookSalesService.processBookSale(bookTitle, quantity)

        then:
        1 * bookRepository.checkBookStock(bookTitle)
        1 * bookRepository.decrementBookStock(bookTitle, quantity)

        where:
        bookTitle   | stockLevel    | quantity
        'Book A'    | 2             | 1
        'Book A'    | 4             | 3

    }

    def 'should not change stock count when not enough stock'() {
        given:
        bookRepository.checkBookStock(bookTitle) >> stockLevel

        when:
        bookSalesService.processBookSale(bookTitle, quantity)

        then:
        1 * bookRepository.checkBookStock(bookTitle)
        0 * bookRepository.decrementBookStock(_ as String, _ as int)

        where:
        bookTitle   | stockLevel    | quantity
        'Book A'    | 2             | 3
        'Book A'    | 4             | 5
    }

    def 'should return message indicating book purchase success or failure'() {
        given:
        bookRepository.checkBookStock(bookTitle) >> stockLevel

        when:
        def result = bookSalesService.processBookSale(bookTitle, quantity)

        then:
        1 * bookRepository.decre
        result.get().responseMessage == expectedResponseMessage

        where:
        bookTitle   | stockLevel    | quantity      | expectedResponseMessage
        'Book A'    | 10            | 2             | 'Thank you for your purchase!'
        'Book A'    | 2             | 3             | 'Sorry, we are out of stock.'
    }

    def 'should indicate when book cannot be found for given title'() {
        given:
        def bookTitle = 'Book X'
        bookRepository.checkBookStock('Book X') >> null

        when:
        def result = bookSalesService.processBookSale(bookTitle, 1)

        then:
        result == null
    }
}
