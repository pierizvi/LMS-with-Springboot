package com.example.springbootcrudapp.service;

import com.example.springbootcrudapp.dto.AuthorDTO;
import com.example.springbootcrudapp.entity.Author;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> getAllAuthors();

    AuthorDTO getAuthorById(Long id);

    AuthorDTO createAuthor(AuthorDTO authorDTO);

    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);

    boolean deleteAuthor(Long id);

    // Convert entities to DTOs and vice versa
    AuthorDTO convertToDTO(Author author);

    Author convertToEntity(AuthorDTO authorDTO);
}