package com.library.books.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

// @Data : Generates getters/setters, a constructor, toString, hashCode and equals.
@EqualsAndHashCode(callSuper = true)
@Data
public class BookDTO extends RepresentationModel<BookDTO> {

    private String bookTitle;

    private String authors;

    private String publisher;

    private String isbn;

    private int yearPublished;

    private int price;

}
