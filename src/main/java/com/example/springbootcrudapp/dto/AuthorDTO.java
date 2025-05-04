package com.example.springbootcrudapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String biography;

    @Min(value = 1800, message = "Birth year must be after 1800")
    @Max(value = 2023, message = "Birth year cannot be in the future")
    private Integer birthYear;

    // Helper method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}