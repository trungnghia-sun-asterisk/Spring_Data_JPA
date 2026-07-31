package com.example.bookstore.dto;
import jakarta.validation.constraints.*;
public record CategoryRequest(@NotBlank(message="Name must not be blank") @Size(max=100) String name,@Size(max=500) String description) {}
