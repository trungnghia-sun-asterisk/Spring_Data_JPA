package com.example.bookstore.repository;
import com.example.bookstore.entity.Book;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
public interface BookRepository extends JpaRepository<Book,Long> {
    boolean existsByIsbnIgnoreCase(String isbn); boolean existsByIsbnIgnoreCaseAndIdNot(String isbn,Long id); boolean existsByAuthorId(Long id); boolean existsByCategoryId(Long id);
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByAuthorId(Long authorId, Pageable pageable); Page<Book> findByCategoryId(Long categoryId, Pageable pageable);
    @Query("select b from Book b join b.author a where lower(b.title) like lower(concat('%', :keyword, '%')) or lower(a.name) like lower(concat('%', :keyword, '%'))")
    Page<Book> searchByTitleOrAuthor(@Param("keyword") String keyword, Pageable pageable);
}
