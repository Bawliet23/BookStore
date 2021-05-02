package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
    Page<BookDTO> getBooksByPage(Pageable page);
    BookDTO getBookById(Long id);
}
