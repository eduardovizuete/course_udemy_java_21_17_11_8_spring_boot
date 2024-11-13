package com.library.books.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookIsbnNotFoundException extends RuntimeException {

    public BookIsbnNotFoundException(String message) {
        super(message);
    }

}
