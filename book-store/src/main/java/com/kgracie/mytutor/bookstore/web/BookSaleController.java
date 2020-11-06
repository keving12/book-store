package com.kgracie.mytutor.bookstore.web;

import com.kgracie.mytutor.sales.api.BookSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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


}
