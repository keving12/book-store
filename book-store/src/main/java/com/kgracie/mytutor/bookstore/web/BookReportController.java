package com.kgracie.mytutor.bookstore.web;

import com.kgracie.mytutor.reporting.api.BookReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookReportController {

    private final BookReportingService bookReportingService;

    @Autowired
    public BookReportController(BookReportingService bookReportingService) {
        this.bookReportingService = bookReportingService;
    }

    @GetMapping(value = "/book-store/report")
    public String printReport() {
        return bookReportingService.generateReport();
    }
}
