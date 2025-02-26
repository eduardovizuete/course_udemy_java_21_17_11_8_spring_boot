package com.library.books.service.impl;

import com.library.books.controller.exception.BookIsbnAlreadyExistsException;
import com.library.books.controller.exception.BookIsbnNotFoundException;
import com.library.books.controller.exception.BookIsbnMismatchException;
import com.library.books.repository.BookRepository;
import com.library.books.repository.entity.Book;
import com.library.books.service.IBookService;
import com.library.books.service.dto.BookDTO;
import com.library.books.service.dto.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // is a Component
@AllArgsConstructor
public class BookServiceImpl implements IBookService {

    private BookRepository bookRepository;
    
    @Override
    public BookDTO addBook(Book book) {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            // car reg is already in db, do not add it again
            throw new BookIsbnAlreadyExistsException("Book isbn already exists in db! : " + book.getIsbn());
        }

        Book savedBook = bookRepository.save(book); // no need to code save(Car)!
        // so the primary key is not returned
        return BookMapper.mapToBookDTO(savedBook, new BookDTO());
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            BookDTO bookDTO = BookMapper.mapToBookDTO(book, new BookDTO()); // so the primary key is not returned
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    @Override
    public BookDTO getBook(String isbn) {
        Book book = bookRepository
                .findByIsbn(isbn) // returns Optional<Car>
                .orElseThrow(() -> new BookIsbnNotFoundException("Book isbn number not found in db! : " + isbn));
        // so the primary key is not returned
        return BookMapper.mapToBookDTO(book, new BookDTO());
    }

    @Override
    public List<BookDTO> getAllBooksByAuthors(String authors) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookRepository.findBooksByAuthorsName(authors)) {
            BookDTO bookDTO = BookMapper.mapToBookDTO(book, new BookDTO()); // so the primary key is not returned
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    @Override
    public boolean deleteBook(String isbn) {
        bookRepository
                .findByIsbn(isbn) // returns Optional<Car>
                .orElseThrow(() -> new BookIsbnNotFoundException("Book isbn number not found in db! : " + isbn));
        bookRepository.deleteByIsbn(isbn); // derived query
        return true;
    }

    @Override
    public boolean deleteAllBooks() {
        bookRepository.deleteAll(); // already available
        return true;
    }

    @Override
    public boolean updateBook(String isbn, Book book) {
        if (!isbn.equals(book.getIsbn())) { // isbn numbers do not match, generate an exception
            throw new BookIsbnMismatchException("Book isbn numbers mismatch!. URI: " + isbn + ", Entity Body: " + book.getIsbn());
        }
        // this is an update as the isbn is already in the database
        // Update - save() does insert as we know; it will do update if the primary key is present.
        // However, as I am letting the db generate ID's for the primary keys, this will not work.
        // Thus, we need to annotate the update method with @Transaction, @Query and @Modifying.
        // https://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa
        bookRepository.updateBook(book.getBookTitle(), book.getAuthors(), book.getPublisher(),
                book.getYearPublished(), book.getPrice(), book.getIsbn());
        return true;
    }

}
