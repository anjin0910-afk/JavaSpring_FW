package com.rookies5.MySpringbootLab.service;

import com.rookies5.MySpringbootLab.dto.BookDTO;
import com.rookies5.MySpringbootLab.entity.Book;
import com.rookies5.MySpringbootLab.entity.BookDetail;
import com.rookies5.MySpringbootLab.exception.BusinessException;
import com.rookies5.MySpringbootLab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookDTO.Response createBook(BookDTO.Request request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new BusinessException("이미 존재하는 ISBN입니다. (ISBN: " + request.getIsbn() + ")", HttpStatus.BAD_REQUEST);
        }

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .publishDate(request.getPublishDate())
                .build();
        
        if (request.getDetailRequest() != null) {
            BookDetail bookDetail = BookDetail.builder()
                    .description(request.getDetailRequest().getDescription())
                    .language(request.getDetailRequest().getLanguage())
                    .pageCount(request.getDetailRequest().getPageCount())
                    .publisher(request.getDetailRequest().getPublisher())
                    .coverImageUrl(request.getDetailRequest().getCoverImageUrl())
                    .edition(request.getDetailRequest().getEdition())
                    .book(book)
                    .build();
            book.setBookDetail(bookDetail);
        }
        
        Book savedBook = bookRepository.save(book);
        return BookDTO.Response.fromEntity(savedBook);
    }

    public List<BookDTO.Response> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDTO.Response::fromEntity)
                .collect(Collectors.toList());
    }

    public BookDTO.Response getBookById(Long id) {
        Book book = bookRepository.findByIdWithBookDetail(id)
                .orElseThrow(() -> new BusinessException("도서를 찾을 수 없습니다. (ID: " + id + ")", HttpStatus.NOT_FOUND));
        return BookDTO.Response.fromEntity(book);
    }

    public BookDTO.Response getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbnWithBookDetail(isbn)
                .orElseThrow(() -> new BusinessException("도서를 찾을 수 없습니다. (ISBN: " + isbn + ")", HttpStatus.NOT_FOUND));
        return BookDTO.Response.fromEntity(book);
    }
    
    public List<BookDTO.Response> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author).stream()
                .map(BookDTO.Response::fromEntity)
                .collect(Collectors.toList());
    }

    public List<BookDTO.Response> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(BookDTO.Response::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookDTO.Response updateBook(Long id, BookDTO.Request request) {
        Book existBook = bookRepository.findByIdWithBookDetail(id)
                .orElseThrow(() -> new BusinessException("도서를 찾을 수 없습니다. (ID: " + id + ")", HttpStatus.NOT_FOUND));

        if (request.getTitle() != null) existBook.setTitle(request.getTitle());
        if (request.getAuthor() != null) existBook.setAuthor(request.getAuthor());
        if (request.getIsbn() != null) {
            // Check if new ISBN is different and already exists
            if (!existBook.getIsbn().equals(request.getIsbn()) && bookRepository.existsByIsbn(request.getIsbn())) {
                throw new BusinessException("이미 존재하는 ISBN입니다. (ISBN: " + request.getIsbn() + ")", HttpStatus.BAD_REQUEST);
            }
            existBook.setIsbn(request.getIsbn());
        }
        if (request.getPrice() != null) existBook.setPrice(request.getPrice());
        if (request.getPublishDate() != null) existBook.setPublishDate(request.getPublishDate());

        if (request.getDetailRequest() != null) {
            BookDetail detail = existBook.getBookDetail();
            BookDTO.BookDetailDTO detailReq = request.getDetailRequest();
            
            if (detail == null) {
                detail = BookDetail.builder().book(existBook).build();
                existBook.setBookDetail(detail);
            }
            
            if (detailReq.getDescription() != null) detail.setDescription(detailReq.getDescription());
            if (detailReq.getLanguage() != null) detail.setLanguage(detailReq.getLanguage());
            if (detailReq.getPageCount() != null) detail.setPageCount(detailReq.getPageCount());
            if (detailReq.getPublisher() != null) detail.setPublisher(detailReq.getPublisher());
            if (detailReq.getCoverImageUrl() != null) detail.setCoverImageUrl(detailReq.getCoverImageUrl());
            if (detailReq.getEdition() != null) detail.setEdition(detailReq.getEdition());
        }

        return BookDTO.Response.fromEntity(existBook);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("도서를 찾을 수 없습니다. (ID: " + id + ")", HttpStatus.NOT_FOUND));
        bookRepository.delete(existBook);
    }
}
