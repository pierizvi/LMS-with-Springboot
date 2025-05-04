package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Find authors by last name
    List<Author> findByLastName(String lastName);

    // Find authors by email
    Author findByEmail(String email);

    // Find authors born after a certain year
    List<Author> findByBirthYearGreaterThan(Integer year);
}