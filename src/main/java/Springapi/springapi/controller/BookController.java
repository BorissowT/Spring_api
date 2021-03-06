package Springapi.springapi.controller;

import Springapi.springapi.entity.BookModel;
import Springapi.springapi.exception.ResourceNotFoundException;
import Springapi.springapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<Object> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity getBookById(@PathVariable(value = "id") Long bookId)
            throws ResourceNotFoundException {
        BookModel book =
                bookRepository
                        .findById(bookId)
                        .orElseThrow(() -> new ResourceNotFoundException("Book not found on :: " + bookId));
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/books")
    public BookModel createBook(@Valid @RequestBody BookModel book,
                                @AuthenticationPrincipal Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }
        return bookRepository.save(book);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Long bookId,
                                           @AuthenticationPrincipal Principal principal) throws Exception {
        if (principal == null) {
            throw new ForbiddenException();
        }

        BookModel book =
                bookRepository
                        .findById(bookId)
                        .orElseThrow(() -> new ResourceNotFoundException("Book not found on :: " + bookId));

        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
