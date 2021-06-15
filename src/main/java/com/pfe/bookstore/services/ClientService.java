package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.DTO.CartDTO;
import com.pfe.bookstore.DTO.ClientDTO;
import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.entities.Cart;
import com.pfe.bookstore.entities.Client;
import com.pfe.bookstore.repositories.IAuteurRepository;
import com.pfe.bookstore.repositories.IBookRepository;
import com.pfe.bookstore.repositories.ICartRepository;
import com.pfe.bookstore.repositories.IClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ClientService implements IClientService{
    @Autowired
    private IClientRepository clientRepo;
    @Autowired
    private IAuteurRepository auteurRepository;
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WebSocketService webSocketService;

    @Override
    public Client addClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        Cart cart = new Cart();
        Cart cart1 = cartRepository.save(cart);
        Client client1 = clientRepo.save(client);
        cart1.setClient(client1);
        client1.setCart(cart);
        return client1;
    }

    @Override
    public Book addToCart(Long id, Long bookId){
        Client client = clientRepo.getOne(id);
        Book book= bookRepository.getOne(bookId);
        if (!client.getCart().getBooks().contains(book)){
            client.getCart().getBooks().add(book);
            clientRepo.save(client);
        }
          return book;
    }

    @Override
    public Book addToWishList(Long id, Long bookId) {
        Client client = clientRepo.getOne(id);
        Book book= bookRepository.getOne(bookId);
       if (!client.getWishList().contains(book)){
           client.getWishList().add(book);
           clientRepo.save(client);
       }
       return book;
    }

    @Override
    public CartDTO getCart(Long id) {
        Client client = clientRepo.getOne(id);
        return modelMapper.map(client.getCart(),CartDTO.class);
    }

    @Override
    public List<BookDTO> getWishList(Long id) {
        Client client = clientRepo.getOne(id);
        return client.getWishList().stream().map(book -> modelMapper.map(book,BookDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean follow(Long id, Long auteurId) {
        Client client = clientRepo.getOne(id);
        Auteur auteur = auteurRepository.getOne(auteurId);
        if (!client.getFallows().contains(auteur)){
            client.getFallows().add(auteur);
            webSocketService.followNotification(auteurId,client.getUsername()+" follows You. ");
            clientRepo.save(client);
            return true;
        }
        return false;
    }

    @Override
    public List<AuteurDTO> getFollows(Long id) {
        Client client = clientRepo.getOne(id);
        return client.getFallows().stream().map(auteur -> modelMapper.map(auteur,AuteurDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean emptyCart(Long id) {
        Client client = clientRepo.getOne(id);
        client.getCart().getBooks().clear();
        clientRepo.save(client);
        return client.getCart().getBooks().isEmpty();
    }

    @Override
    public Boolean emptyWishlist(Long id) {
        Client client = clientRepo.getOne(id);
        client.getWishList().clear();
        clientRepo.save(client);
        return client.getWishList().isEmpty();
    }

    @Override
    public Boolean deleteCartItem(Long id, Long bookId) {
        Client client = clientRepo.getOne(id);
        Boolean deleted = client.getCart().getBooks().removeIf(book -> book.getId().equals(bookId));
        clientRepo.save(client);
        return deleted;
    }

    @Override
    public Boolean deleteWishListItem(Long id, Long bookId) {
        Client client = clientRepo.getOne(id);
        Boolean deleted = client.getWishList().removeIf(book -> book.getId().equals(bookId));
        clientRepo.save(client);
        return deleted;
    }

    @Override
    public Client findUser(Long id) {
        return null;
    }

    @Override
    public Page<ClientDTO> findUsers(Pageable page) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {

    }

    @Override
    public Client updateUser(Client client) {
        return null;
    }
}
