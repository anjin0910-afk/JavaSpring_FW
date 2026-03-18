package com.rookies5.MySpringbootLab.controller;

import com.rookies5.MySpringbootLab.dto.BookDTO;
import com.rookies5.MySpringbootLab.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO.Response> createBook(@Valid @RequestBody BookDTO.Request request) {
        BookDTO.Response response = bookService.createBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO.Response>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO.Response> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO.Response> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<BookDTO.Response>> searchBooksByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(bookService.searchBooksByAuthor(author));
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<BookDTO.Response>> searchBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchBooksByTitle(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO.Response> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO.Request request) {
        BookDTO.Response response = bookService.updateBook(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
