package com.example.library.mode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookDTO {
    private Long id; // We keep this so the user knows which book is which

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Author cannot be empty")
    private String author;

    @Positive(message = "Price must be greater than zero")
    private double price;
}
