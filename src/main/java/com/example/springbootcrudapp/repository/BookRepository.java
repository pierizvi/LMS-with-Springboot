package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by title containing a keyword
    List<Book> findByTitleContaining(String keyword);

    // Find books by genre
    List<Book> findByGenre(String genre);

    // Find books by author id
    List<Book> findByAuthorId(Long authorId);

    // Custom query to perform inner join between Book and Author
    @Query("SELECT b FROM Book b INNER JOIN b.author a WHERE a.lastName = ?1")
    List<Book> findBooksByAuthorLastName(String lastName);

    // Get books and authors together (inner join)
    @Query("SELECT b, a FROM Book b INNER JOIN b.author a")
    List<Object[]> findBooksWithAuthors();
}