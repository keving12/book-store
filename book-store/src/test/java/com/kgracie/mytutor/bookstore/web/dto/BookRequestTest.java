package com.kgracie.mytutor.bookstore.web.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRequestTest {

    @Test
    void shouldSerialize() throws Exception {
        var bookRequest = new BookRequest("Book A", 1);
        var objectMapper = new ObjectMapper();

        var jsonString = objectMapper.writeValueAsString(bookRequest);

        assertThat(jsonString).isEqualTo("{\"title\":\"Book A\",\"quantity\":1}");

        var readRecord = objectMapper.readValue(jsonString, BookRequest.class);

        assertThat(readRecord.title()).isEqualTo("Book A");
        assertThat(readRecord.quantity()).isEqualTo(1);

    }
}
