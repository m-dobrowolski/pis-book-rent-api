package com.example.book_rent_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class LocalBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BookLoan> bookLoans;

    // Getters and Setters
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public List<BookLoan> getBookLoans() { return bookLoans; }
    public void setBookLoans(List<BookLoan> bookLoans) { this.bookLoans = bookLoans; }
}
