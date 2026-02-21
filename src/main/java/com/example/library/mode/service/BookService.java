package com.example.library.mode.service;

import com.example.library.mode.dto.BookDTO;
import com.example.library.mode.model.Book;
import com.example.library.mode.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Tells Spring: "Manage this class as a Bean!"
public class BookService {

    // 1. Dependency Injection (DI)
    // We 'final' this to ensure it's initialized via constructor
    private final BookRepository bookRepository;

    // Spring sees this constructor and automatically injects the BookRepository bean
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 2. Bean Lifecycle Hook
    @PostConstruct
    public void seedDatabase() {
        System.out.println("🌱 Bean Lifecycle: BookService is initialized. Seeding data...");
        bookRepository.save(new Book(null, "Clean Code", "Robert Martin", "12345", 45.0, "Classic must-read"));
        bookRepository.save(new Book(null, "Spring Start Here", "Laurentiu Spilca", "67890", 35.0, "Great for beginners"));
    }

    // 3. Business Logic: Get all books and convert to DTOs
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Manual Mapping Logic (Understanding the "Why" of DTOs)
    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        return dto;
    }
}
