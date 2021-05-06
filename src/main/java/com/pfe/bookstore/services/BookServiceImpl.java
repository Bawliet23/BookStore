package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.DTO.GenreDTO;
import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.entities.Genre;
import com.pfe.bookstore.repositories.IAuteurRepository;
import com.pfe.bookstore.repositories.IBookRepository;
import com.pfe.bookstore.repositories.IGenreRepository;
import com.pfe.bookstore.repositories.IUserRepository;
import com.pfe.bookstore.utils.FileHandler;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements IBookService {
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private IAuteurRepository auteurRepository;
    @Autowired
    private IGenreRepository genreRepository;
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

    @Override
    public void saveBook(BookDTO bookDTO, MultipartFile content, MultipartFile cover) throws IOException {

        Book book = convertToEntity(bookDTO);
         book.setContenu(FileHandler.uploadFile(content));
         book.setImage(FileHandler.uploadFile(cover));
        Auteur auteur = auteurRepository.getOne(book.getAuteur().getId());
        ArrayList<Genre> genres = new ArrayList<>();
        for (GenreDTO genreDTO : bookDTO.getGenres()){
            Genre genre = genreRepository.getOne(genreDTO.getId());
            genres.add(genre);
        }
        book.setGenres(genres);
        book.setAuteur(auteur);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.getOne(id);
        FileHandler.deleteFile(book.getContenu());
        FileHandler.deleteFile(book.getImage());
        bookRepository.delete(book);
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
