package com.library.books.repository;

import com.library.books.repository.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // The methods defined here are custom methods that the JPA will implement for me, assuming I name
    // them properly. These are in addition to the other methods already available e.g. save().

    Optional<Book> findByIsbn(String isbn); // must have a property "isbn" in Book

    // no method save(Book) defined, framework provides by default

    // A derived query => I would have to provide the full authors name exactly.
    List<Book> findByAuthors(String author); // must have a property "authors" in Book

    // A derived query would work but then I would have to type in the full authors name exactly.
    // I would prefer a wildcard.
    @Query("select b from Book b where authors like %:authorName%")
    List<Book> findBooksByAuthorsName(@Param("authorName") String authorName);


    @Transactional
        // jakarta, REQUIRED, this method is done completely or not at all
    void deleteByIsbn(String isbn);

    @Transactional  // jakarta, REQUIRED, this method is done completely or not at all
    @Modifying
    @Query("update Book b set b.bookTitle = ?1, b.authors = ?2, b.publisher = ?3, b.yearPublished = ?4, b.price = ?5 where b.isbn = ?6")
        // @Query enables SELECT, @Modifying adds INSERT, UPDATE and DELETE
    void updateBook(String bookTitle, String authors, String publisher,
                   Integer yearPublished, Integer price, String isbn);

}