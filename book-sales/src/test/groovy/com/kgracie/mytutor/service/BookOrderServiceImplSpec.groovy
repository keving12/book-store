package com.kgracie.mytutor.service

import com.kgracie.mytutor.repository.BookRepository
import spock.lang.Specification

class BookOrderServiceImplSpec extends Specification {

    def bookRepository
    def bookOrderingService

    def setup() {
        bookRepository = Mock(BookRepository)
        bookOrderingService = new BookOrderServiceImplSpec()
    }

    def 'should increase book stock when books ordered'() {
        when:
        bookOrderingService.orderBooksOfTitle('Book A')

        then:
        1 * bookRepository.incrementBookStock('Book A', 10)
    }

}
