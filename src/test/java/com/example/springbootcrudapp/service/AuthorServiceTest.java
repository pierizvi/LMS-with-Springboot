package com.example.springbootcrudapp.service;

import com.example.springbootcrudapp.dto.AuthorDTO;
import com.example.springbootcrudapp.entity.Author;
import com.example.springbootcrudapp.repository.AuthorRepository;
import com.example.springbootcrudapp.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        author = new Author();
        author.setId(1L);
        author.setFirstName("Mark");
        author.setLastName("Twain");
        author.setEmail("mark.twain@example.com");
        author.setBiography("American writer and humorist.");
        author.setBirthYear(1835);

        authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setFirstName("Mark");
        authorDTO.setLastName("Twain");
        authorDTO.setEmail("mark.twain@example.com");
        authorDTO.setBiography("American writer and humorist.");
        authorDTO.setBirthYear(1835);
    }

    @Test
    public void whenGetAllAuthors_thenReturnAuthorDTOList() {
        // given
        List<Author> authors = new ArrayList<>();
        authors.add(author);

        when(authorRepository.findAll()).thenReturn(authors);

        // when
        List<AuthorDTO> authorDTOs = authorService.getAllAuthors();

        // then
        assertThat(authorDTOs).hasSize(1);
        assertThat(authorDTOs.get(0).getFirstName()).isEqualTo(author.getFirstName());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void whenGetAuthorById_thenReturnAuthorDTO() {
        // given
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        // when
        AuthorDTO found = authorService.getAuthorById(1L);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1L);
        assertThat(found.getFirstName()).isEqualTo(author.getFirstName());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    public void whenGetAuthorByIdNotFound_thenThrowException() {
        // given
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(EntityNotFoundException.class, () -> {
            authorService.getAuthorById(99L);
        });
        verify(authorRepository, times(1)).findById(99L);
    }

    @Test
    public void whenCreateAuthor_thenReturnAuthorDTO() {
        // given
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        // when
        AuthorDTO created = authorService.createAuthor(authorDTO);

        // then
        assertThat(created).isNotNull();
        assertThat(created.getFirstName()).isEqualTo(authorDTO.getFirstName());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void whenUpdateAuthor_thenReturnUpdatedAuthorDTO() {
        // given
        Author existingAuthor = new Author();
        existingAuthor.setId(1L);
        existingAuthor.setFirstName("Old Name");
        existingAuthor.setLastName("Old Last");
        existingAuthor.setEmail("old.email@example.com");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(any(Author.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        AuthorDTO updated = authorService.updateAuthor(1L, authorDTO);

        // then
        assertThat(updated).isNotNull();
        assertThat(updated.getFirstName()).isEqualTo(authorDTO.getFirstName());
        assertThat(updated.getLastName()).isEqualTo(authorDTO.getLastName());
        assertThat(updated.getEmail()).isEqualTo(authorDTO.getEmail());
        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void whenUpdateAuthorNotFound_thenThrowException() {
        // given
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(EntityNotFoundException.class, () -> {
            authorService.updateAuthor(99L, authorDTO);
        });
        verify(authorRepository, times(1)).findById(99L);
        verify(authorRepository, never()).save(any(Author.class));
    }

    @Test
    public void whenDeleteAuthor_thenReturnTrue() {
        // given
        when(authorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(authorRepository).deleteById(1L);

        // when
        boolean result = authorService.deleteAuthor(1L);

        // then
        assertThat(result).isTrue();
        verify(authorRepository, times(1)).existsById(1L);
        verify(authorRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteAuthorNotFound_thenReturnFalse() {
        // given
        when(authorRepository.existsById(99L)).thenReturn(false);

        // when
        boolean result = authorService.deleteAuthor(99L);

        // then
        assertThat(result).isFalse();
        verify(authorRepository, times(1)).existsById(99L);
        verify(authorRepository, never()).deleteById(any());
    }
}