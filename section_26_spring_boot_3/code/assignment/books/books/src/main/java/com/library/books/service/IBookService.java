package com.library.books.service;

import com.library.books.repository.entity.Book;
import com.library.books.service.dto.BookDTO;

import java.util.List;

public interface IBookService {

    // POST
    BookDTO addBook(Book Book);

    // GET
    List<BookDTO> getAllBooks();

    BookDTO getBook(String isbn);

    List<BookDTO> getAllBooksByAuthors(String authors);

    // DELETE
    boolean deleteBook(String isbn);

    boolean deleteAllBooks();

    // PUT
    boolean updateBook(String isbn, Book book);
    
}
