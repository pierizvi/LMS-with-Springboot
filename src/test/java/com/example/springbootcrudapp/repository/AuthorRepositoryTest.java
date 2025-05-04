package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void whenFindByLastName_thenReturnAuthor() {
        // given
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setEmail("john.doe@example.com");
        author.setBirthYear(1980);
        entityManager.persist(author);
        entityManager.flush();

        // when
        List<Author> found = authorRepository.findByLastName("Doe");

        // then
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getFirstName()).isEqualTo(author.getFirstName());
        assertThat(found.get(0).getLastName()).isEqualTo(author.getLastName());
    }

    @Test
    public void whenFindByEmail_thenReturnAuthor() {
        // given
        Author author = new Author();
        author.setFirstName("Jane");
        author.setLastName("Smith");
        author.setEmail("jane.smith@example.com");
        author.setBirthYear(1985);
        entityManager.persist(author);
        entityManager.flush();

        // when
        Author found = authorRepository.findByEmail("jane.smith@example.com");

        // then
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo(author.getEmail());
    }

    @Test
    public void whenFindByBirthYearGreaterThan_thenReturnAuthors() {
        // given
        Author author1 = new Author();
        author1.setFirstName("Bob");
        author1.setLastName("Johnson");
        author1.setEmail("bob.johnson@example.com");
        author1.setBirthYear(1990);
        entityManager.persist(author1);

        Author author2 = new Author();
        author2.setFirstName("Alice");
        author2.setLastName("Brown");
        author2.setEmail("alice.brown@example.com");
        author2.setBirthYear(1970);
        entityManager.persist(author2);

        entityManager.flush();

        // when
        List<Author> found = authorRepository.findByBirthYearGreaterThan(1980);

        // then
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getFirstName()).isEqualTo(author1.getFirstName());
    }
}