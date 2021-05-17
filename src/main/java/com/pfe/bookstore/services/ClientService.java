package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.DTO.CartDTO;
import com.pfe.bookstore.DTO.ClientDTO;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.entities.Cart;
import com.pfe.bookstore.entities.Client;
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



@Service
@Transactional
public class ClientService implements IClientService{
    @Autowired
    private IClientRepository clientRepo;
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
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
    public Book addToCart(Long id, BookDTO bookDTO){
        Client client = clientRepo.getOne(id);
        Book book= bookRepository.getOne(bookDTO.getId());
        client.getCart().getBooks().add(book);
        clientRepo.save(client);
        return book;
    }

    @Override
    public CartDTO getCart(Long id) {
        Client client = clientRepo.getOne(id);
        CartDTO cartDTO = modelMapper.map(client.getCart(),CartDTO.class);
        return cartDTO;
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
