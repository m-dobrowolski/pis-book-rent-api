package com.example.book_rent_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class LocalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<BookLoan> bookLoans;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<BookLoan> getBookLoans() { return bookLoans; }
    public void setBookLoans(List<BookLoan> bookLoans) { this.bookLoans = bookLoans; }
}
