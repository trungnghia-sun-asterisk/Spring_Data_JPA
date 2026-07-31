package com.example.bookstore.repository;
import com.example.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AuthorRepository extends JpaRepository<Author,Long> { boolean existsByEmailIgnoreCase(String email); boolean existsByEmailIgnoreCaseAndIdNot(String email,Long id); }
