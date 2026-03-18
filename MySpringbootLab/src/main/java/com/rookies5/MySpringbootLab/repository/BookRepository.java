package com.rookies5.MySpringbootLab.repository;

import com.rookies5.MySpringbootLab.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    
    Optional<Book> findByIsbn(String isbn);
    
    List<Book> findByAuthor(String author);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookDetail bd WHERE b.id = :id")
    Optional<Book> findByIdWithBookDetail(@Param("id") Long id);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookDetail bd WHERE b.isbn = :isbn")
    Optional<Book> findByIsbnWithBookDetail(@Param("isbn") String isbn);

    boolean existsByIsbn(String isbn);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByTitleContainingIgnoreCase(String title);
}
