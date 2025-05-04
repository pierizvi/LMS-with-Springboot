package com.example.springbootcrudapp.controller;

import com.example.springbootcrudapp.dto.AuthorDTO;
import com.example.springbootcrudapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Display list of all authors
    @GetMapping
    public String getAllAuthors(Model model) {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "author/list";
    }

    // Display form to create a new author
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new AuthorDTO());
        return "author/form";
    }

    // Handle author creation
    @PostMapping
    public String createAuthor(@Valid @ModelAttribute("author") AuthorDTO authorDTO,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "author/form";
        }

        try {
            AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Author created successfully!");
            return "redirect:/authors";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating author: " + e.getMessage());
            return "redirect:/authors/new";
        }
    }

    // Display form to edit an author
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            AuthorDTO authorDTO = authorService.getAuthorById(id);
            model.addAttribute("author", authorDTO);
            return "author/form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/authors";
        }
    }

    // Handle author update
    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @Valid @ModelAttribute("author") AuthorDTO authorDTO,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "author/form";
        }

        try {
            AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully!");
            return "redirect:/authors";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating author: " + e.getMessage());
            return "redirect:/authors/edit/" + id;
        }
    }

    // Display author details
    @GetMapping("/{id}")
    public String getAuthorById(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            AuthorDTO authorDTO = authorService.getAuthorById(id);
            model.addAttribute("author", authorDTO);
            return "author/details";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/authors";
        }
    }
}