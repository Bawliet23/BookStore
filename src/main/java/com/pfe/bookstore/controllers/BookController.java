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
import java.util.List;

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
    @GetMapping("/genres")
    public ResponseEntity<Page<BookDTO>> getBookByGenre(@PageableDefault(size = 10)Pageable page, @RequestParam List<Long> genresId){
        return ResponseEntity.ok()
                .body(bookService.getBooksByGenre(page, genresId));
    }
        @PostMapping("/addBook")
    public ResponseEntity<Void> addBook(@RequestParam("content") MultipartFile book,@RequestParam("cover") MultipartFile cover,@RequestParam("book") String  bookDTO) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BookDTO bookDTO1 = objectMapper.readValue(bookDTO,BookDTO.class);
        bookService.saveBook(bookDTO1,book,cover);
        return ResponseEntity.ok().build();
    }
//@PostMapping("/addBook")
//    public ResponseEntity<Void> addBook(@RequestParam("content") MultipartFile book,@RequestParam("cover") MultipartFile cover,@RequestBody BookDTO  bookDTO) throws IOException {
//        bookService.saveBook(bookDTO,book,cover);
//     return ResponseEntity.ok().build();
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
