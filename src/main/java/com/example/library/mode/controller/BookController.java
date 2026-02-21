package com.example.library.mode.controller;

import com.example.library.mode.dto.BookDTO;
import com.example.library.mode.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Combines @Controller and @ResponseBody
@RequestMapping("/api/books") // Base URL for all endpoints in this class
public class BookController {

    private final BookService bookService;

    // Spring injects the BookService bean here (DI in action again!)
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET: http://localhost:8080/api/books
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    // POST: http://localhost:8080/api/books
    @PostMapping
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO bookDTO) {
        // In a full app, you'd call bookService.save(bookDTO)
        // For now, let's just confirm the validation worked
        return new ResponseEntity<>("Book '" + bookDTO.getTitle() + "' is valid and ready to be saved!", HttpStatus.CREATED);
    }


}
