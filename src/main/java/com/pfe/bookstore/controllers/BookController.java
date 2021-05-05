package com.pfe.bookstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.services.IBookService;
import com.pfe.bookstore.utils.FileHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    @PostMapping("/addBook")
    public void addBook(@RequestParam("content") MultipartFile book,@RequestParam("cover") MultipartFile cover,@RequestBody BookDTO bookDTO) throws IOException {
        bookService.saveBook(bookDTO,book,cover);
    }
}
