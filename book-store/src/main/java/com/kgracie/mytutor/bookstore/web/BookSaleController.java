package com.kgracie.mytutor.bookstore.web;

import com.kgracie.mytutor.bookstore.web.dto.BookRequest;
import com.kgracie.mytutor.sales.api.BookSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookSaleController {

    private final BookSalesService bookSalesService;

    @Autowired
    public BookSaleController(BookSalesService bookSalesService) {
        this.bookSalesService = bookSalesService;
    }

    @GetMapping(value = "/book/{title}/buy/{quantity}")
    public String buyBook(@PathVariable("title") String title,
                          @PathVariable("quantity") int quantity) {
        return bookSalesService.processBookSale(title, quantity).responseMessage();
    }

    @PostMapping(value = "/buy-book")
    public String buyBook(@RequestBody @Valid BookRequest bookRequest) {
        return bookSalesService.processBookSale(bookRequest.title(), bookRequest.quantity()).responseMessage();
    }

}
