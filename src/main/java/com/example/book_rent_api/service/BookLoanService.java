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
                .orElseThrow(() -> new IllegalStateException("User not found"));

        LocalBook book = localBookRepository.findById(bookLoanDTO.getBookId())
                .orElseThrow(() -> new IllegalStateException("Book not found"));

        if (bookLoanRepository.existsByBookAndReturnDateIsNull(book)) {
            throw new IllegalStateException("This book is already loaned and cannot be rented at the same time.");
        }

        // Create and save loan
        BookLoan bookLoan = new BookLoan();
        bookLoan.setUser(user);
        bookLoan.setBook(book);
        bookLoan.setDateFrom(bookLoanDTO.getDateFrom());
        bookLoan.setDateTo(bookLoanDTO.getDateTo());

        return bookLoanRepository.save(bookLoan);
    }
}
//@Service
//public class BookLoanService {
//
//    @Autowired
//    private LocalUserRepository localUserRepository;
//
//    @Autowired
//    private LocalBookRepository localBookRepository;
//
//    @Autowired
//    private BookLoanRepository bookLoanRepository;
//
//    public ResponseEntity<?> processLoanRequest(BookLoanDTO bookLoanDTO) {
//        Optional<LocalUser> userOpt = localUserRepository.findById(bookLoanDTO.getUserId());
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        Optional<LocalBook> bookOpt = localBookRepository.findById(bookLoanDTO.getBookId());
//        if (bookOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
//        }
//
//        LocalUser user = userOpt.get();
//        LocalBook book = bookOpt.get();
//
//        if (bookLoanRepository.existsByBookAndReturnDateIsNull(book)) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body("This book is already loaned and cannot be rented at the same time.");
//        }
//
//        // Create and save loan
//        BookLoan bookLoan = new BookLoan();
//        bookLoan.setUser(user);
//        bookLoan.setBook(book);
//        bookLoan.setDateFrom(bookLoanDTO.getDateFrom());
//        bookLoan.setDateTo(bookLoanDTO.getDateTo());
//
//        BookLoan savedLoan = bookLoanRepository.save(bookLoan);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedLoan);
//    }
//}
