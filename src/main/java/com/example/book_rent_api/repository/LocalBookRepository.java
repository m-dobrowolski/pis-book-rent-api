package com.example.book_rent_api.repository;

import com.example.book_rent_api.model.LocalBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalBookRepository extends JpaRepository<LocalBook, Long> {
}
