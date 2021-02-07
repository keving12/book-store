package com.kgracie.mytutor.bookstore.web.dto;

import javax.validation.constraints.NotNull;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record BookRequest(@NotNull String title, int quantity) { }
