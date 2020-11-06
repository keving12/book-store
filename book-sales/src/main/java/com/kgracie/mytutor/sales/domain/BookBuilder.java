package com.kgracie.mytutor.sales.domain;

public class BookBuilder {
    private String title;
    private double price;
    private int stock;

    private BookBuilder() {}

    public static BookBuilder newInstance() {
        return new BookBuilder();
    }

    public BookBuilder title(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder price(double price) {
        this.price = price;
        return this;
    }

    public BookBuilder stock(int stock) {
        this.stock = stock;
        return this;
    }

    public Book build() {
        return new Book(title, price, stock);
    }
}
