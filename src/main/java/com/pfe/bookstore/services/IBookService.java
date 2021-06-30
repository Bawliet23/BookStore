package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBookService {
    Page<BookDTO> getBooksByPage(Pageable page);
    BookDTO getBookById(Long id);
    void saveBook(BookDTO bookDTO, MultipartFile content,MultipartFile cover) throws IOException;
    void deleteBook(Long id);
    Page<BookDTO> getBooksByGenre(Pageable page, List<Long> genreId);
    Page<BookDTO> searchBookByName(String name,Pageable page);
    void rateBook(Long userId,Long bookId,Long value);
}
