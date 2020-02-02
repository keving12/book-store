package com.kgracie.mytutor.service

import com.kgracie.mytutor.repository.BookRepositoryImpl
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class BookRepositoryImplSpec extends Specification {

    def bookRepository

    def setup() {
        bookRepository = BookRepositoryImpl.getInstance()
    }

    def 'initial state should be each book with a quantity of 10 each'() {
        when:
        def book = bookRepository.fetchBook(bookTitle)

        then:
        book.title == bookTitle
        book.price == price
        book.stock == 10

        where:
        bookTitle   | price
        'Book A'    | 25.00
        'Book B'    | 20.00
        'Book C'    | 23.00
        'Book D'    | 30.00
        'Book E'    | 27.00
    }

    def 'should return null when book does not exist'() {
        when:
        def book = bookRepository.fetchBook('Book X')

        then:
        book == null
    }

    def 'should increment book stock'() {
        when:
        bookRepository.incrementBookStock('Book A', 3)

        then:
        def bookA = bookRepository.fetchBook('Book A')
        bookA.stock == 13
    }

    def 'should decrement book stock'() {
        when:
        bookRepository.decrementBookStock('Book B', 3)

        then:
        def bookB = bookRepository.fetchBook('Book B')
        bookB.stock == 7
    }
}
