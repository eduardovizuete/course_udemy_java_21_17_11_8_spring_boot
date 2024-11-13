package com.library.books.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookIsbnAlreadyExistsException.class)
    public ResponseEntity<DetailedErrorResponse> handleIsbnAlreadyExists(BookIsbnAlreadyExistsException exception,
                                                                          WebRequest webRequest) {
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookIsbnNotFoundException.class)
    public ResponseEntity<DetailedErrorResponse> handleIsbnNoNotFound(BookIsbnNotFoundException exception,
                                                                     WebRequest webRequest) {
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookIsbnMismatchException.class)
    public ResponseEntity<DetailedErrorResponse> handleIsbnNumbersMismatchOnPut(BookIsbnMismatchException exception,
                                                                                WebRequest webRequest) {
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
