package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.Author;
import com.example.springbootcrudapp.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindByTitleContaining_thenReturnBook() {
        // given
        Author author = new Author();
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setEmail("stephen.king@example.com");
        entityManager.persist(author);

        Book book = new Book();
        book.setTitle("The Shining");
        book.setIsbn("1234567890");
        book.setGenre("Horror");
        book.setAuthor(author);
        entityManager.persist(book);
        entityManager.flush();

        // when
        List<Book> found = bookRepository.findByTitleContaining("Shining");

        // then
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getTitle()).isEqualTo(book.getTitle());
    }

    @Test
    public void whenFindByGenre_thenReturnBooks() {
        // given
        Author author = new Author();
        author.setFirstName("J.R.R.");
        author.setLastName("Tolkien");
        author.setEmail("jrr.tolkien@example.com");
        entityManager.persist(author);

        Book book1 = new Book();
        book1.setTitle("The Hobbit");
        book1.setIsbn("1234567891");
        book1.setGenre("Fantasy");
        book1.setAuthor(author);
        entityManager.persist(book1);

        Book book2 = new Book();
        book2.setTitle("The Lord of the Rings");
        book2.setIsbn("1234567892");
        book2.setGenre("Fantasy");
        book2.setAuthor(author);
        entityManager.persist(book2);

        Book book3 = new Book();
        book3.setTitle("On Writing");
        book3.setIsbn("1234567893");
        book3.setGenre("Non-Fiction");
        book3.setAuthor(author);
        entityManager.persist(book3);

        entityManager.flush();

        // when
        List<Book> found = bookRepository.findByGenre("Fantasy");

        // then
        assertThat(found).hasSize(2);
        assertThat(found.get(0).getGenre()).isEqualTo("Fantasy");
        assertThat(found.get(1).getGenre()).isEqualTo("Fantasy");
    }

    @Test
    public void whenFindByAuthorId_thenReturnBooks() {
        // given
        Author author1 = new Author();
        author1.setFirstName("George");
        author1.setLastName("Orwell");
        author1.setEmail("george.orwell@example.com");
        entityManager.persist(author1);

        Author author2 = new Author();
        author2.setFirstName("Jane");
        author2.setLastName("Austen");
        author2.setEmail("jane.austen@example.com");
        entityManager.persist(author2);

        Book book1 = new Book();
        book1.setTitle("1984");
        book1.setIsbn("1234567894");
        book1.setGenre("Dystopian");
        book1.setAuthor(author1);
        entityManager.persist(book1);

        Book book2 = new Book();
        book2.setTitle("Animal Farm");
        book2.setIsbn("1234567895");
        book2.setGenre("Political Satire");
        book2.setAuthor(author1);
        entityManager.persist(book2);

        Book book3 = new Book();
        book3.setTitle("Pride and Prejudice");
        book3.setIsbn("1234567896");
        book3.setGenre("Romance");
        book3.setAuthor(author2);
        entityManager.persist(book3);

        entityManager.flush();

        // when
        List<Book> found = bookRepository.findByAuthorId(author1.getId());

        // then
        assertThat(found).hasSize(2);
        assertThat(found.get(0).getAuthor().getId()).isEqualTo(author1.getId());
        assertThat(found.get(1).getAuthor().getId()).isEqualTo(author1.getId());
    }

    @Test
    public void whenFindBooksByAuthorLastName_thenReturnBooks() {
        // given
        Author author = new Author();
        author.setFirstName("Agatha");
        author.setLastName("Christie");
        author.setEmail("agatha.christie@example.com");
        entityManager.persist(author);

        Book book1 = new Book();
        book1.setTitle("Murder on the Orient Express");
        book1.setIsbn("1234567897");
        book1.setGenre("Mystery");
        book1.setAuthor(author);
        entityManager.persist(book1);

        Book book2 = new Book();
        book2.setTitle("Death on the Nile");
        book2.setIsbn("1234567898");
        book2.setGenre("Mystery");
        book2.setAuthor(author);
        entityManager.persist(book2);

        entityManager.flush();

        // when
        List<Book> found = bookRepository.findBooksByAuthorLastName("Christie");

        // then
        assertThat(found).hasSize(2);
        assertThat(found.get(0).getAuthor().getLastName()).isEqualTo("Christie");
        assertThat(found.get(1).getAuthor().getLastName()).isEqualTo("Christie");
    }

    @Test
    public void whenFindBooksWithAuthors_thenReturnBooksAndAuthors() {
        // given
        Author author = new Author();
        author.setFirstName("Ernest");
        author.setLastName("Hemingway");
        author.setEmail("ernest.hemingway@example.com");
        entityManager.persist(author);

        Book book = new Book();
        book.setTitle("The Old Man and the Sea");
        book.setIsbn("1234567899");
        book.setGenre("Literary Fiction");
        book.setPublicationDate(LocalDate.of(1952, 9, 1));
        book.setPrice(new BigDecimal("10.99"));
        book.setAuthor(author);
        entityManager.persist(book);

        entityManager.flush();

        // when
        List<Object[]> results = bookRepository.findBooksWithAuthors();

        // then
        assertThat(results).isNotEmpty();

        Object[] result = results.get(0);
        Book foundBook = (Book) result[0];
        Author foundAuthor = (Author) result[1];

        assertThat(foundBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(foundAuthor.getLastName()).isEqualTo(author.getLastName());
    }
}