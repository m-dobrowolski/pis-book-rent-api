package com.example.book_rent_api.controller;

import com.example.book_rent_api.dto.BookLoanDTO;
import com.example.book_rent_api.model.BookLoan;
import com.example.book_rent_api.model.LocalUser;
import com.example.book_rent_api.repository.LocalUserRepository;
import com.example.book_rent_api.service.BookLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class BookLoanController {

    @Autowired
    private BookLoanService bookLoanService;

    @Autowired
    private LocalUserRepository localUserRepository;

    @GetMapping
    public List<BookLoan> getAllLoans() {
        return bookLoanService.getAllLoans();
    }

    @PostMapping
    public BookLoan createLoan(@RequestBody BookLoanDTO bookLoanDTO) {
        return bookLoanService.processLoanRequest(bookLoanDTO);
    }

    @GetMapping("/user/{userId}")
    public List<BookLoan> getLoansByUserId(@PathVariable Long userId) {
        return bookLoanService.getLoansByUserId(userId);
    }
}
