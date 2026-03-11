package com.rookies5.MySpringbootLab.repository;

import com.rookies5.MySpringbootLab.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("도서 등록 테스트")
    public void testCreateBook() {
        // given
        Book book = Book.builder()
                .title("스프링 부트 입문")
                .author("홍길동")
                .isbn("9788956746425")
                .price(30000)
                .publishDate(LocalDate.of(2025, 5, 7))
                .build();

        // when
        Book savedBook = bookRepository.save(book);

        // then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isGreaterThan(0L);
        assertThat(savedBook.getTitle()).isEqualTo("스프링 부트 입문");
        assertThat(savedBook.getIsbn()).isEqualTo("9788956746425");
    }

    @Test
    @DisplayName("ISBN으로 도서 조회 테스트")
    public void testFindByIsbn() {
        // given
        Book book = Book.builder()
                .title("JPA 프로그래밍")
                .author("박둘리")
                .isbn("9788956746432")
                .price(35000)
                .publishDate(LocalDate.of(2025, 4, 30))
                .build();
        bookRepository.save(book);

        // when
        Optional<Book> foundBook = bookRepository.findByIsbn("9788956746432");

        // then
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getAuthor()).isEqualTo("박둘리");
    }

    @Test
    @DisplayName("저자명으로 도서 목록 조회 테스트")
    public void testFindByAuthor() {
        // given
        Book book1 = Book.builder()
                .title("스프링 부트 입문")
                .author("홍길동")
                .isbn("9788956746425")
                .price(30000)
                .publishDate(LocalDate.of(2025, 5, 7))
                .build();
        Book book2 = Book.builder()
                .title("스프링 완전 정복")
                .author("홍길동")
                .isbn("9788911111111")
                .price(40000)
                .publishDate(LocalDate.of(2025, 6, 1))
                .build();
        bookRepository.save(book1);
        bookRepository.save(book2);

        // when
        List<Book> books = bookRepository.findByAuthor("홍길동");

        // then
        assertThat(books).hasSize(2);
        assertThat(books).extracting(Book::getTitle).contains("스프링 부트 입문", "스프링 완전 정복");
    }

    @Test
    @DisplayName("도서 정보 수정 테스트")
    public void testUpdateBook() {
        // given
        Book book = Book.builder()
                .title("스프링 부트 입문")
                .author("홍길동")
                .isbn("9788956746425")
                .price(30000)
                .publishDate(LocalDate.of(2025, 5, 7))
                .build();
        Book savedBook = bookRepository.save(book);

        // when
        savedBook.setPrice(35000); // 30000 -> 35000 수정
        Book updatedBook = bookRepository.save(savedBook);

        // then
        assertThat(updatedBook.getPrice()).isEqualTo(35000);
        
        Optional<Book> foundBook = bookRepository.findById(updatedBook.getId());
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getPrice()).isEqualTo(35000);
    }

    @Test
    @DisplayName("도서 삭제 테스트")
    public void testDeleteBook() {
        // given
        Book book = Book.builder()
                .title("JPA 프로그래밍")
                .author("박둘리")
                .isbn("9788956746432")
                .price(35000)
                .publishDate(LocalDate.of(2025, 4, 30))
                .build();
        Book savedBook = bookRepository.save(book);
        Long bookId = savedBook.getId();

        // when
        bookRepository.deleteById(bookId);

        // then
        Optional<Book> deletedBook = bookRepository.findById(bookId);
        assertThat(deletedBook).isEmpty();
    }
}
