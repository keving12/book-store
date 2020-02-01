package com.kgracie.mytutor.domain;

import java.util.Objects;

public class BookSalesResponse {

    private final String responseMessage;

    public BookSalesResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSalesResponse that = (BookSalesResponse) o;
        return Objects.equals(responseMessage, that.responseMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseMessage);
    }
}
