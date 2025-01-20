package com.example.book_rent_api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private LocalBook book;

    private LocalDate dateFrom;
    private LocalDate dateTo;

    // Getters and Setters
    public Long getLoanId() { return loanId; }
    public void setLoanId(Long loanId) { this.loanId = loanId; }

    public LocalUser getUser() { return user; }
    public void setUser(LocalUser user) { this.user = user; }

    public LocalBook getBook() { return book; }
    public void setBook(LocalBook book) { this.book = book; }

    public LocalDate getDateFrom() { return dateFrom; }
    public void setDateFrom(LocalDate dateFrom) { this.dateFrom = dateFrom; }

    public LocalDate getDateTo() { return dateTo; }
    public void setDateTo(LocalDate dateTo) { this.dateTo = dateTo; }
}
