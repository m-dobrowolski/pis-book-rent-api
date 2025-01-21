package com.example.book_rent_api.controller;

import com.example.book_rent_api.service.BookReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/return")
public class BookReturnController {

    @Autowired
    private BookReturnService bookReturnService;

    // Endpoint to return a book
    @PutMapping("/{bookId}/")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId) {
        return bookReturnService.processBookReturn(bookId);
    }
}
