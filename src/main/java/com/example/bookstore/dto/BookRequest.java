package com.example.bookstore.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal; import java.time.LocalDate;
public record BookRequest(@NotBlank(message="Title must not be blank") String title,@NotBlank(message="ISBN must not be blank") String isbn,@NotNull(message="Price is required") @Positive(message="Price must be greater than 0") BigDecimal price,@NotNull(message="Stock is required") @PositiveOrZero(message="Stock must be zero or greater") Integer stock,LocalDate publishedDate,@NotNull(message="Author id is required") Long authorId,@NotNull(message="Category id is required") Long categoryId) {}
