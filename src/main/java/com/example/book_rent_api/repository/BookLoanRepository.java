package com.example.book_rent_api.repository;

import com.example.book_rent_api.model.BookLoan;
import com.example.book_rent_api.model.LocalBook;
import com.example.book_rent_api.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    boolean existsByBookAndReturnDateIsNull(LocalBook book);
    Optional<BookLoan> findByBookAndReturnDateIsNull(LocalBook book);
    List<BookLoan> findByUser(LocalUser user);
}
