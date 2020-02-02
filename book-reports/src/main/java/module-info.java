module com.kgracie.mytutor.bookreports {
        requires spring.context;
        requires com.kgracie.mytutor.booksales;

        exports com.kgracie.mytutor.reporting.api;
}