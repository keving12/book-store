module com.kgracie.mytutor.bookstore {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.beans;
    requires spring.context;
    requires spring.core;

    requires com.kgracie.mytutor.bookreports;
    requires com.kgracie.mytutor.booksales;
}
