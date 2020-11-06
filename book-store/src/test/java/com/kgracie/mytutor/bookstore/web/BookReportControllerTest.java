package com.kgracie.mytutor.bookstore.web;

import com.kgracie.mytutor.reporting.api.BookReportingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookReportController.class)
public class BookReportControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookReportingService bookReportingService;

    @Test
    void shouldPrintReport() throws Exception {
        var result = mockMvc.perform(get("/book-store/report"))
                .andReturn();

        var responseString = result.getResponse().getContentAsString();

        assertThat(responseString).isEqualTo("");
    }
}
