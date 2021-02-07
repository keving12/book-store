module com.kgracie.mytutor.bookstore {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires java.validation;
    requires spring.boot.starter.validation;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    requires com.kgracie.mytutor.bookreports;
    requires com.kgracie.mytutor.booksales;
}
