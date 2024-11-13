package com.library.books.service.dto;

import com.library.books.repository.entity.Book;

@SuppressWarnings("ALL")
public class BookMapper {

    public static BookDTO mapToBookDTO(Book book, BookDTO bookDTO) {
        bookDTO.setBookTitle(book.getBookTitle());
        bookDTO.setAuthors(book.getAuthors());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setYearPublished(book.getYearPublished());
        bookDTO.setPrice(book.getPrice());
        return bookDTO;
    }

}