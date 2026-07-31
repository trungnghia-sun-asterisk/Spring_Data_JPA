package com.example.bookstore.dto;
import jakarta.validation.constraints.*;
public record AuthorRequest(@NotBlank(message="Name must not be blank") @Size(max=150) String name,@NotBlank(message="Email must not be blank") @Email(message="Email must be valid") String email,@Size(max=5000) String biography) {}
