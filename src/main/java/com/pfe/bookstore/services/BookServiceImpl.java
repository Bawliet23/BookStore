package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.DTO.GenreDTO;
import com.pfe.bookstore.entities.*;
import com.pfe.bookstore.repositories.*;
import com.pfe.bookstore.utils.FileHandler;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
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
    @Autowired
    private IClientRepository clientRepo;
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private INotificationRepository notificationRepository;
    @Autowired
    private IRateRepository rateRepository;
    @Autowired
    private  IUserRepository userRepository;

    @Override
    public Page<BookDTO> getBooksByPage(Pageable page) {
        Page<Book> books = bookRepository.findAll(page);
        Page<BookDTO> bookDtoPage = books.map(this::convertToDTO);
        return bookDtoPage;
    }

    @Override
    public Page<BookDTO> getBooksByMostSelles(Pageable page) {
        return bookRepository.findByOrderBySellesDesc(page).map(book -> modelMapper.map(book,BookDTO.class));
    }

    @Override
    public Page<BookDTO> getBooksByPrice(Pageable page) {
        return bookRepository.findByOrderByPriceDesc(page).map(book -> modelMapper.map(book,BookDTO.class));
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book =bookRepository.getOne(id);
        return convertToDTO(book);
    }

    @Override
    public void saveBook(BookDTO bookDTO, MultipartFile content, MultipartFile cover) throws IOException {

        Book book = modelMapper.map(bookDTO,Book.class);
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
        List<Auteur> auteurs = new ArrayList<>();
        auteurs.add(auteur);
        List<Client> clients =  clientRepo.findClientsByFallowsIn(auteurs);
        for (Client client : clients){
            Notification notification = new Notification();
            notification.setNotification(auteur.getUsername()+" added a new Book with the title "+book.getName());
            notification.setUser(client);
            notificationRepository.save(notification);
            webSocketService.newBookNotification(client.getId());
        }

    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.getOne(id);
        FileHandler.deleteFile(book.getContenu());
        FileHandler.deleteFile(book.getImage());
        bookRepository.delete(book);
    }

    @Override
    public Page<BookDTO> getBooksByGenre(Pageable page, List<Long> genreId) {
        List<Genre> genres= new ArrayList<>() ;
        for (Long id : genreId){
           Genre g = genreRepository.getOne(id);
           genres.add(g);
        }
        return bookRepository.findBooksByGenresIn(genres,page).map(this::convertToDTO);
    }

    @Override
    public Page<BookDTO> searchBookByName(String name,Pageable page) {
        return bookRepository.findBooksByNameContaining(page,name).map(this::convertToDTO);
    }
    @Override
    public void rateBook(Long userId, Long bookId, Long value) {
        Rate rate = new Rate();
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        rate.setUser(user);
        rate.setBook(book);
        rate.setValue(value);
        rateRepository.save(rate);
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
