package com.library.books.controller;

import com.library.books.repository.entity.Book;
import com.library.books.service.IBookService;
import com.library.books.service.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController // is a Component
@RequestMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE) // works
@AllArgsConstructor
public class BookController {

    private IBookService iBookService; // injected due to @RestController (which maps to @Component), @AllArgsConstructor

    //  /books
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOList = iBookService.getAllBooks();

        if(bookDTOList.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT) // success, no message body
                    .build();
        } else {
            for (BookDTO bookDTO : bookDTOList) {
                // HATEOAS
                // add() is a method in RepresentationalModel
                // linkTo() and MethodOn() are both static methods in WebMvcLinkBuilder
                //   - linkTo inspect the BookController class and gets the root mapping
                //   - methodOn obtains the method mapping by making dummy invocation on the target method
                //     as they are dummy invocation, I can pass 'null' where convenient
                bookDTO.add(
                        linkTo(
                                methodOn(BookController.class).getAllBooks()
                        ).withSelfRel(),
                        linkTo(
                                methodOn(BookController.class).addBook(null, null)
                        ).withRel("addBook"),
                        linkTo(
                                methodOn(BookController.class).getBookDetailsPath(bookDTO.getIsbn())
                        ).withRel("getBook"),
                        linkTo(
                                methodOn(BookController.class).updateBook(bookDTO.getIsbn(), null)
                        ).withRel("updateBook"),
                        linkTo(
                                methodOn(BookController.class).deleteBookDetails(bookDTO.getIsbn())
                        ).withRel("deleteBook")
                );
            }
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDTOList);
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody Book book, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("XXX book is " + book);
        BookDTO bookDTO = iBookService.addBook(book);

        URI locationURI = uriComponentsBuilder
                .path("/books/" + bookDTO.getIsbn())
                .buildAndExpand(uriComponentsBuilder.toUriString())
                .toUri();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(locationURI)
                .body(bookDTO);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllBooks() {
        iBookService.deleteAllBooks();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // success, no message body
                .build();
    }

    @PutMapping
    public ResponseEntity<String> putNotSupported() {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }

    @GetMapping(path = "/book", params = "authors")  // "params" value must match (@RequestParam identifier)
    public ResponseEntity<List<BookDTO>> getAllBookByAuthors(@RequestParam String authors) { // must be ?authorName=XX in Postman
        List<BookDTO> authorsBooks = iBookService.getAllBooksByAuthors(authors);
        if(authorsBooks.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT) // success, no message body
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authorsBooks);
        }
    }

    //       /{isbn}
    @GetMapping("/{isbn}")
    public ResponseEntity<BookDTO> getBookDetailsPath(@PathVariable String isbn) {
        BookDTO BookDTO = iBookService.getBook(isbn);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BookDTO);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> deleteBookDetails(@PathVariable String isbn) {
        iBookService.deleteBook(isbn);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // success, no message body
                .build();
    }

    @PostMapping("/{isbn}")
    public ResponseEntity<String> postNotSupported() {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        iBookService.updateBook(isbn, book);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // no message body
                .build();
    }

    //    @OptionsMapping  - not available
    //    @HeadMapping     - not available
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsCollectionOfBooks() {  //      /books
        return ResponseEntity
                .status(HttpStatus.OK) // 200 OK
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE) // varargs list
                .build();
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsIndividualBook() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
                .build();
    }

    @RequestMapping(value = "/book", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsIndividualRequestParams() {
        return ResponseEntity
                .status(HttpStatus.OK) // success
                .allow(HttpMethod.HEAD, HttpMethod.GET)
                .build();
    }
    
}
