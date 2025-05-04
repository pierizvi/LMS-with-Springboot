package com.example.springbootcrudapp.service;

import com.example.springbootcrudapp.dto.BookDTO;
import com.example.springbootcrudapp.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    BookDTO createBook(BookDTO bookDTO);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    boolean deleteBook(Long id);

    List<BookDTO> getBooksByAuthorId(Long authorId);

    List<BookDTO> getBooksByAuthorLastName(String lastName);

    // Get books with authors using inner join
    List<Map<String, Object>> getBooksWithAuthors();

    // Convert entities to DTOs and vice versa
    BookDTO convertToDTO(Book book);

    Book convertToEntity(BookDTO bookDTO);
}