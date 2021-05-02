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
        Page<BookDTO> bookDtoPage =new Page<BookDTO>() {
            @Override
            public int getTotalPages() {
                return books.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return books.getTotalElements();
            }

            @Override
            public <U> Page<U> map(Function<? super BookDTO, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return books.getNumber();
            }

            @Override
            public int getSize() {
                return books.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return books.getNumberOfElements();
            }

            @Override
            public List<BookDTO> getContent() {
                return books.getContent().stream().map(book -> convertToDTO(book)).collect(Collectors.toList());
            }

            @Override
            public boolean hasContent() {
                return books.hasContent();
            }

            @Override
            public Sort getSort() {
                return books.getSort();
            }

            @Override
            public boolean isFirst() {
                return books.isFirst();
            }

            @Override
            public boolean isLast() {
                return books.isLast();
            }

            @Override
            public boolean hasNext() {
                return books.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return books.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return books.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return books.previousPageable();
            }

            @Override
            public Iterator<BookDTO> iterator() {
                return null;
            }
        };
        System.out.println(bookDtoPage.getContent().get(0));
        return bookDtoPage;
    }

    @Override
    public BookDTO getBookById(Long id) {
        return convertToDTO(bookRepository.getOne(id));
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
