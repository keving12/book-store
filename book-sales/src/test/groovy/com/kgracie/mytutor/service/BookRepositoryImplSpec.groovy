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
        def bookCount = bookRepository.checkBookStock(bookTitle)

        then:
        bookCount == 10

        where:
        bookTitle << ['Book A', 'Book B', 'Book C', 'Book D','Book E']
    }

    def 'should return null when book does not exist'() {
        when:
        def bookCount = bookRepository.checkBookStock('Book X')

        then:
        bookCount == null
    }

    def 'should return new stock count when book stock incremented'() {
        when:
        def newBookCount = bookRepository.incrementBookStock('Book A', 3)

        then:
        newBookCount == 13
    }

    def 'should return new stock count when book stock decremented'() {
        when:
        def newBookCount = bookRepository.decrementBookStock('Book B', 3)

        then:
        newBookCount == 7
    }
}
