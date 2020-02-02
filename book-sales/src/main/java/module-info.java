module com.kgracie.mytutor.booksales {
    requires spring.context;
    requires spring.web;

    exports com.kgracie.mytutor.sales.api;
    exports com.kgracie.mytutor.sales.domain;
}