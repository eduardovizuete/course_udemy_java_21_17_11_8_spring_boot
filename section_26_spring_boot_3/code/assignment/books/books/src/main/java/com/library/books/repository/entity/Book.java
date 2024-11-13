package com.library.books.repository.entity;

// JPQL = Jakarta Persistence Query Language

import jakarta.persistence.*;
import lombok.*;

@Entity // jakarta
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor // lombok
public class Book {

    @Id // jakarta
    @GeneratedValue(strategy = GenerationType.IDENTITY) // jakarta - use a db id column
    private Long id;

    // @Column required if the member != the db column name i.e. book_title != bookTitle
    @Column(name = "book_title") // jakarta
    private String bookTitle;

    private String authors;

    private String publisher;

    private String isbn;

    @Column(name = "year_published")
    private int yearPublished;

    private int price;

}
