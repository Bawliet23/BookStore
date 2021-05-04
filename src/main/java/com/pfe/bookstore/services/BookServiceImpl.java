package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.DTO.GenreDTO;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.entities.Genre;
import com.pfe.bookstore.repositories.IBookRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements IBookService {
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<BookDTO> getBooksByPage(Pageable page) {
        Page<Book> books = bookRepository.findAll(page);
        Page<BookDTO> bookDtoPage = books.map(this::convertToDTO);
        return bookDtoPage;
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book =bookRepository.getOne(id);
        return convertToDTO(book);
    }

    private Book convertToEntity (BookDTO bookDTO)
    {

        Book order = modelMapper.map(bookDTO, Book.class);

        return order;
    }

    private BookDTO convertToDTO (Book book)
    {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;
    }

}
