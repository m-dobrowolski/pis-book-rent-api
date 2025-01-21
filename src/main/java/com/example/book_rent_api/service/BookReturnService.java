package com.example.book_rent_api.service;

import com.example.book_rent_api.model.BookLoan;
import com.example.book_rent_api.model.LocalBook;
import com.example.book_rent_api.repository.BookLoanRepository;
import com.example.book_rent_api.repository.LocalBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookReturnService {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private LocalBookRepository localBookRepository;

    public ResponseEntity<?> processBookReturn(Long bookId) {
        Optional<LocalBook> bookOpt = localBookRepository.findById(bookId);
        if (bookOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }

        LocalBook book = bookOpt.get();
        Optional<BookLoan> bookLoanOpt = bookLoanRepository.findByBookAndReturnDateIsNull(book);
        if (bookLoanOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No active loan found for this book");
        }

        BookLoan bookLoan = bookLoanOpt.get();
        bookLoan.setReturnDate(LocalDate.now());
        bookLoanRepository.save(bookLoan);

        return ResponseEntity.ok("Book successfully returned on " + bookLoan.getReturnDate());
    }
}
