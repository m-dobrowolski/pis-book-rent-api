package com.example.book_rent_api.service;

import com.example.book_rent_api.dto.BookLoanDTO;
import com.example.book_rent_api.model.BookLoan;
import com.example.book_rent_api.model.LocalBook;
import com.example.book_rent_api.model.LocalUser;
import com.example.book_rent_api.repository.BookLoanRepository;
import com.example.book_rent_api.repository.LocalBookRepository;
import com.example.book_rent_api.repository.LocalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookLoanService {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private LocalUserRepository localUserRepository;

    @Autowired
    private LocalBookRepository localBookRepository;

    public List<BookLoan> getAllLoans() {
        return bookLoanRepository.findAll();
    }

    public BookLoan processLoanRequest(BookLoanDTO bookLoanDTO) {
        LocalUser user = localUserRepository.findById(bookLoanDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalBook book = localBookRepository.findById(bookLoanDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Create and save loan
        BookLoan bookLoan = new BookLoan();
        bookLoan.setUser(user);
        bookLoan.setBook(book);
        bookLoan.setDateFrom(bookLoanDTO.getDateFrom());
        bookLoan.setDateTo(bookLoanDTO.getDateTo());

        return bookLoanRepository.save(bookLoan);
    }
}
