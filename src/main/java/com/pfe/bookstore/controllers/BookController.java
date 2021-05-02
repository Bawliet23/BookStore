package com.pfe.bookstore.controllers;

import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.services.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("/all")
    public ResponseEntity<Page<BookDTO>> getBooks(@PageableDefault(size = 10)Pageable page){
        return ResponseEntity.ok()
                .body(bookService.getBooksByPage(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id){
        return ResponseEntity.ok()
                .body(bookService.getBookById(id));
    }

}
