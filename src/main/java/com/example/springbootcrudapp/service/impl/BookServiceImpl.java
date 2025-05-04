package com.example.springbootcrudapp.service.impl;

import com.example.springbootcrudapp.dto.BookDTO;
import com.example.springbootcrudapp.entity.Author;
import com.example.springbootcrudapp.entity.Book;
import com.example.springbootcrudapp.repository.AuthorRepository;
import com.example.springbootcrudapp.repository.BookRepository;
import com.example.springbootcrudapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + id));
        return convertToDTO(book);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + id));

        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + bookDTO.getAuthorId()));

        // Update fields
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setPublicationDate(bookDTO.getPublicationDate());
        existingBook.setPrice(bookDTO.getPrice());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setDescription(bookDTO.getDescription());
        existingBook.setAuthor(author);

        Book updatedBook = bookRepository.save(existingBook);
        return convertToDTO(updatedBook);
    }

    @Override
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<BookDTO> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByAuthorLastName(String lastName) {
        return bookRepository.findBooksByAuthorLastName(lastName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getBooksWithAuthors() {
        List<Object[]> results = bookRepository.findBooksWithAuthors();
        List<Map<String, Object>> booksWithAuthors = new ArrayList<>();

        for (Object[] result : results) {
            Book book = (Book) result[0];
            Author author = (Author) result[1];

            Map<String, Object> bookWithAuthor = new HashMap<>();
            bookWithAuthor.put("bookId", book.getId());
            bookWithAuthor.put("bookTitle", book.getTitle());
            bookWithAuthor.put("isbn", book.getIsbn());
            bookWithAuthor.put("genre", book.getGenre());
            bookWithAuthor.put("authorId", author.getId());
            bookWithAuthor.put("authorName", author.getFirstName() + " " + author.getLastName());

            booksWithAuthors.add(bookWithAuthor);
        }

        return booksWithAuthors;
    }

    @Override
    public BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublicationDate(book.getPublicationDate());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setDescription(book.getDescription());

        if (book.getAuthor() != null) {
            bookDTO.setAuthorId(book.getAuthor().getId());
            bookDTO.setAuthorName(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
        }

        return bookDTO;
    }

    @Override
    public Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setPrice(bookDTO.getPrice());
        book.setGenre(bookDTO.getGenre());
        book.setDescription(bookDTO.getDescription());

        if (bookDTO.getAuthorId() != null) {
            Author author = authorRepository.findById(bookDTO.getAuthorId())
                    .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + bookDTO.getAuthorId()));
            book.setAuthor(author);
        }

        return book;
    }
}